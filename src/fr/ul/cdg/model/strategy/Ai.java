package fr.ul.cdg.model.strategy;


import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;


public class Ai{

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

    public Vector2 nextShot(Game game) {
        return currentStrategy.nextShot(game);
    }

    public void setStrategy(Strategies s) {
        switch(s){
            case RANDOM:
                currentStrategy = random;
                break;
            case RANDOM_LOCK:
                currentStrategy = lock;
            case NEAR_HIT_SEARCH:
                currentStrategy = nearHitSearch;
                break;
        }
    }
}
