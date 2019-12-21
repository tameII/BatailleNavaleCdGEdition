package fr.ul.cdg.mv.view;

import fr.ul.cdg.util.ViewsLoader;
import javafx.scene.Parent;

public class MainMenuView implements Views {

    private Parent view;

    public MainMenuView(){
        view = ViewsLoader.loadFXML("mainmenu.fxml",null);
    }

    @Override
    public Parent getView() {
        return view;
    }
}
