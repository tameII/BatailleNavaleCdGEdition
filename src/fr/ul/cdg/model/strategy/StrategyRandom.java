package fr.ul.cdg.model.strategy;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class StrategyRandom implements Strategy {

    @Override
    public Vector2 nextShot(Game game) {
        return game.getPlayerBoard().getRandomShotPosition();
    }

    @Override
    public Ai.Strategies getAssociatedAiStrategiesName() {
        return Ai.Strategies.RANDOM;
    }
}
