package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.view.ConnectionView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIConnectionWindow {

    private GUIConnectionPromptReceiver receiver;

    private Stage stage;

    private Scene scene;


    public GUIConnectionWindow(GUIConnectionPromptReceiver receiver) {
        this.receiver = receiver;
    }

    private void setupStage() {
        this.stage = new Stage();
        this.stage.setTitle("Connect to Server...");

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/connect.fxml"));
            this.scene = new Scene(root, 500, 300);

            this.stage.setScene(scene);
            this.stage.setResizable(false);

            ToggleGroup connectionTypeGroup = new ToggleGroup();

            ToggleButton socketButton = (ToggleButton) scene.lookup("#socketButton");
            socketButton.setToggleGroup(connectionTypeGroup);

            ToggleButton rmiButton = (ToggleButton) scene.lookup("#rmiButton");
            rmiButton.setToggleGroup(connectionTypeGroup);

            Button submitButton = (Button) scene.lookup("#submitButton");
            submitButton.setDefaultButton(true);
            submitButton.setOnAction(this::sendConnectionData);

            connectionTypeGroup.selectedToggleProperty().addListener((observer, oldToggle, newToggle) -> {
                if (newToggle == null)
                    oldToggle.setSelected(true);
            });
        } catch (IOException x) {
            // nothing to do here.
        }
    }

    public void showError(String message) {
        Label errorLabel = (Label) scene.lookup("#errorLabel");
        errorLabel.setText(message);
    }

    public void show() {
        if (stage == null) {
            setupStage();
        }
        stage.show();
        Platform.setImplicitExit(true);
    }

    public void hide() {
        Platform.setImplicitExit(false);
        this.stage.hide();
    }

    private void sendConnectionData(ActionEvent actionEvent) {
        TextField hostField = (TextField) scene.lookup("#hostField");
        TextField portField = (TextField) scene.lookup("#portField");

        Label errorLabel = (Label) scene.lookup("#errorLabel");
        errorLabel.setText("");

        String host = hostField.getText();

        int port;
        try {
            port = Integer.valueOf(portField.getText());
        } catch (NumberFormatException x) {
            errorLabel.setText("Server port must be a valid port.");
            return;
        }


        ToggleButton rmiButton = (ToggleButton) scene.lookup("#rmiButton");

        ConnectionView.ConnectionType connectionType = ConnectionView.ConnectionType.SOCKETS;
        if (rmiButton.isSelected()) {
            connectionType = ConnectionView.ConnectionType.RMI;
        }

        receiver.deliverData(host, port, connectionType);
    }

}
