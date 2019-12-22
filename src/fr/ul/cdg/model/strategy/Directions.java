package fr.ul.cdg.model.strategy;

import fr.ul.cdg.util.Vector2;

import java.util.Random;

public enum Directions {
    LEFT,RIGHT,DOWN,UP;

    public static Directions getRandomDirection(){
        Random rd = new Random();
        switch (rd.nextInt(4)){
            case 0:
                return LEFT;
            case 1:
                return RIGHT;
            case 2:
                return DOWN;
            case 3:
                return UP;
        }
        return null;
    }

    public Vector2 getAssociatedVector(){
        switch (this){
            case UP:
                return new Vector2(0,-1);
            case DOWN:
                return new Vector2(0,1);
            case LEFT:
                return new Vector2(-1,0);
            case RIGHT:
                return new Vector2(1,0);
        }
        return new Vector2(0,0);
    }

    public Directions rotate(int steps, boolean clockwise){
        Directions dir = this;
        for(int i = 0 ; i < steps ; i++){
            switch (dir){
                case UP:
                    dir=clockwise?LEFT:RIGHT;
                    break;
                case RIGHT:
                    dir=clockwise?UP:DOWN;
                    break;
                case LEFT:
                    dir=clockwise?DOWN:UP;
                    break;
                case DOWN:
                    dir=clockwise?RIGHT:LEFT;
                    break;
            }
        }
        return dir;
    }

    public Directions flip(){
        switch (this){
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
        }
        return null;
    }
}
