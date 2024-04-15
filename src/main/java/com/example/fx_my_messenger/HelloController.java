package com.example.fx_my_messenger;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static java.lang.Thread.sleep;

public class HelloController implements Initializable{



    @FXML
    private Label profileLbl,date1,date2,date3,date4,date5,profile,chat;

    @FXML
    private ImageView imgV,messageImg,profileImg,imgV1,imgV2,imgV3,imgV4,imgV5;

    @FXML
    private Pane infoPane,topPane;

    @FXML
    private Label lbl;


    @FXML
    private FontAwesomeIconView newMessage,mail,search,sendM,exit,home,calendar;
    @FXML
    private TextField searchField;
    @FXML
    private Circle online;
    @FXML
    private ScrollPane scrollPane;


    @FXML
    private TextArea textArea1,inMessage,outMessage;
    @FXML
    private AnchorPane personPane,messagePane;
    List<Person>persons;

    Stage stage;
    double currentLayoutY = 10;
    double prefH = 147;
    Map<String,String>messages = new HashMap<>();

    BackgroundFill backgroundFill = new BackgroundFill(Color.web("#becdbc"),null,null);
    Background background = new Background(backgroundFill);
    CornerRadii corner = new CornerRadii(20);
    BackgroundFill backgroundFill2 = new BackgroundFill(Color.web("#ccd0fc"),corner,null);
    Background background2 = new Background(backgroundFill2);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createAList();
        search.setOnMouseClicked(event -> searchPerson(persons));
        searchField.setOnAction(event -> searchPerson(persons));

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    void activateTopPane(MouseEvent event) {
        topPane.setVisible(true);
        topPane.setBackground(background);
        profileLbl.setFont(Font.font(20));
        profileLbl.setText("Info");
    }
    @FXML
    void activateChat(MouseEvent event) {
        topPane.setVisible(false);

    }


    @FXML
    void close(MouseEvent event) {

        stage.close();
    }

    @FXML
    void sendMessage(MouseEvent event) {
        String newText = textArea1.getText();
        String oldText = outMessage.getText();
        outMessage.setWrapText(true);
        outMessage.setStyle("-fx-text-fill: grey;");
        String combinedText = oldText + " -" + newText;
        outMessage.setText(combinedText);
        textArea1.clear();
        saveMessage(lbl,outMessage);
        if(!outMessage.getText().isEmpty()) {
            saveMessageToDB();
        }

    }
    void saveMessage(Label lb,TextArea area) {
        if(!area.getText().isEmpty()) {
            messages.put(lb.getText(), area.getText());

        }
    }
    void showMessage(){
        String personName = lbl.getText();
        String message = messages.get(personName);
        if (message != null) {
            outMessage.setText(message);
        } else {
            outMessage.setText("");
        }


    }

    void addToThePane(Person person){

        Pane pane1 = new Pane();
        pane1.setPrefSize(310,118);

        pane1.setOnMouseClicked(event -> {
            imgV.setImage(person.getImage());
            lbl.setText(person.getName());
            textArea1.setDisable(false);
            messageImg.setImage(person.getImage());
            searchField.setPromptText("Search..");
            showMessage();
            showDbMessage();
        });
        Date time = new Date();
        Label label2 = new Label("Last seen: " + time);
        label2.setPrefSize(190,17);
        label2.setLayoutX(120);
        label2.setLayoutY(90);
        Font font2 = Font.font("Arial", FontPosture.ITALIC, 12);
        label2.setFont(font2);

        Circle circle = new Circle(56,61,50, Color.WHITE);
        DropShadow shadow = new DropShadow();
        circle.setEffect(shadow);

        Circle circle1 = new Circle(275,25,10,Color.web("#11f533"));
        circle1.setEffect(shadow);
        circle1.setOpacity(0.05);

        addLabel(pane1,person);

        pane1.getChildren().addAll(circle,label2,circle1);
        addImageview(pane1,person);
        pane1.setLayoutX(1.6);
        pane1.setLayoutY(currentLayoutY);
        currentLayoutY = currentLayoutY+127;
        personPane.setPrefHeight(prefH);
        prefH = prefH + 127;
        personPane.getChildren().add(pane1);
        pane1.setBackground(background2);
        avoidClick(inMessage);
        avoidClick(outMessage);
        scale(pane1);

    }
    void addLabel(Pane pane, Person per){
        Label label1 = new Label();
        label1.setPrefSize(150,30);
        label1.setLayoutX(132);
        label1.setLayoutY(46);
        double fontSize = 20;
        Font font = new Font(fontSize);
        label1.setFont(font);
        label1.setText(per.getName());
        pane.getChildren().add(label1);
    }
    void addImageview(Pane pane, Person per) {
        ImageView view1 = new ImageView();
        view1.setImage(per.getImage());
        view1.setFitHeight(70);
        view1.setFitWidth(70);
        view1.setLayoutX(21);
        view1.setLayoutY(26);
        pane.getChildren().add(view1);
    }



