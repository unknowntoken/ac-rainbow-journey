package org.academiadecodigo.tropadelete.cheiodesono;

public enum ObstacleImage {
    GOD ("resources/Gody.png"),
    JS ("resources/images/js.png");

    private String resource;

    ObstacleImage (String resource){
        this.resource = resource;
    }

    public String getResource (){
        System.out.println(resource);
        return this.resource;
    }

    public static ObstacleImage getRandomType (){
        return ObstacleImage.values()[(int)(Math.random() * ObstacleImage.values().length)];
    }

}
