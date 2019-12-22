package fr.ul.cdg.factory.armada;


import fr.ul.cdg.factory.Ship;

public class ArmadaShip extends Ship {
    public static int TOTAL_HP = 1;
    public static int AMMO_MAX = 5;
    public ArmadaShip(){
        nbCells = 2;
        hp = TOTAL_HP;
        nbMunitions = AMMO_MAX;
    }

    @Override
    public String getName(){
        return "Ship";
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
