package fr.ul.cdg.mv.view;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.ViewsLoader;
import javafx.scene.layout.HBox;

public class BatailleNavaleView implements Views{

    private HBox view;

    public BatailleNavaleView(Game g){
        view = (HBox) ViewsLoader.loadFXML("bataillenavaleview.fxml",g);
    }

    public HBox getView() {
        return view;
    }
}
