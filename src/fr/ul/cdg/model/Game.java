package fr.ul.cdg.model;

import fr.ul.cdg.factory.Ship;
import fr.ul.cdg.model.strategy.Ai;

import java.util.List;
import java.util.Observable;

public class Game extends Observable {
    private Board aiBoard;
    private Board playerBoard;
    private Ai ai = new Ai();

    public Game(List<Ship> shipListAI, List<Ship> shipListPlayer){
        createBoards(shipListAI, shipListPlayer);
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

    public void nextShotAI(){
        ai.nextShot(this);
    }
}
