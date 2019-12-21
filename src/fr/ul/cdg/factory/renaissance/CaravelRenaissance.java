package fr.ul.cdg.factory.renaissance;


import fr.ul.cdg.factory.Ship;

public class CaravelRenaissance extends Ship {
    public static int AMMO_MAX = 20;
    public static int TOTAL_HP = 1;
    public CaravelRenaissance(){
        nbCells = 3;
        hp = TOTAL_HP;
        nbMunitions = AMMO_MAX;
    }

    @Override
    public String getName(){
        return "Caravel";
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
