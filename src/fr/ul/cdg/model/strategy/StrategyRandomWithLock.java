package fr.ul.cdg.model.strategy;

import fr.ul.cdg.factory.Ship;
import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.CdGMath;
import fr.ul.cdg.util.Vector2;

public class StrategyRandomWithLock implements Strategy {

    private int locktime;
    private Vector2 lockedpos;
    private static int LOCK_TIME = 6;

    public StrategyRandomWithLock(){
        lockedpos=null;
        locktime=0;
    }

    @Override
    public Vector2 nextShot(Game game) {
        Vector2 pos;
        if(locktime==0){
            pos = game.getPlayerBoard().getRandomShotPosition();
        }
        else{
            pos = game.getPlayerBoard().getRandomShotNearPosition(lockedpos);
        }
        Ship s = game.getPlayerBoard().findBoatAtPosition(pos);
        int hp = s==null?-1:s.getHp();
        while(hp==0){
            pos = game.getPlayerBoard().getRandomShotPosition();
            hp = game.getPlayerBoard().findBoatAtPosition(pos).getHp();
        }
        if(hp>1){
            locktime=LOCK_TIME;
            lockedpos=pos;
        }
        if(hp==1){
            lockedpos=null;
            locktime=0;
        }
        if(hp==-1){
            locktime--;
        }
        locktime=CdGMath.clamp(locktime,0,LOCK_TIME);
        return pos;
    }
}
