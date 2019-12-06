package fr.ul.cdg.model.strategy;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;


public interface Strategy {
    Vector2 nextShot(Game game);
}
