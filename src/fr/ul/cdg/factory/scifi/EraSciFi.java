package fr.ul.cdg.factory.scifi;



import fr.ul.cdg.factory.Era;
import fr.ul.cdg.factory.Ship;

import java.util.ArrayList;
import java.util.List;

public class EraSciFi implements Era {
    @Override
    public List<Ship> shipCreation() {
        ArrayList<Ship> listShip = new ArrayList<>();
        listShip.add(new CorvetteSciFi());

        return listShip;
    }
}
