package it.polimi.deib.newdem.adrenaline.view.client.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GUILobbyWindow {

    private GUIUsernamePromptReceiver usernameReceiver;

    private Stage stage;

    private Scene scene;


    public GUILobbyWindow(GUIUsernamePromptReceiver usernameReceiver) {
        this.usernameReceiver = usernameReceiver;
    }

    private void setupStage() {
        this.stage = new Stage();
        this.stage.setTitle("Adrenaline");

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/lobby.fxml"));
            this.scene = new Scene(root, 700, 500);

            this.stage.setScene(scene);
            this.stage.setResizable(false);

            Pane contentPane = (Pane) scene.lookup("#contentPane");
            contentPane.getChildren().add(FXMLLoader.load(getClass().getResource("/gui/username-form.fxml")));

            Button submitButton = (Button) scene.lookup("#usernameSubmit");
            submitButton.setDefaultButton(true);
            submitButton.setOnAction(this::sendUsername);

        } catch (IOException x) {
            // nothing to do here.
        }
    }

    private void sendUsername(ActionEvent actionEvent) {
        TextField usernameField = (TextField) scene.lookup("#usernameField");

        usernameReceiver.deliverUsername(usernameField.getText());
    }

    public void showUsernameError(String message) {
        Label errorLabel = (Label) scene.lookup("#errorLabel");
        errorLabel.setText(message);
    }


    private Label getUserSlot(int slotIndex) {
        return (Label) scene.lookup("#userSlot"+ slotIndex);
    }

    public void addUser(String name) {
        Label userSlot;
        for (int i = 1; i <= 5; i++) {
            userSlot = getUserSlot(i);

            if (!userSlot.getStyleClass().contains("online-user")) {
                userSlot.getStyleClass().add("online-user");

                userSlot.setText(name);
                break;
            }
        }
    }

    public void removeUser(String name) {
        Label userSlot;
        for (int i = 1; i <= 5; i++) {
            userSlot = getUserSlot(i);

            if (userSlot.getStyleClass().contains("online-user") && userSlot.getText().equals(name)) {
                userSlot.getStyleClass().remove("online-user");

                userSlot.setText("Waiting for players...");
                break;
            }
        }
    }

    public void startTimer(int seconds) {
        Label lobbyStateLabel = (Label) scene.lookup("#lobbyState");
        lobbyStateLabel.setText("Enough players. Starting in");

        syncTimer(seconds);
    }

    public void syncTimer(int seconds) {
        Label lobbyTimerLabel = (Label) scene.lookup("#lobbyTimer");
        lobbyTimerLabel.setText(""+ seconds);
    }

    public void abortTimer() {
        Label lobbyStateLabel = (Label) scene.lookup("#lobbyState");
        lobbyStateLabel.setText("Waiting for three players");

        Label lobbyTimerLabel = (Label) scene.lookup("#lobbyTimer");
        lobbyTimerLabel.setText("");
    }

    public void closeUsernameForm() {
        Pane contentPane = (Pane) scene.lookup("#contentPane");
        contentPane.getChildren().clear();

        try {
            contentPane.getChildren().add(FXMLLoader.load(getClass().getResource("/gui/lobby-state.fxml")));
        } catch (IOException x) {
            // nothing to do here.
        }
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

    public void notifyDisconnection() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Adrenaline");
        alert.setHeaderText(null);
        alert.setContentText("Connection to server lost.");

        alert.showAndWait();

        Platform.exit();
    }
}
