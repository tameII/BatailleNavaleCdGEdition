package fr.ul.cdg.factory.scifi;


import fr.ul.cdg.factory.Ship;

public class DestroyerSciFi extends Ship {
    public DestroyerSciFi(){
        nbCells = 5;
        hp = 5;
        nbMunitions = 20;

    }

    @Override
    public String getName(){
        return "DestroyerSciFi";
    }
}
