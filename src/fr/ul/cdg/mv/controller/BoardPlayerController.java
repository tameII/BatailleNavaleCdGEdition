package fr.ul.cdg.mv.controller;

import fr.ul.cdg.factory.Ship;
import fr.ul.cdg.model.Game;
import fr.ul.cdg.mv.view.BoardCanvas;
import fr.ul.cdg.util.Vector2;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
    private ProgressBar boatAmmo;

    private Game g;
    private boolean vertical=false;

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
                        Ship s = g.getNonPlacedShips().get(0);
                        Vector2 pos = playerBoardCanvas.viewToBoard(new Vector2((int)event.getX(),(int)event.getY()));
                        g.placePlayerShip(s,pos,vertical?Ship.VERTICAL:Ship.HORIZONTAL);

                    }
                    break;
                case PLAYER_AIM:
                    if(event.getButton()==MouseButton.PRIMARY){
                        Vector2 pos = playerBoardCanvas.viewToBoard(new Vector2((int)event.getX(),(int)event.getY()));
                        Ship s = g.getPlayerBoard().findBoatAtPosition(pos);
                        if(s==null){
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
                            playerBoardCanvas.setSelected(s);
                        }
                    }
                    break;
            }
        });
        playerBoardCanvas.setOnMouseMoved(event -> {
            if(g.getPhase()==Phase.PLACING){
                playerBoardCanvas.drawBoard(g.getPlayerBoard(),true);
                playerBoardCanvas.drawBoat(g.getNonPlacedShips().get(0),playerBoardCanvas.viewToBoard(new Vector2((int)event.getX(),(int)event.getY())),vertical?Ship.VERTICAL:Ship.HORIZONTAL,new Color(0,0,1,0.3));
            }
        });
    }

    @Override
    public void initData(Object o) {
        g = (Game) o;
        g.addObserver(this);
        redraw();
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(this::redraw);
    }

    private void redraw(){
        playerBoardCanvas.drawBoard(g.getPlayerBoard(),true);
        Phase phase=g.getPhase();
        switch (phase){
            case PLACING:
                placingLabel.setVisible(true);
                placingLabel.setText("Placing "+g.getNonPlacedShips().get(0).getName()+" ("+g.getNonPlacedShips().get(0).getNbCells()+")");
                break;
            case PLAYER_THINKING:
                placingLabel.setVisible(false);
                turnLabel.setVisible(true);
                boatSelectBox.setVisible(false);
                break;
            case PLAYER_AIM:
                turnLabel.setVisible(false);
                boatSelectBox.setVisible(true);
                boatSelectHP.progressProperty().setValue((double)g.getPlayerFiring().getHp()/g.getPlayerFiring().getTotalHP());
                boatSelectLabel.setText(g.getPlayerFiring().getName()+" : ");
                boatAmmo.progressProperty().setValue((double)g.getPlayerFiring().getNbMunitions()/g.getPlayerFiring().getAmmoMax());
                break;
            case PLAYER_FIRE:
                boatSelectBox.setVisible(false);
                hitLabel.setVisible(true);
                hitLabel.setText(g.isPreviousHit()?"HIT !":"Missed !");
                break;
            case AI_FIRE:
            case AI_THINKING:
                turnLabel.setVisible(false);
                boatSelectBox.setVisible(false);
                hitLabel.setVisible(false);
                break;
            case GAME_OVER:
                turnLabel.setVisible(false);
                boatSelectBox.setVisible(false);
                placingLabel.setVisible(false);
        }
        playerFleetHP.progressProperty().setValue((float)g.getPlayerBoard().getFleetHp()/(float)g.getPlayerTotalHP());
        if(g.getPlayerBoard().getFleetAmmo()==0){
            hitLabel.setVisible(true);
            if(g.getPlayerBoard().getFleetHp()==0){
                hitLabel.setText("Defeated");
                return;
            }
            hitLabel.setText("Out of ammo");
        }
    }
}
