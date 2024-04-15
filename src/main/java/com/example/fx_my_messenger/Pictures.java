package com.example.fx_my_messenger;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pictures {
    static List<Image> images;
    static Image pic1 = new Image("https://randomuser.me/api/portraits/women/28.jpg");
    static Image pic2 = new Image("https://randomuser.me/api/portraits/men/65.jpg");
    static Image pic3 = new Image("https://randomuser.me/api/portraits/women/40.jpg");
    static Image pic4 = new Image("https://randomuser.me/api/portraits/men/12.jpg");
    static Image pic5 = new Image("https://randomuser.me/api/portraits/men/71.jpg");
    static Image pic6 = new Image("https://randomuser.me/api/portraits/men/32.jpg");
    static Image  pic7 = new Image("https://randomuser.me/api/portraits/men/79.jpg");
    static Image pic8 = new Image("https://randomuser.me/api/portraits/women/44.jpg");
    static Image  pic9 = new Image("https://randomuser.me/api/portraits/men/58.jpg");
    static Image pic10 = new Image("https://randomuser.me/api/portraits/men/93.jpg");
    static Image addAnImage (){

        images = new ArrayList<>();
        images.add(pic1);
        images.add(pic2);
        images.add(pic3);
        images.add(pic4);
        images.add(pic5);
        images.add(pic6);
        images.add(pic7);
        images.add(pic8);
        images.add(pic9);

        Collections.shuffle(images);
        return images.get(1);

    }

}
