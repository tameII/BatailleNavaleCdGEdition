package fr.ul.cdg.mv.controller;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.mv.view.BoardCanvas;
import fr.ul.cdg.util.Vector2;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BoardAiController implements Controllers{

    @FXML
    private BoardCanvas aiBoardCanvas;

    @FXML
    private ProgressBar aiFleetHP;

    @FXML
    private Label hitLabel;

    @FXML
    private Label boatSelectLabel;

    @FXML
    private Label turnLabel;




    private Game g;

    public void initialize(){
        aiBoardCanvas.setOnMouseClicked(event -> {
            Phase phase = g.getPhase();
            switch (phase){
                case PLAYER_AIM:
                    Vector2 pos = aiBoardCanvas.viewToBoard(new Vector2((int)event.getX(),(int)event.getY()));
                    g.fireShot(pos);
                    break;
            }
        });
    }

    @Override
    public void initData(Object o) {
        g  = (Game) o;
        g.addObserver(this);
        update(g,null);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(this::redraw);
        switch (g.getPhase()){
            case AI_THINKING:
                Timer t = new Timer();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        g.nextShotAI();
                    }
                },750);
                break;
        }
    }

    private void redraw(){
        aiBoardCanvas.drawBoard(g.getAiBoard(),false);
        switch (g.getPhase()){
            case PLACING:
            case PLAYER_AIM:
            case PLAYER_THINKING:
                turnLabel.setVisible(false);
                boatSelectLabel.setVisible(false);
                hitLabel.setVisible(false);
                break;
            case AI_THINKING:
                turnLabel.setVisible(true);
                hitLabel.setVisible(false);
                boatSelectLabel.setVisible(false);
                break;
            case AI_FIRE:
                turnLabel.setVisible(false);
                hitLabel.setVisible(true);
                boatSelectLabel.setVisible(true);
                hitLabel.setText(g.isPreviousHit()?"HIT !":"Missed !");
                boatSelectLabel.setText("Fired with "+g.getAiFiring().getName());
                break;
            case GAME_OVER:
                turnLabel.setVisible(false);
                boatSelectLabel.setVisible(false);
        }
        aiFleetHP.progressProperty().setValue((float)g.getAiBoard().getFleetHp()/(float)g.getAITotalHP());
        if(g.getAiBoard().getFleetAmmo()==0){
            hitLabel.setVisible(true);
            if(g.getAiBoard().getFleetHp()==0){
                hitLabel.setText("Defeated");
                return;
            }
            hitLabel.setText("Out of ammo");
        }
    }

}
