package fr.ul.cdg.factory.scifi;


import fr.ul.cdg.factory.Ship;

public class CorvetteSciFi extends Ship {
    public static int AMMO_MAX = 20;
    public CorvetteSciFi(){
        nbCells = 3;
        hp = 3;
        nbMunitions = AMMO_MAX;
    }

    @Override
    public String getName(){
        return "CorvetteSciFi";
    }

    @Override
    public double getAmmoMAx() {
        return AMMO_MAX;
    }
}
