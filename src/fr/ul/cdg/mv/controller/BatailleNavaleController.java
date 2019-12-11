package fr.ul.cdg.mv.controller;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.mv.view.BoardAiView;
import fr.ul.cdg.mv.view.BoardPlayerView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.Observable;

public class BatailleNavaleController implements Controllers {
    @FXML
    private Pane playerPane;

    @FXML
    private Pane aiPane;

    @FXML
    private Label winnerLabel;

    private Game game;

    public void initData(Object o){
        game = (Game) o;
        //Create board views
        BoardPlayerView bpv = new BoardPlayerView(game);
        BoardAiView bav = new BoardAiView(game);
        playerPane.getChildren().add(bpv.getView());
        aiPane.getChildren().add(bav.getView());
        //Setup observers
        game.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(game.getPhase()==Phase.GAME_OVER){
            winnerLabel.setVisible(true);
            if(game.getPlayerBoard().getFleetHp()==0){
                winnerLabel.setText("Communism Wins");
                return;
            }
            winnerLabel.setText("De Gaulle Wins");
        }
    }
}
