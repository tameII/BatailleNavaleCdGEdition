package fr.ul.cdg.model.strategy;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class StrategyRandom implements Strategy {
    private Vector2 lastShooted;
    private boolean lockedTarget;
    private int lockTimer;
    private Random random;
    private static final int LOCK_TIMER_MAX = 6;

    public StrategyRandom(){
        lockedTarget = false;
        lockTimer = 0;
        random = new Random();
    }

    /**
     * Shot randomly on the board.
     * If a ship is shooted, lock the position until a timer stop.
     * @param game the game
     * @return the next shot
     */
    @Override
    public Vector2 nextShot(Game game) {
        Vector2 shot;
        if (lockedTarget) {
            shot = game.getPlayerBoard().getRandomShotNearPosition(lastShooted);
        }else{
            shot = game.getPlayerBoard().getRandomShotPosition();
        }
        updateState(game, shot);
        return shot;
    }

    /**
     * Update the state for the tactic.
     * Is a ship shooted ?
     * so we lock the position for further investigation
     * The position is locked for with a lockTimer duration.
     * The lockTimer is random.
     * @param game the game
     * @param shot where the ai shooted
     */
    private void updateState(Game game, Vector2 shot){
        if (game.getPlayerBoard().findBoatAtPosition(shot) != null) {
            if(!lockedTarget) {
                lockTimer = random.nextInt(LOCK_TIMER_MAX);
                lockedTarget = true;
            }else if(lockTimer>0){ //lockedTarget=true
                lockTimer--;
            }else{ //lockedTarget=true && lockTimer<=0
                lockedTarget = false;
            }
            lastShooted = shot;
        }

    }


}
