package org.academiadecodigo.tropadelete.cheiodesono;

public enum ObstacleImage {
    GOD ("resources/images/god1.png"),
    JS ("resources/images/js1.png"),
    WS("resources/images/windos.png");


    private String resource;

    ObstacleImage (String resource){
        this.resource = resource;
    }

    public String getResource (){
        return this.resource;
    }

    public static ObstacleImage getRandomType (){
        return ObstacleImage.values()[(int)(Math.random() * ObstacleImage.values().length)];
    }

}
