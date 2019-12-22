package fr.ul.cdg.model.strategy;


import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;

import java.io.Serializable;

public class Ai implements Strategist, Serializable{
    private Strategy currentStrategy;
    private StrategyRandom random;
    private StrategyRisingSun risingSun;


    public Ai() {
        random = new StrategyRandom();
        risingSun = new StrategyRisingSun();
        currentStrategy = random;
    }

    @Override
    public Vector2 nextShot(Game game) {
        return currentStrategy.nextShot(game);
    }

    /**
     * Check if the given Strategy equal the actual
     * If not, the given strategy replace the actual.
     * @param strategy the strategy you want to use
     * @param game the game
     * @return the position of the nextShot
     */
    @Override
    public Vector2 nextShot(Strategy strategy, Game game) {
        if(this.currentStrategy.equals(strategy)){
            return this.currentStrategy.nextShot(game);
        }

        setStrategy(strategy.getClass().getSimpleName());
        return this.currentStrategy.nextShot(game);
    }

    public void setStrategy(String s) {
        switch(s){
            case "StrategyRandom":
                currentStrategy = random;
                break;
            case "StrategyRisingSun":
                currentStrategy = risingSun;
                break;
            default:
                break;
        }
    }

    /**
     * @return the current Strategy of the Ai
     */
    public Strategy getCurrentStrategy() {
        return currentStrategy;
    }
}
