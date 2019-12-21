package fr.ul.cdg.factory.renaissance;


import fr.ul.cdg.factory.Ship;

public class BrickRenaissance extends Ship {
    public static int AMMO_MAX = 20;
    public BrickRenaissance(){
        nbCells = 2;
        hp = 2;
        nbMunitions = AMMO_MAX;
    }

    @Override
    public String getName(){
        return "Brick";
    }

    @Override
    public double getAmmoMAx() {
        return AMMO_MAX;
    }
}
