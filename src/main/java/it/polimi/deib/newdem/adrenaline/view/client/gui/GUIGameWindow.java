package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs.Dialog;
import it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs.DisconnectionDialog;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIGameWindow {

    private Stage stage;

    private Scene scene;


    public GUIGameWindow() {
        Platform.runLater(this::setupStage);
    }

    private synchronized void setupStage() {
        this.stage = new Stage();
        this.stage.setTitle("Adrenaline");

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/game.fxml"));
            this.scene = new Scene(root, 700, 500);

            this.stage.setScene(scene);
            this.stage.setResizable(true);
            this.stage.setFullScreen(true);

            notifyAll();
        } catch (IOException x) {
            // nothing to do here.
        }
    }

    public synchronized void runLater(Runnable runnable) {
        try {
            while (stage == null) wait();

            Platform.runLater(runnable);
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
        }
    }

    Scene getScene() {
        return scene;
    }

    public void show() {
        stage.show();
        Platform.setImplicitExit(true);
    }


    public void showDialog(Dialog dialog) {
        StackPane overlayPane = (StackPane) scene.lookup("#overlay-pane");

        StackPane overlayBackground = new StackPane();
        overlayBackground.getStyleClass().add("overlay-pane");

        Node pane = dialog.createDialogPane(this);

        overlayBackground.getChildren().add(pane);

        Node closeButton = pane.lookup(".close-button");
        if (closeButton != null) {
            closeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                overlayPane.getChildren().remove(overlayBackground);
            });
        }

        overlayPane.getChildren().add(overlayBackground);
    }

    public void closeDialog() {
        StackPane overlayPane = (StackPane) scene.lookup("#overlay-pane");
        if (overlayPane.getChildren().size() > 1) {
            overlayPane.getChildren().remove(overlayPane.getChildren().size() - 1);
        }
    }

    public void notifyDisconnection() {
        showDialog(new DisconnectionDialog());
    }
}
