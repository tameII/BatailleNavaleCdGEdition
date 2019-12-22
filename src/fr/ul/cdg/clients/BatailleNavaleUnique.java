package fr.ul.cdg.clients;

import fr.ul.cdg.factory.Era;
import fr.ul.cdg.factory.renaissance.EraRenaissance;
import fr.ul.cdg.model.Game;
import fr.ul.cdg.mv.view.BatailleNavaleView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BatailleNavaleUnique extends Application {

    /**
     * Create a unique instance of BatailleNavale in the Renaissance
     * No rmi can be used here
     * @param primaryStage the primaryStage
     * @throws Exception if something bad occur
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Era era = new EraRenaissance();
        primaryStage.initStyle(StageStyle.DECORATED);
        BatailleNavaleView mmv = new BatailleNavaleView(new Game(era.shipCreation(),era.shipCreation(), false));
        primaryStage.setScene(new Scene(mmv.getView()));
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }
}
