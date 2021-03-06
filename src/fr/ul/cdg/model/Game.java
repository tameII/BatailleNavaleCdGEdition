package fr.ul.cdg.model;

import fr.ul.cdg.clients.StrategistClient;
import fr.ul.cdg.factory.Ship;
import fr.ul.cdg.model.strategy.Ai;
import fr.ul.cdg.mv.controller.Phase;
import fr.ul.cdg.util.Vector2;

import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class Game extends Observable implements Serializable {
    private Board aiBoard;
    private Board playerBoard;
    private Phase phase;
    private Ship[] firing;
    private boolean previousHit;
    private Ai ai = new Ai();
    private boolean useRMI;
    private StrategistClient strategistClient;

    public Game(List<Ship> shipListAI, List<Ship> shipListPlayer, boolean useRMI){
        createBoards(shipListAI, shipListPlayer);
        phase = Phase.PLACING;
        firing = new Ship[]{null,null};
        previousHit=false;
        this.useRMI = useRMI;
        strategistClient = new StrategistClient(ai);
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
                previousHit=playerBoard.findBoatAtPosition(pos)!=null;
                setPhase(Phase.AI_FIRE);
                getAiFiring().shot();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(playerBoard.getFleetAmmo()==0){
                            setPhase(Phase.AI_THINKING);
                        }
                        else {
                            setPhase(Phase.PLAYER_AIM);
                        }
                    }
                },800);
            }
        }
        if(getPhase()==Phase.PLAYER_AIM) {
            //Player shot
            if(!getPlayerFiring().canFire()) return;
            if (aiBoard.takeShot(pos)) {
                previousHit=aiBoard.findBoatAtPosition(pos)!=null;
                setPhase(Phase.PLAYER_FIRE);
                getPlayerFiring().shot();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(aiBoard.getFleetAmmo()==0) {
                            setPhase(Phase.PLAYER_AIM);
                        }
                        else {
                            setPhase(Phase.AI_THINKING);
                        }
                    }
                }, 750);
            }
        }
        if(aiBoard.getFleetHp()==0 || playerBoard.getFleetHp()==0 || (playerBoard.getFleetAmmo()==0 && aiBoard.getFleetAmmo()==0)){
            t.cancel();
            setPhase(Phase.GAME_OVER);
        }
    }

    /**
     * Ask the AI to shoot if it can.
     * If  RMI is up, ask the distant AI to shoot instead
     */
    public void nextShotAI() throws NamingException, NotBoundException, RemoteException {

        Random rd = new Random();
        setAiFiring(null);
        while(getAiFiring()==null){
            setFiring(getAiBoard().getShipList().get(rd.nextInt(getAiBoard().getShipList().size())));
        }
        if(useRMI) {
            fireShot(strategistClient.nextShotRMI(ai.getCurrentStrategy(), this));
        }else {
            fireShot(ai.nextShot(this));
        }
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
            update();
        }
        else {
            if(firing.canFire()) setAiFiring(firing);
        }
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
        return playerBoard.getFleetTotalHp();
    }

    public int getAITotalHP() {
        return aiBoard.getFleetTotalHp();
    }

    public boolean isPreviousHit() {
        return previousHit;
    }

    public void setAiStrategy(Ai.Strategies s){
        ai.setStrategy(s);
    }
}
