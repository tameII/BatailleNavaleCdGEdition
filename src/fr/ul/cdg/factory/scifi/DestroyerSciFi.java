package fr.ul.cdg.factory.scifi;


import fr.ul.cdg.factory.Ship;

public class DestroyerSciFi extends Ship {
    public static int AMMO_MAX = 20;
    public static int TOTAL_HP = 5;
    public DestroyerSciFi(){
        nbCells = 5;
        hp = TOTAL_HP;
        nbMunitions = AMMO_MAX;

    }

    @Override
    public String getName(){
        return "DestroyerSciFi";
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
