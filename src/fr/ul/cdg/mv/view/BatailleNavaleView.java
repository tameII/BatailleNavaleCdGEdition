package fr.ul.cdg.mv.view;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.ViewsLoader;
import javafx.scene.layout.StackPane;

public class BatailleNavaleView implements Views{

    private StackPane view;

    public BatailleNavaleView(Game g){
        view = (StackPane) ViewsLoader.loadFXML("bataillenavaleview.fxml",g);
    }

    public StackPane getView() {
        return view;
    }
}
