package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

import java.util.ArrayList;

public class ParallaxImageSet {
    private ArrayList<ParallaxImage> images;

    public ParallaxImageSet(){
        images = new ArrayList<>();
    }
    public void add (ParallaxImage pic){
        images.add(pic);
    }

    public void update (){
        for (ParallaxImage pi: images) {
            pi.update();
        }
    }

    public void showAll (){
        for (ParallaxImage pi: images) {
            pi.show();
        }
    }
    public void hideAll (){
        for (ParallaxImage pi: images) {
            pi.hide();
        }
    }
}
