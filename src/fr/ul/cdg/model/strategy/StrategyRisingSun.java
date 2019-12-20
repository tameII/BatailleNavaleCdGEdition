package fr.ul.cdg.model.strategy;

import fr.ul.cdg.factory.Ship;
import fr.ul.cdg.model.Board;
import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;

import java.util.HashSet;
import java.util.Set;

public class StrategyRisingSun implements Strategy {

    private Set<Vector2> tests;
    private Vector2 current;
    private Vector2 next;

    public StrategyRisingSun(){
        tests=new HashSet<>();
        current=null;
        next=null;
    }

    /**
     * Find ship, then lock it down until death.
     * @param game the current game
     * @return where to shoot next
     */
    @Override
    public Vector2 nextShot(Game game) {
        System.out.println(current);
        if(current==null){
            current=game.getPlayerBoard().getRandomShotPosition();
        }
        else {
            if (game.getPlayerBoard().getFiredShotAtPosition(current).state == Board.EMPTY_FIRED_CELL) {
                current = getNextTest(game);
            } else {
                //Test if we took down boat
                if(game.getPlayerBoard().findBoatAtPosition(current).getHp()==0){

                }
                //Create neighbour tests
                for (int i = -1; i < 2; i += 2) {
                    if (current.getX() + i > 0 && current.getX() + i < Board.BOARD_SIZE) {
                        Vector2 xDif = new Vector2(current.getX() + i, current.getY());
                        Board.FiredShots c = game.getPlayerBoard().getFiredShotAtPosition(xDif);
                        if (c.state == Board.OCCUPIED_CELL || c.state == Board.EMPTY_CELL) {
                            tests.add(xDif);
                        }
                    }
                    if (current.getY() + i > 0 && current.getY() + i < Board.BOARD_SIZE) {
                        Vector2 yDif = new Vector2(current.getX(), current.getY() + i);
                        Board.FiredShots c = game.getPlayerBoard().getFiredShotAtPosition(yDif);
                        if (c.state == Board.OCCUPIED_CELL || c.state == Board.EMPTY_CELL) {
                            tests.add(yDif);
                        }
                    }
                }
                if (next == null) {
                    Vector2 nextTest = getNextTest(game);
                    setNextNeighbour(nextTest);
                    current = nextTest;
                } else {
                    Vector2 nextTest = new Vector2(next);
                    setNextNeighbour(nextTest);
                    current = nextTest;
                }
            }
        }
        System.out.println(current);
        System.out.println(next);
        return current;
    }

    private Vector2 getNextTest(Game game){
        Vector2 cur;
        if(tests.size()==0){
            cur=game.getPlayerBoard().getRandomShotPosition();
        }
        else {
            cur=(Vector2)tests.toArray()[0];
            Ship s = game.getPlayerBoard().findBoatAtPosition(cur);
            while(s!=null && s.getHp()==0){
                //Purge dead boat tests
                tests.remove(cur);
                if(tests.size()==0){
                    cur = game.getPlayerBoard().getRandomShotPosition();
                }else {
                    cur=(Vector2)tests.toArray()[0];
                }
            }
            tests.remove(cur);
        }
        next=null;
        return cur;
    }

    private void setNextNeighbour(Vector2 nextTest){
        Vector2 dif = new Vector2(nextTest);
        dif.add(-current.getX(),-current.getY());
        next = new Vector2(current);
        next.add(dif);
        next.add(dif);
    }
}
