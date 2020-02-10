package net.malkkis.portfolio;


import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class StartWindow {

    private Stage stage = null;

    @FXML
    private AnchorPane main;

    @FXML
    private void showAboutWindow(MouseEvent event) {

        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("About: Chat-JavaFX");
        about.setHeaderText("Chat-JavaFX, version 1.0");
        about.setContentText("Author: Mikko Malkavaara\n" +
                "10.2.2020");
        about.showAndWait();

    }

    @FXML
    private void openGithub(ActionEvent actionEvent) {
        //this was actually bit more convoluted than I thought
        //nicked this off from stackoverflow: https://stackoverflow.com/a/34182115
        HostServices hostServices = (HostServices) this.getStage().getProperties().get("hostServices");
        hostServices.showDocument("http://www.github.com/mmmalk?tab=repositories");

    }

    @FXML
    private void quit(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public Stage getStage(){
        if(this.stage == null){
            this.stage = (Stage) this.main.getScene().getWindow();
        }
        return stage;
    }

}
