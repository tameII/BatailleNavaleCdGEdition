package fr.ul.cdg.mv.controller;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.mv.view.BoardAiView;
import fr.ul.cdg.mv.view.BoardPlayerView;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class BatailleNavaleController implements Controllers{
    @FXML
    private Pane playerPane;

    @FXML
    private Pane aiPane;

    private Game game;

    public void initData(Object o){
        game = (Game) o;
        BoardPlayerView bpv = new BoardPlayerView(game.getPlayerBoard());
        BoardAiView bav = new BoardAiView(game.getAiBoard());
        playerPane.getChildren().add(bpv.getView());
        aiPane.getChildren().add(bav.getView());
    }
}
