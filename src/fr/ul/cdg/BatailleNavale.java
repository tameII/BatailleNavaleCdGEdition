package fr.ul.cdg;

import fr.ul.cdg.factory.renaissance.EraRenaissance;
import fr.ul.cdg.model.Game;
import fr.ul.cdg.mv.view.BatailleNavaleView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BatailleNavale extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //TODO : Allow a choice of era at the start
        EraRenaissance era = new EraRenaissance();
        BatailleNavaleView bnv = new BatailleNavaleView(new Game(era.shipCreation(),era.shipCreation()));
        primaryStage.setScene(new Scene(bnv.getView()));
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }
}
