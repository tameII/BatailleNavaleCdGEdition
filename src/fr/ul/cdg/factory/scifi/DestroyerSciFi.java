package fr.ul.cdg.factory.scifi;


import fr.ul.cdg.factory.Ship;

public class DestroyerSciFi extends Ship {
    public static int AMMO_MAX = 20;
    public DestroyerSciFi(){
        nbCells = 5;
        hp = 5;
        nbMunitions = AMMO_MAX;

    }

    @Override
    public String getName(){
        return "DestroyerSciFi";
    }

    @Override
    public double getAmmoMAx() {
        return AMMO_MAX;
    }
}
