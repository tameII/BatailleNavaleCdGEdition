package fr.ul.cdg.mv.controller;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.model.strategy.Ai;
import fr.ul.cdg.model.strategy.StrategyRandom;
import fr.ul.cdg.mv.view.BoardAiView;
import fr.ul.cdg.mv.view.BoardPlayerView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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

    @FXML
    private ChoiceBox<String> aiChoiceBox;

    private Game game;

    private static final String randomFT = "Random Fire (1)";
    private static final String randomLockFT = "Random with Lock (2)";
    private static final String nearHitFT = "Near Hit Search (5)";

    public void initData(Object o){
        game = (Game) o;
        //Create board views
        BoardPlayerView bpv = new BoardPlayerView(game);
        BoardAiView bav = new BoardAiView(game);
        playerPane.getChildren().add(bpv.getView());
        aiPane.getChildren().add(bav.getView());
        //Add choice box choices
        aiChoiceBox.getItems().addAll(randomFT,randomLockFT,nearHitFT);
        aiChoiceBox.setValue(randomFT);
        aiChoiceBox.setOnAction(event -> {
            switch (aiChoiceBox.getValue()){
                case randomFT :
                    game.setAiStrategy(Ai.Strategies.RANDOM);
                    break;
                case randomLockFT :
                    game.setAiStrategy(Ai.Strategies.RANDOM_LOCK);
                    break;
                case nearHitFT :
                    game.setAiStrategy(Ai.Strategies.NEAR_HIT_SEARCH);
                    break;
            }
        });
        //Setup observers
        game.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(this::redraw);
    }

    private void redraw(){
        if(game.getPhase()==Phase.GAME_OVER){
            winnerLabel.setVisible(true);
            if(game.getPlayerBoard().getFleetHp() < game.getAiBoard().getFleetHp()){
                winnerLabel.setText("Communism Wins");
                return;
            }
            if(game.getPlayerBoard().getFleetHp() > game.getAiBoard().getFleetHp()){
                winnerLabel.setText("De Gaulle Wins");
                return;
            }
            winnerLabel.setText("Tied");
        }
    }
}
