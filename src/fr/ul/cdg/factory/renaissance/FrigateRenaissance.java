package fr.ul.cdg.factory.renaissance;


import fr.ul.cdg.factory.Ship;

public class FrigateRenaissance extends Ship {
    public FrigateRenaissance(){
        nbCells = 4;
        hp = 4;
        nbMunitions = 20;
    }

    @Override
    public String getName(){
        return "Frigate";
    }
}
