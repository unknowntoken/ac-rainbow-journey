package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

public enum ObstacleResource {
    GOD ("resources/images/god1.png"),
    WS("resources/images/windos.png"),
    JS ("resources/images/js1.png");

    private String resource;

    ObstacleResource(String resource){
        this.resource = resource;
    }

    public String getResource (){
        return this.resource;
    }

    public static ObstacleResource getRandomType (){
        return ObstacleResource.values()[(int)(Math.random() * ObstacleResource.values().length)];
    }

}