    void createAList(){

        persons = new ArrayList<>();

        persons.add(new Person("Mari K Vari",Pictures.pic1,outMessage.getText()));
        persons.add(new Person("Toomas J Roomas",Pictures.pic2,outMessage.getText()));
        persons.add(new Person("Jaana H Laana",Pictures.pic3,outMessage.getText()));
        persons.add(new Person("Siin K Liin",Pictures.pic4,outMessage.getText()));
        persons.add(new Person("Harra Hernes", Pictures.pic5,outMessage.getText()));
        persons.add(new Person("Toits",Pictures.pic6,outMessage.getText()));
        persons.add(new Person("Gairo K Imairo",Pictures.pic7,outMessage.getText()));
        persons.add(new Person("Irilla M Ra",Pictures.pic8,outMessage.getText()));
        persons.add(new Person("Nonii Siis", Pictures.pic9,outMessage.getText()));
        persons.add(new Person("Keegi Teine", Pictures.pic10,outMessage.getText()));

        persons.sort(Comparator.comparing(Person::getName));
        personPane.getChildren().clear();

        for(Person person : persons) {
            addToThePane(person);
        }



    }
    void searchPerson(List<Person>list) {
        String name = searchField.getText();
        for (Person person : list) {
            if (name.trim().equalsIgnoreCase(person.getName())) {
                imgV.setImage(person.getImage());
                messageImg.setImage(person.getImage());
                lbl.setText(person.getName());
                searchField.clear();
                searchField.setDisable(true);
                searchField.setPromptText(person.getName());
                profileLbl.setFont(Font.font(24));
                showDbMessage();
                break;

            } else {
                searchField.clear();
                searchField.setDisable(true);
                searchField.setPromptText("No match");
                imgV.setImage(null);
                messageImg.setImage(null);
                lbl.setText(null);
            }
        }
     /*   try {
            sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        Platform.runLater(() ->{
            searchField.setDisable(false);

        });
      /* Timer timer = new Timer();
       timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> searchField.setDisable(false));
            }
        }, 200);*/
    }

        void saveMessageToDB(){

        String sql = "Insert into friends(name, message) values (?,?);";
        String name = lbl.getText();
        String message = outMessage.getText();
        PreparedStatement prepared_InsertStatement = null;
        try {
            prepared_InsertStatement = Connect.createConnection().prepareStatement(sql);
            prepared_InsertStatement.setString(1, name);
            prepared_InsertStatement.setString(2, message);
            prepared_InsertStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }Connect.closeDb();

    }
    void showDbMessage() {


        try {
            String sql = "SELECT * FROM friends WHERE name = ?";
            PreparedStatement statement = Connect.createConnection().prepareStatement(sql);
            statement.setString(1, lbl.getText());
            ResultSet resultSet = statement.executeQuery();

            StringBuilder messagesBuilder = new StringBuilder();
            while (resultSet.next()) {
                String message = resultSet.getString("message");
                messagesBuilder.append(message).append("\n");
            }

            String allMessages = messagesBuilder.toString().trim();
            outMessage.setText(allMessages);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void avoidClick(Node node){
        node.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            event.consume();
        });
    }
    void scale(Node node){
        Scale scaleEffect = new Scale();
        scaleEffect.setX(1.01);
        scaleEffect.setY(1.01);
        DropShadow shadow = new DropShadow(8.0, Color.DARKBLUE);

        node.setOnMouseEntered(event -> {
            node.setEffect(shadow);
            node.getTransforms().add(scaleEffect);
            node.toFront();
            node.setCursor(Cursor.HAND);

        });
        node.setOnMouseExited(event -> {
            node.setEffect(null);
            node.getTransforms().remove(scaleEffect);
        });





    }


}//END