package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.GameView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GUIGameView implements GameView {

    private GUIGameWindow window;


    public GUIGameView(GUIGameWindow window) {
        this.window = window;
    }


    @Override
    public void setGameData(GameData data) {
        Platform.runLater(() -> {
            window.getScene().getRoot().applyCss();

            Pane playerSlots = (Pane) window.getScene().lookup("#playerSlots");
            playerSlots.getChildren().clear();

            for (GameData.UserColorPair pair : data.getPlayers()) {
                try {
                    Pane playerSlot = FXMLLoader.load(getClass().getResource("/gui/player-view.fxml"));
                    playerSlot.getStyleClass().add(GUIGameWindowHelper.toStyleClass(pair.getColor()));

                    Label playerName = (Label) playerSlot.lookup(".player-name");
                    playerName.setText(pair.getUsername());

                    playerSlots.getChildren().add(playerSlot);
                } catch (IOException x) {
                    // nothing to do here.
                }
            }

            window.show();
        });
    }

    @Override
    public void disablePlayer(PlayerColor color) {
        Platform.runLater(() -> {
            Label label = (Label) window.getScene().lookup(".player-panel."+ GUIGameWindowHelper.toStyleClass(color) +" .status");
            label.getStyleClass().add("offline");
            label.setText("");
        });
    }

    @Override
    public void enablePlayer(PlayerColor color) {
        Platform.runLater(() -> {
            Label label = (Label) window.getScene().lookup(".player-panel."+ GUIGameWindowHelper.toStyleClass(color) +" .status");
            label.getStyleClass().remove("offline");
            label.setText("Offline");
        });
    }

}
