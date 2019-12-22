package fr.ul.cdg.factory.scifi;


import fr.ul.cdg.factory.Ship;

public class FrigateSciFi extends Ship {
    public static int AMMO_MAX = 20;
    public static int TOTAL_HP = 4;
    public FrigateSciFi(){
        nbCells = 4;
        hp = TOTAL_HP;
        nbMunitions = AMMO_MAX;
    }

    @Override
    public String getName(){
        return "Frigate";
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
