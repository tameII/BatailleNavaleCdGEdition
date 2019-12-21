package fr.ul.cdg.factory.renaissance;


import fr.ul.cdg.factory.Ship;

public class GallionRenaissance extends Ship {
    public static int AMMO_MAX = 20;
    public static int TOTAL_HP = 3;
    public GallionRenaissance(){
        nbCells = 5;
        hp = TOTAL_HP;
        nbMunitions = AMMO_MAX;
    }

    @Override
    public String getName(){
        return "Gallion";
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
