package fr.ul.cdg.factory;


import fr.ul.cdg.util.Vector2;

public abstract class Ship {
    protected int hp, nbCells, nbMunitions, orientation;
    protected Vector2 position;

    public void takeShot(){
        hp = hp - 1;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "hp=" + hp +
                ", nbCells=" + nbCells +
                ", nbMunitions=" + nbMunitions +
                '}';
    }
}
