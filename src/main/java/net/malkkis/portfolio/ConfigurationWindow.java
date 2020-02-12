package net.malkkis.portfolio;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Simple configuration window for client
 * TODO: Make configuration persistent
 */
public class ConfigurationWindow extends AnchorPane {

    private HBox settingsBox = new HBox(16);
    private VBox nameCont = new VBox(16);
    private VBox addrCont = new VBox(16);
    private Label nameLabel = new Label("Username:");
    private Label addrLabel = new Label("Server address:");
    private VBox layoutContainer = new VBox(16);
    private TextField userName= new TextField("username");
    private TextField serverAddr = new TextField("server address");
    private Button okButton = new Button("Ok");


    public ConfigurationWindow() {
        okButton.setOnAction(buttonHandler);

        nameCont.getChildren().addAll(nameLabel, userName);
        addrCont.getChildren().addAll(addrLabel, serverAddr);

        settingsBox.setPrefWidth(400);
        settingsBox.setPadding(new Insets(16, 16, 16, 16));


        layoutContainer.setPrefHeight(200);
        layoutContainer.setAlignment(Pos.CENTER);
        layoutContainer.setPadding(new Insets(16, 16, 16, 16));

        settingsBox.getChildren().addAll(nameCont, addrCont);
        layoutContainer.getChildren().addAll(settingsBox, okButton);
        getChildren().add(layoutContainer);
    }

    /**
     * EventHandler for OK button, passes settings to main chat application
     */
    private EventHandler<ActionEvent> buttonHandler = actionEvent -> {
        //Pass textfield string values to client state
        Main.setClientConfig(userName.getText(), serverAddr.getText());
        //close this window
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    };

}
