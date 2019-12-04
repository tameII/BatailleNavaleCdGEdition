package fr.ul.cdg.factory.scifi;



import fr.ul.cdg.factory.Era;
import fr.ul.cdg.factory.Ship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EraSciFi implements Era {
    @Override
    public Collection<Ship> shipCreation() {
        ArrayList<Ship> listShip = new ArrayList<>();
        listShip.add(new DestroyerSciFi());
        listShip.add(new FrigateSciFi());
        listShip.add(new CorvetteSciFi());
        listShip.add(new CorvetteSciFi());
        listShip.add(new FighterSciFi());
        return listShip;
    }
}
