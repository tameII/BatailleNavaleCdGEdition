package fr.ul.cdg.factory;


import fr.ul.cdg.util.Vector2;

public abstract class Ship {
    protected int hp, nbCells, nbMunitions, orientation;
    protected Vector2 position;

    protected void takeShot(){}
}
