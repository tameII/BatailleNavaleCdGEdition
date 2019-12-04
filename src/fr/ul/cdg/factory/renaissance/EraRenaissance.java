package fr.ul.cdg.factory.renaissance;



import fr.ul.cdg.factory.Era;
import fr.ul.cdg.factory.Ship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EraRenaissance implements Era {
    @Override
    public List<Ship> shipCreation() {
        ArrayList<Ship> ships = new ArrayList<>();
        ships.add(new GallionRenaissance());
        ships.add(new FrigateRenaissance());
        ships.add(new CaravelRenaissance());
        ships.add(new CaravelRenaissance());
        ships.add(new BrickRenaissance());
        return ships;
    }
}
