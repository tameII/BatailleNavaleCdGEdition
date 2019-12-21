package fr.ul.cdg.factory.scifi;


import fr.ul.cdg.factory.Ship;

public class FighterSciFi extends Ship {
    public static int AMMO_MAX = 20;
    public static int TOTAL_HP = 2;
    public FighterSciFi(){
        nbCells = 2;
        hp = TOTAL_HP;
        nbMunitions = AMMO_MAX;
    }

    @Override
    public String getName(){
        return "FighterSciFi";
    }

    @Override
    public double getAmmoMax() {
        return AMMO_MAX;
    }
    @Override
    public int getTotalHP() {
        return TOTAL_HP;
    }
}
