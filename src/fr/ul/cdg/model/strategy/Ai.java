package fr.ul.cdg.model.strategy;


import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;

import java.security.SecureRandomSpi;
import java.util.Random;

public class Ai{
    private Strategy strategy;
    private StrategyRandom strategyRandom;
    public static final int RANDOM = 0;


    public Ai() {
        strategyRandom = new StrategyRandom();
        strategy = strategyRandom;
    }

    public Vector2 nextShot(Game game) {
        return strategy.nextShot(game);
    }

}
