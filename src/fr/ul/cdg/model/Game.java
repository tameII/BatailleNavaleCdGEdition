package fr.ul.cdg.model;

import fr.ul.cdg.factory.Ship;
import fr.ul.cdg.model.strategy.Ai;
import fr.ul.cdg.mv.controller.Phase;
import fr.ul.cdg.util.Vector2;

import java.util.*;

public class Game extends Observable {
    private Board aiBoard;
    private Board playerBoard;
    private Phase phase;
    private Ship[] firing;
    private Ai ai = new Ai();

    public Game(List<Ship> shipListAI, List<Ship> shipListPlayer){
        createBoards(shipListAI, shipListPlayer);
        phase = Phase.PLACING;
        firing = new Ship[]{null,null};
    }

    private void createBoards(List<Ship> shipListAI, List<Ship> shipListPlayer) {
        aiBoard = new Board(shipListAI);
        playerBoard = new Board(shipListPlayer);
        aiBoard.placeShips();
    }

    public Board getAiBoard() {
        return aiBoard;
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public boolean placePlayerShip(Ship s, Vector2 pos, int orientation){
        s.setPosition(pos);
        s.setOrientation(orientation);
        if(!playerBoard.isConflict(s, false)) {
            playerBoard.placeShip(s);
        }else{
            s.setPosition(null);
            return false;
        }
        phase=Phase.PLAYER_THINKING;
        for(Ship ship : playerBoard.getShipList()){
            if(ship.getPosition()==null){
                phase=Phase.PLACING;
            }
        }
        update();
        return true;
    }

    public List<Ship> getNonPlacedShips(){
        List<Ship> list = new LinkedList<>();
        for(Ship s : playerBoard.getShipList()){
            if(s.getPosition()==null){
                list.add(s);
            }
        }
        return list;
    }

    public void fireShot(Vector2 pos){
        Timer t = new Timer();
        if(getPhase()==Phase.AI_THINKING){
            //AI shot
            if(playerBoard.takeShot(pos)) {
                setPhase(Phase.AI_FIRE);
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        setPhase(Phase.PLAYER_AIM);
                    }
                },800);
                getAiFiring().shot();
                return;
            }
        }
        if(getPhase()==Phase.PLAYER_AIM) {
            //Player shot
            if(!getPlayerFiring().canFire()) return;
            if (aiBoard.takeShot(pos)) {
                setPhase(Phase.PLAYER_FIRE);
                getPlayerFiring().shot();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        setPhase(Phase.AI_THINKING);
                    }
                }, 750);
            }
        }
        if(aiBoard.getFleetHp()==0 || playerBoard.getFleetHp()==0){
            t.cancel();
            setPhase(Phase.GAME_OVER);
        }

    }

    public void nextShotAI(){
        Random rd = new Random();
        while(getAiFiring()==null){
            setFiring(getAiBoard().getShipList().get(rd.nextInt(getAiBoard().getShipList().size())));
        }
        fireShot(ai.nextShot(this));
    }

    private void setPhase(Phase phase) {
        this.phase = phase;
        update();
    }

    public Phase getPhase() {
        return phase;
    }

    public void setFiring(Ship firing) {
        if(firing == null) return;
        if(getPlayerBoard().getShipList().contains(firing)){
            setPlayerFiring(firing);
            if(getPhase()==Phase.PLAYER_THINKING) setPhase(Phase.PLAYER_AIM);
        }
        else {
            if(firing.canFire()) setAiFiring(firing);
        }
        update();
    }

    private void setPlayerFiring(Ship firing){
        this.firing[0]=firing;
    }

    private void setAiFiring(Ship firing){
        this.firing[1]=firing;
    }

    public Ship getPlayerFiring(){
        return getFiring()[0];
    }

    public Ship getAiFiring(){
        return getFiring()[1];
    }

    public Ship[] getFiring() {
        return firing;
    }

    private void update(){
        setChanged();
        notifyObservers();
    }

    public int getPlayerTotalHP() {
        return playerBoard.getTotalHP();

    }

    public int getAITotalHP() {
        return aiBoard.getTotalHP();
    }
}
