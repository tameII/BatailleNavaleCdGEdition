package fr.ul.cdg.model.strategy;

import fr.ul.cdg.factory.Ship;
import fr.ul.cdg.model.Board;
import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;

public class StrategyNearHitSearch implements Strategy {

    private Directions directions;
    private boolean alignment;
    private Vector2 firstHit;
    private Vector2 lastHit;

    public StrategyNearHitSearch(){
        reset();
    }

    @Override
    public Vector2 nextShot(Game game) {
        Vector2 pos;
        if(firstHit==null){
            //No target locked
            pos = getInitialFire(game);
        }
        else {
            //Target selected
            if(lastHit==null){
                if (directions==null){
                    directions=Directions.getRandomDirection();
                }
                pos=firstHit.adjacent(directions);
                int state = game.getPlayerBoard().getFiredShotAtPosition(pos).state;
                while(state == Board.EMPTY_FIRED_CELL || state == Board.OCCUPIED_DAMAGED_CELL){
                    directions=directions.rotate(1,true);
                    pos=firstHit.adjacent(directions);
                    state = game.getPlayerBoard().getFiredShotAtPosition(pos).state;
                }
                if(isHit(pos,game)){
                    if(isLockableHit(pos,game)){
                        //Orientation found
                        lastHit=pos;
                        alignment=true;
                    }
                    else {
                        //Target destroyed
                        reset();
                    }
                }
                else {
                    //Seek orientation
                    if(alignment){
                        directions=directions.flip();
                        alignment=false;
                    }
                    else{
                        directions=directions.rotate(1,true);
                        alignment=true;
                    }
                }
            }
            else {
                //Orientation fixed
                if(!alignment){
                    //Go back from start, but fire in opposite direction
                    pos=firstHit.adjacent(directions);
                }
                else{
                    pos=lastHit.adjacent(directions);
                }
                if(isHit(pos,game)){
                    if(isLockableHit(pos,game)){
                        lastHit=pos;
                        alignment=true;
                    }
                    else {
                        reset();
                    }
                }
                else {
                    if(alignment){
                        alignment=false;
                        directions=directions.flip();
                    }
                    else {
                        //Edge case : reset
                        reset();
                        pos=getInitialFire(game);
                    }
                }
            }
        }
        int state = game.getPlayerBoard().getFiredShotAtPosition(pos).state;
        if(state == Board.EMPTY_FIRED_CELL || state == Board.OCCUPIED_DAMAGED_CELL){
            //Edge case : reset
            reset();
            pos=getInitialFire(game);
        }
        return pos;
    }

    private Vector2 getInitialFire(Game game){
        Vector2 pos;
        pos=game.getPlayerBoard().getRandomShotPosition();
        if(isHit(pos,game)){
            if(isLockableHit(pos,game)){
                firstHit=pos;
            }
            else {
                reset();
            }
        }
        return pos;
    }

    private boolean isLockableHit(Vector2 pos, Game game){
        Ship s = game.getPlayerBoard().findBoatAtPosition(pos);
        int hp = s!=null?s.getHp():-1;
        return hp>1;
    }

    private boolean isHit(Vector2 pos, Game game){
        Ship s = game.getPlayerBoard().findBoatAtPosition(pos);
        int hp = s!=null?s.getHp():-1;
        return hp>0;
    }

    private void reset(){
        directions=null;
        alignment=true;
        firstHit=null;
        lastHit=null;
    }
}
