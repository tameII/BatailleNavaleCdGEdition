package fr.ul.cdg.factory.scifi;


import fr.ul.cdg.factory.Ship;

public class CorvetteSciFi extends Ship {
    public static int AMMO_MAX = 20;
    public static int TOTAL_HP = 3;
    public CorvetteSciFi(){
        nbCells = 3;
        hp = TOTAL_HP;
        nbMunitions = AMMO_MAX;
    }

    @Override
    public String getName(){
        return "CorvetteSciFi";
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
