package fr.ul.cdg.mv.view;

import fr.ul.cdg.model.Board;
import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.ViewsLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class BoardPlayerView implements Views {

    private BorderPane view;

    public BoardPlayerView(Board board){
        view = (BorderPane) ViewsLoader.loadFXML("playerboardview.fxml",board);
    }

    @Override
    public BorderPane getView() {
        return view;
    }
}
