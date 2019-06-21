package it.polimi.deib.newdem.adrenaline.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class GameWindowTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Waiting for players...");

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/game.fxml"));
            Scene scene = new Scene(root, 700, 500);

            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.setResizable(true);

            primaryStage.show();

        } catch (IOException x) {
            // nothing to do here.
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
