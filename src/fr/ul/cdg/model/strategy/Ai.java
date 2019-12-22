package fr.ul.cdg.model.strategy;


import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;


public class Ai{
    private Strategy currentStrategy;
    private StrategyRandom random;
    private StrategyRisingSun risingSun;


    public Ai() {
        random = new StrategyRandom();
        risingSun = new StrategyRisingSun();
        currentStrategy = random;
    }

    public Vector2 nextShot(Game game) {
        return currentStrategy.nextShot(game);
    }

    public void setStrategy(String s) {
        switch(s){
            case "random":
                currentStrategy = random;
                System.out.println("random");
                break;
            case "rising sun":
                currentStrategy = risingSun;
                System.out.println("rising sun");
                break;
            default:
                break;
        }
    }
}
