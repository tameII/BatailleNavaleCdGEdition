package sample;

import fr.ul.cdg.factory.Era;
import fr.ul.cdg.factory.renaissance.EraRenaissance;
import fr.ul.cdg.factory.scifi.EraSciFi;
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


    public static void main(String[] args) {
        Era era = new EraRenaissance();
        System.out.println("Renaissance: "+era.shipCreation());
        era = new EraSciFi();
        System.out.println("SciFi: "+era.shipCreation());
        launch(args);
    }
}
