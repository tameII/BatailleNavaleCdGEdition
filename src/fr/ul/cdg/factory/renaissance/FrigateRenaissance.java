package fr.ul.cdg.factory.renaissance;


import fr.ul.cdg.factory.Ship;

public class FrigateRenaissance extends Ship {
    public static int AMMO_MAX = 20;
    public FrigateRenaissance(){
        nbCells = 4;
        hp = 4;
        nbMunitions = AMMO_MAX;
    }

    @Override
    public String getName(){
        return "Frigate";
    }

    @Override
    public double getAmmoMAx() {
        return AMMO_MAX;
    }
}
