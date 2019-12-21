package fr.ul.cdg.clients;

import fr.ul.cdg.mv.view.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BatailleNavale extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainMenuView mmv = new MainMenuView();
        primaryStage.setScene(new Scene(mmv.getView()));
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }
}
