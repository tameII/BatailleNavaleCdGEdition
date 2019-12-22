package fr.ul.cdg.mv.view;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.ViewsLoader;
import javafx.scene.Parent;

public class BatailleNavaleView implements Views{

    private Parent view;

    public BatailleNavaleView(Game g){
        view = ViewsLoader.loadFXML("bataillenavaleview.fxml",g);
    }

    public Parent getView() {
        return view;
    }
}
