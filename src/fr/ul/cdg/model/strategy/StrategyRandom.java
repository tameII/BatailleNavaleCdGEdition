package fr.ul.cdg.model.strategy;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;

public class StrategyRandom implements Strategy {
    @Override
    public Vector2 nextShot(Game game) {
        return game.getPlayerBoard().getRandomShotPosition();
    }
}
