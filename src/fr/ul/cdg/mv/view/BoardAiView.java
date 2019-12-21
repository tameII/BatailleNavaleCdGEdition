package fr.ul.cdg.mv.view;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.ViewsLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class BoardAiView implements Views {

    private BorderPane view;

    public BoardAiView(Game board){
        view = (BorderPane) ViewsLoader.loadFXML("aiboardview.fxml",board);
    }

    @Override
    public Parent getView() {
        return view;
    }
}
