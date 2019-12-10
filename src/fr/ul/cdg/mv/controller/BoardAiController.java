package fr.ul.cdg.mv.controller;

import fr.ul.cdg.model.Board;
import fr.ul.cdg.mv.view.BoardCanvas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

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

    private Board board;

    @Override
    public void initData(Object o) {
        board = (Board) o;
    }

    public void update(){
        aiBoardCanvas.drawBoard(board);
    }
}
