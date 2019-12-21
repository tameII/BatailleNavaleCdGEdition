package fr.ul.cdg.factory.renaissance;


import fr.ul.cdg.factory.Ship;

public class CaravelRenaissance extends Ship {
    public static int AMMO_MAX = 20;
    public CaravelRenaissance(){
        nbCells = 3;
        hp = 3;
        nbMunitions = AMMO_MAX;
    }

    @Override
    public String getName(){
        return "Caravel";
    }

    @Override
    public double getAmmoMAx() {
        return AMMO_MAX;
    }
}
