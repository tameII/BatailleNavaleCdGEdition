package fr.ul.cdg.clients;

import fr.ul.cdg.factory.Era;
import fr.ul.cdg.factory.renaissance.EraRenaissance;
import fr.ul.cdg.model.Game;
import fr.ul.cdg.mv.view.BatailleNavaleView;
import fr.ul.cdg.mv.view.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BatailleNavaleUnique extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Era era = new EraRenaissance();
        primaryStage.initStyle(StageStyle.DECORATED);
        BatailleNavaleView mmv = new BatailleNavaleView(new Game(era.shipCreation(),era.shipCreation()));
        primaryStage.setScene(new Scene(mmv.getView()));
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }
}
