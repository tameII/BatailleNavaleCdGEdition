package fr.ul.cdg.mv.controller;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.model.strategy.StrategyRandom;
import fr.ul.cdg.model.strategy.StrategyRisingSun;
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

    public void initData(Object o){
        game = (Game) o;
        //Create board views
        BoardPlayerView bpv = new BoardPlayerView(game);
        BoardAiView bav = new BoardAiView(game);
        playerPane.getChildren().add(bpv.getView());
        aiPane.getChildren().add(bav.getView());
        //Add choice box choices
        aiChoiceBox.getItems().add("Random Fire (1)");
        aiChoiceBox.getItems().add("Random with Lock (2)");
        aiChoiceBox.getItems().add("The Rising Sun (5)");
        aiChoiceBox.setValue("Random Fire (1)");
        aiChoiceBox.setOnAction(event -> {
            switch (aiChoiceBox.getValue()){
                case "Random Fire (1)":
                    game.setAiStrategy("StrategyRandom");
                    break;
                case "Random with Lock (2)" :
                    game.setAiStrategy("StrategyRandom");
                    break;
                case "The Rising Sun (5)" :
                    game.setAiStrategy("StrategyRisingSun");
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
