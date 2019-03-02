package org.academiadecodigo.tropadelete.cheiodesono;

public enum PowerUpResource {
    VI("resources/images/vim.png"),
    JAVA("resources/images/java.png");

    private String resource;

    PowerUpResource(String resource){
        this.resource = resource;
    }

    public String getResource (){
        return this.resource;
    }

    public static PowerUpResource getRandomType (){
        return PowerUpResource.values()[(int)(Math.random() * PowerUpResource.values().length)];
    }

}
