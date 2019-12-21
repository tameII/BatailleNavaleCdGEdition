package fr.ul.cdg.factory.renaissance;


import fr.ul.cdg.factory.Ship;

public class GallionRenaissance extends Ship {
    public static int AMMO_MAX = 20;
    public GallionRenaissance(){
        nbCells = 5;
        hp = 5;
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
}
