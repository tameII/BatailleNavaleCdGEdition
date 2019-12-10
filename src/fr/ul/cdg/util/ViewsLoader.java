package fr.ul.cdg.util;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.mv.controller.Controllers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ViewsLoader {

    public static Parent loadFXML(String file, Object controllerParam){
        FXMLLoader loader = new FXMLLoader(ViewsLoader.class.getResource("/fxml/"+file));
        try {
            Parent p = loader.load();
            loader.<Controllers>getController().initData(controllerParam);
            return p;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}
