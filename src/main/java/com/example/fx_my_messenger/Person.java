package com.example.fx_my_messenger;

import javafx.scene.image.Image;

public class Person {
    String name;

    String message;
    Image image;

    private int id;

    public Person(){}

    Person(String name, Image image, String message){
        this.name = name;
        this.image = image;
        this.message = message;

    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setImage(Image image) {
        this.image = image;
    }




}
