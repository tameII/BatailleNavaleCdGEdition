package fr.ul.cdg.mv.controller;

import fr.ul.cdg.model.Board;
import fr.ul.cdg.model.Game;
import fr.ul.cdg.mv.view.BoardCanvas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    private Board board;

    @Override
    public void initData(Object o) {
        this.board = (Board) o;
    }

    public void update(){
        playerBoardCanvas.drawBoard(board);
    }

}
