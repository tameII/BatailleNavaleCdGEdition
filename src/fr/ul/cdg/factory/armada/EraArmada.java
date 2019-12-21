package fr.ul.cdg.factory.armada;

import fr.ul.cdg.factory.Era;
import fr.ul.cdg.factory.Ship;

import java.util.ArrayList;
import java.util.List;

public class EraArmada implements Era {

    @Override
    public List<Ship> shipCreation() {
        ArrayList<Ship> ships = new ArrayList<>();
        for(int i = 0; i<10 ; i++){
            ships.add(new ArmadaShip());
        }
        return ships;
    }
}
