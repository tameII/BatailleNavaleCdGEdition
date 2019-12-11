package fr.ul.cdg.util;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ImagesLoader {
    public enum Sprites{
        CROSS,NOUGHT
    }

    private static ImagesLoader instance = new ImagesLoader();
    private Map<Sprites, Image> spriteMap;

    private ImagesLoader(){
        spriteMap=new HashMap<>();
        spriteMap.put(Sprites.CROSS,new Image(getClass().getResourceAsStream("/images/cross.png")));
        spriteMap.put(Sprites.NOUGHT,new Image(getClass().getResourceAsStream("/images/nought.png")));
    }

    public Image getSprite(Sprites sprite){
        return spriteMap.get(sprite);
    }

    public static ImagesLoader getInstance() {
        return instance;
    }
}
