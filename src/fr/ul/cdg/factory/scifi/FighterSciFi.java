package fr.ul.cdg.factory.scifi;


import fr.ul.cdg.factory.Ship;

public class FighterSciFi extends Ship {
    public static int AMMO_MAX = 20;
    public FighterSciFi(){
        nbCells = 2;
        hp = 2;
        nbMunitions = AMMO_MAX;
    }

    @Override
    public String getName(){
        return "FighterSciFi";
    }

    @Override
    public double getAmmoMAx() {
        return AMMO_MAX;
    }
}
