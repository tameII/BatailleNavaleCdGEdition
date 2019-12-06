package sample;

import fr.ul.cdg.factory.Era;
import fr.ul.cdg.factory.renaissance.EraRenaissance;
import fr.ul.cdg.factory.scifi.EraSciFi;
import fr.ul.cdg.model.Board;
import fr.ul.cdg.model.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args){
        Era era = new EraRenaissance();
        Game g= new Game(era.shipCreation(), era.shipCreation());
        for(int i = 0; i < 20000; i++) {
             g = new Game(era.shipCreation(), era.shipCreation());
        }
        for(int i = 0; i < 5; i++){
            g.nextShotAI();
        }
        System.out.println(g.getAiBoard().printCells());
        System.out.println("/////");
        System.out.println(g.getPlayerBoard().printCells());
        //era = new EraSciFi();
        //System.out.println("SciFi: "+era.shipCreation());
        launch(args);
    }
}
