package fr.ul.cdg.factory.renaissance;


import fr.ul.cdg.factory.Ship;

public class FrigateRenaissance extends Ship {
    public static int AMMO_MAX = 15;
    public static int TOTAL_HP = 2;
    public FrigateRenaissance(){
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
