package fr.ul.cdg.factory.scifi;


import fr.ul.cdg.factory.Ship;

public class FrigateSciFi extends Ship {
    public static int AMMO_MAX = 20;
    public FrigateSciFi(){
        nbCells = 4;
        hp = 4;
        nbMunitions = AMMO_MAX;
    }

    @Override
    public String getName(){
        return "FrigateSciFi";
    }

    @Override
    public double getAmmoMAx() {
        return AMMO_MAX;
    }
}
