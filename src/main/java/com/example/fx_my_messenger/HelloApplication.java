package com.example.fx_my_messenger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setTitle("Messenger");
        Image icon = new Image("send.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        //stage.initStyle(StageStyle.DECORATED);
        // stage.setY(50);
        //stage.setFullScreen(true);
        // stage.setFullScreenExitHint("No way out..  hahahahahahahaaa");
        //stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}