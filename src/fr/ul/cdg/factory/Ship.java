package fr.ul.cdg.factory;


import fr.ul.cdg.util.Vector2;

public abstract class Ship {
    protected int hp, nbCells, nbMunitions, orientation;
    protected Vector2 position=null;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    public void takeShot(){
        hp = hp - 1;
    }

    public void shot(){
        nbMunitions--;
    }

    public int getHp() {
        return hp;
    }

    public int getNbCells() {
        return nbCells;
    }

    public int getNbMunitions() {
        return nbMunitions;
    }

    public int getOrientation() {
        return orientation;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public String getName() { return "Ship"; }

    public boolean canFire() { return nbMunitions>0 && getHp() > 0; }

    @Override
    public String toString() {
        return "Ship{" +
                "hp=" + hp +
                ", nbCells=" + nbCells +
                ", nbMunitions=" + nbMunitions +
                ", orientation=" + orientation +
                ", position=" + position +
                '}';
    }

    public abstract double getAmmoMax();
}
