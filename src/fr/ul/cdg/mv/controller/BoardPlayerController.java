package fr.ul.cdg.mv.controller;

import fr.ul.cdg.factory.Ship;
import fr.ul.cdg.model.Game;
import fr.ul.cdg.mv.view.BoardCanvas;
import fr.ul.cdg.util.Vector2;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class BoardPlayerController implements Controllers{

    @FXML
    private BoardCanvas playerBoardCanvas;

    @FXML
    private ProgressBar playerFleetHP;

    @FXML
    private ProgressBar boatSelectHP;

    @FXML
    private Label placingLabel;

    @FXML
    private Label hitLabel;

    @FXML
    private Label turnLabel;

    @FXML
    private Label boatSelectLabel;

    @FXML
    private VBox boatSelectBox;

    @FXML
    private HBox boatAmmo;

    private Game g;
    private boolean vertical=false;
    private List<Ship> ships;

    public void initialize(){
        boatSelectBox.setVisible(false);
        turnLabel.setVisible(false);
        hitLabel.setVisible(false);
        playerBoardCanvas.setOnMouseClicked(event -> {
            Phase phase=g.getPhase();
            switch(phase){
                case PLACING:
                    if(event.getButton()== MouseButton.SECONDARY){
                        vertical=!vertical;
                    }
                    if(event.getButton()== MouseButton.PRIMARY){
                        Ship s = ships.remove(0);
                        Vector2 pos = playerBoardCanvas.viewToBoard(new Vector2((int)event.getX(),(int)event.getY()));
                        g.placePlayerShip(s,pos,vertical?Ship.VERTICAL:Ship.HORIZONTAL);
                    }
                    break;
                case PLAYER_AIM:
                    if(event.getButton()==MouseButton.PRIMARY){
                        Vector2 pos = playerBoardCanvas.viewToBoard(new Vector2((int)event.getX(),(int)event.getY()));
                        Ship s = g.getPlayerBoard().findBoatAtPosition(pos);
                        if(s==null || s.equals(g.getFiring())){
                            g.setFiring(null);
                            break;
                        }
                    }
                case PLAYER_THINKING:
                    if(event.getButton()==MouseButton.PRIMARY){
                        Vector2 pos = playerBoardCanvas.viewToBoard(new Vector2((int)event.getX(),(int)event.getY()));
                        Ship s = g.getPlayerBoard().findBoatAtPosition(pos);
                        if(s!=null){
                            g.setFiring(s);
                        }
                    }
                    break;
            }
        });
    }

    @Override
    public void initData(Object o) {
        g = (Game) o;
        ships=new LinkedList<>(g.getPlayerBoard().getShipList());
        g.addObserver(this);
        redraw();
    }

    @Override
    public void update(Observable o, Object arg) {
        redraw();
    }

    private void redraw(){
        playerBoardCanvas.drawBoard(g.getPlayerBoard(),true);
        Phase phase=g.getPhase();
        switch (phase){
            case PLACING:
                //TODO : Add a ghost display of where the ship would be placed (or at least a way to know if it's gonna be placed vertically or horizontally)
                placingLabel.setVisible(true);
                placingLabel.setText("Placing "+ships.get(0).getName()+" ("+ships.get(0).getNbCells()+")");
                break;
            case PLAYER_THINKING:
                placingLabel.setVisible(false);
                turnLabel.setVisible(true);
                boatSelectBox.setVisible(false);
                break;
            case PLAYER_AIM:
                turnLabel.setVisible(false);
                boatSelectBox.setVisible(true);
                boatSelectHP.progressProperty().setValue((double)g.getFiring().getHp()/g.getFiring().getNbCells());
                boatSelectLabel.setText(g.getFiring().getName()+"("+g.getFiring().getNbCells()+") : ");
                break;
            case PLAYER_FIRE:
                //TODO : Show if the shot was either a hit or miss
                boatSelectBox.setVisible(false);
                hitLabel.setVisible(true);
                break;
            case AI_FIRE:
            case AI_THINKING:
                turnLabel.setVisible(false);
                boatSelectBox.setVisible(false);
                hitLabel.setVisible(false);
                break;
        }
        playerFleetHP.progressProperty().setValue((float)g.getPlayerBoard().getFleetHp()/17f);
    }
}
