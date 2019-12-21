package fr.ul.cdg.mv.view;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.ViewsLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class BoardPlayerView implements Views {

    private BorderPane view;

    public BoardPlayerView(Game board){
        view = (BorderPane) ViewsLoader.loadFXML("playerboardview.fxml",board);
    }

    @Override
    public Parent getView() {
        return view;
    }
}
