package fr.ul.cdg.factory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface Era extends Serializable {

    public List<Ship> shipCreation();
}
