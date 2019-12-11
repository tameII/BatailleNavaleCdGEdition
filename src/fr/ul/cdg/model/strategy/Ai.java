package fr.ul.cdg.model.strategy;


import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;


public class Ai{
    private Strategy strategy;

    public Ai() {
        strategy = new StrategyRandom();
    }

    public Vector2 nextShot(Game game) {
        return strategy.nextShot(game);
    }

}
