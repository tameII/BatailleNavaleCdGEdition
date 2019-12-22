package fr.ul.cdg.mv.controller;

import fr.ul.cdg.factory.Era;
import fr.ul.cdg.factory.armada.EraArmada;
import fr.ul.cdg.factory.renaissance.EraRenaissance;
import fr.ul.cdg.factory.scifi.EraSciFi;
import fr.ul.cdg.model.Game;
import fr.ul.cdg.mv.view.BatailleNavaleView;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Observable;

public class MainMenuController implements Controllers {

    @FXML
    private Button renaissanceEra;

    @FXML
    private Button scifiEra;

    @FXML
    private Button armadaEra;

    @FXML
    private CheckBox rmiCheckbox;

    public void initialize(){
        renaissanceEra.setOnAction(event -> {
            createNewStage(new EraRenaissance());
        });

        scifiEra.setOnAction(event -> {
            createNewStage(new EraSciFi());
        });
        armadaEra.setOnAction(event -> {
            createNewStage(new EraArmada());
        });
    }

    private void createNewStage(Era era){
        Stage stage = new Stage();
        BatailleNavaleView bnv = new BatailleNavaleView(new Game(era.shipCreation(),era.shipCreation()));
        stage.setScene(new Scene(bnv.getView()));
        stage.setResizable(false);
        stage.show();
        stage.setWidth(611); //dont even ask about these
        stage.setHeight(569);
    }

    @Override
    public void initData(Object o) {
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
