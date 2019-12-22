package fr.ul.cdg.model.strategy;


import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Ai implements Strategist, Serializable{

    public enum Strategies{
        RANDOM,RANDOM_LOCK,NEAR_HIT_SEARCH
    }

    private Strategy currentStrategy;
    private StrategyRandom random;
    private StrategyRandomWithLock lock;
    private StrategyNearHitSearch nearHitSearch;

    public Ai() {
        random = new StrategyRandom();
        lock = new StrategyRandomWithLock();
        nearHitSearch = new StrategyNearHitSearch();
        currentStrategy = random;
    }

    @Override
    public Vector2 nextShot(Game game) throws RemoteException {
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

        setStrategy(strategy.getAssociatedAiStrategiesName());
        return this.currentStrategy.nextShot(game);
    }

    public void setStrategy(Strategies s) {
        switch(s){
            case RANDOM:
                currentStrategy = random;
                break;
            case RANDOM_LOCK:
                currentStrategy = lock;
                break;
            case NEAR_HIT_SEARCH:
                currentStrategy = nearHitSearch;
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
