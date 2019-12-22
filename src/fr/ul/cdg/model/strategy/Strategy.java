package fr.ul.cdg.model.strategy;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;

import java.io.Serializable;


public interface Strategy extends Serializable {
    Vector2 nextShot(Game game);
}
