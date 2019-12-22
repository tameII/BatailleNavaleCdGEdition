package fr.ul.cdg.factory.renaissance;


import fr.ul.cdg.factory.Ship;

public class BrickRenaissance extends Ship {
    public static int TOTAL_HP = 1;
    public static int AMMO_MAX = 5;
    public BrickRenaissance(){
        nbCells = 2;
        hp = TOTAL_HP;
        nbMunitions = AMMO_MAX;
    }

    @Override
    public String getName(){
        return "Brick";
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
