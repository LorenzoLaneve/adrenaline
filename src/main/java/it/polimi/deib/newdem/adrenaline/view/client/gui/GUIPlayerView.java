package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerData;
import it.polimi.deib.newdem.adrenaline.view.PlayerView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GUIPlayerView implements PlayerView {

    private GUIGameWindow window;

    private PlayerColor color;

    private FlowPane weaponsPane;

    private FlowPane powerUpsPane;

    private Pane playerPane;


    public GUIPlayerView(GUIGameWindow window, PlayerColor color) {
        this.window = window;
        this.color = color;

        this.weaponsPane = new FlowPane();
        this.weaponsPane.setHgap(10);
        this.weaponsPane.setAlignment(Pos.CENTER);

        this.powerUpsPane = new FlowPane();
        this.powerUpsPane.setHgap(10);
        this.powerUpsPane.setAlignment(Pos.CENTER);

        Platform.runLater(this::setupPane);
    }

    private void setupPane() {
        window.getScene().getRoot().applyCss();
        Pane playerSlots = (Pane) window.getScene().lookup("#playerSlots");

        try {
            Pane playerSlot = FXMLLoader.load(getClass().getResource("/gui/player-view.fxml"));
            playerSlot.getStyleClass().add(GUIGameWindowHelper.toStyleClass(color));

            playerSlots.getChildren().add(playerSlot);
        } catch (IOException x) {
            // nothing to do here.
        }
    }

    private Pane getPlayerPane() {
        return (Pane) window.getScene().lookup(".player-slot."+ GUIGameWindowHelper.toStyleClass(color));
    }


    @Override
    public void setPlayerData(PlayerData data) {
        Platform.runLater(() -> {
            // TODO
        });
    }

    @Override
    public void setName(String name) {
        Platform.runLater(() -> {
            Label playerName = (Label) getPlayerPane().lookup(".player-name");
            playerName.setText(name);
        });
    }

    @Override
    public void takeControl() {
        Platform.runLater(() -> {
            Pane playerSlots = (Pane) window.getScene().lookup("#playerSlots");

            for (Node child : playerSlots.getChildren()) {
                Label status = (Label) child.lookup(".status");

                if (child.getStyleClass().contains(GUIGameWindowHelper.toStyleClass(color))) {
                    status.setText("Their turn");
                } else if (!child.getStyleClass().contains("offline")) {
                    status.setText("");
                }
            }
        });
    }

    @Override
    public void setScore(int score) {
        Platform.runLater(() -> {
            Label scoreLabel = (Label) getPlayerPane().lookup(".score-amt");
            scoreLabel.setText(""+ score);
        });
    }

    @Override
    public void addPowerUpCard(int cardID) {
        Platform.runLater(() -> {
            Label powerUpsLabel = (Label) getPlayerPane().lookup(".ammo-amt.powerup");
            powerUpsLabel.setText(""+ (Integer.valueOf(powerUpsLabel.getText()) + 1));

            powerUpsPane.getChildren().add(GUIGameWindowHelper.createPowerUpCardPane(cardID));
        });
    }

    @Override
    public void removePowerUpCard(int cardID) {
        Platform.runLater(() -> {
            Label powerUpsLabel = (Label) getPlayerPane().lookup(".ammo-amt.powerup");
            powerUpsLabel.setText(""+ (Integer.valueOf(powerUpsLabel.getText()) - 1));

            if (cardID < 0) {
                powerUpsPane.getChildren().remove(powerUpsPane.lookup(".equipPowerUpHidden"));
            } else {
                powerUpsPane.getChildren().remove(powerUpsPane.lookup(".equipPowerUp"+ cardID));
            }
        });
    }

    @Override
    public void addWeaponCard(int cardID) {
        Platform.runLater(() -> {
            weaponsPane.getChildren().add(GUIGameWindowHelper.createWeaponCardPane(cardID));
        });
    }

    @Override
    public void removeWeaponCard(int cardID) {
        Platform.runLater(() -> {
            powerUpsPane.getChildren().remove(powerUpsPane.lookup(".equipWeapon"+ cardID));
        });
    }

    @Override
    public void addAmmoSet(int yellowAmount, int redAmount, int blueAmount) {
        Platform.runLater(() -> {
            Label redAmmoLabel = (Label) getPlayerPane().lookup(".ammo-amt.red");
            redAmmoLabel.setText(""+ (Integer.valueOf(redAmmoLabel.getText()) + redAmount));

            Label blueAmmoLabel = (Label) getPlayerPane().lookup(".ammo-amt.blue");
            blueAmmoLabel.setText(""+ (Integer.valueOf(blueAmmoLabel.getText()) + blueAmount));

            Label yellowAmmoLabel = (Label) getPlayerPane().lookup(".ammo-amt.yellow");
            yellowAmmoLabel.setText(""+ (Integer.valueOf(yellowAmmoLabel.getText()) + yellowAmount));
        });
    }

    @Override
    public void removeAmmoSet(int yellowAmount, int redAmount, int blueAmount) {
        Platform.runLater(() -> {
            Label redAmmoLabel = (Label) getPlayerPane().lookup(".ammo-amt.red");
            redAmmoLabel.setText(""+ (Integer.valueOf(redAmmoLabel.getText()) - redAmount));

            Label blueAmmoLabel = (Label) getPlayerPane().lookup(".ammo-amt.blue");
            blueAmmoLabel.setText(""+ (Integer.valueOf(blueAmmoLabel.getText()) - blueAmount));

            Label yellowAmmoLabel = (Label) getPlayerPane().lookup(".ammo-amt.yellow");
            yellowAmmoLabel.setText(""+ (Integer.valueOf(yellowAmmoLabel.getText()) - yellowAmount));
        });
    }

    @Override
    public void reloadWeaponCard(int cardID) {
        Platform.runLater(() -> {
            Group weaponPane = (Group) weaponsPane.lookup(".equipWeapon"+ cardID);
            weaponPane.getStyleClass().remove("needs-reload");
        });
    }

    @Override
    public void unloadWeaponCard(int cardID) {
        Platform.runLater(() -> {
            Group weaponPane = (Group) weaponsPane.lookup(".equipWeapon"+ cardID);
            weaponPane.getStyleClass().add("needs-reload");
        });
    }

}
