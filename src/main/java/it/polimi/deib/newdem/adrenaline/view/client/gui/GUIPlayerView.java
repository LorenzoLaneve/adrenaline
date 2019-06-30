package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerData;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.view.PlayerView;
import it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs.PlayerEquipDialog;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.util.Map;

public class GUIPlayerView implements PlayerView {

    private GUIGameWindow window;

    private PlayerColor color;

    private FlowPane weaponsPane;

    private FlowPane powerUpsPane;


    public GUIPlayerView(GUIGameWindow window, PlayerColor color) {
        this.window = window;
        this.color = color;

        this.weaponsPane = new FlowPane();
        this.weaponsPane.setHgap(10);
        this.weaponsPane.setAlignment(Pos.CENTER);

        this.powerUpsPane = new FlowPane();
        this.powerUpsPane.setHgap(10);
        this.powerUpsPane.setAlignment(Pos.CENTER);
    }

    private Pane getPlayerPane() {
        return (Pane) window.getScene().lookup(".player-slot."+ GUIGameWindowHelper.toStyleClass(color));
    }


    @Override
    public void setPlayerData(PlayerData data) {
        Platform.runLater(() -> {
            Map<AmmoColor, Integer> ammos = data.getAmmos();

            Label redAmmoLabel = (Label) getPlayerPane().lookup(".ammo-amt.red");
            redAmmoLabel.setText(""+ ammos.get(AmmoColor.RED));

            Label blueAmmoLabel = (Label) getPlayerPane().lookup(".ammo-amt.blue");
            blueAmmoLabel.setText(""+ ammos.get(AmmoColor.BLUE));

            Label yellowAmmoLabel = (Label) getPlayerPane().lookup(".ammo-amt.yellow");
            yellowAmmoLabel.setText(""+ ammos.get(AmmoColor.YELLOW));


            for (Integer powerUp : data.getPowerUpCards()) {
                powerUpsPane.getChildren().add(GUIGameWindowHelper.createPowerUpCardPane(powerUp));
            }

            for (Integer weapon : data.getReadyWeaponCards()) {
                weaponsPane.getChildren().add(GUIGameWindowHelper.createWeaponCardPane(weapon));
            }
            for (Integer weapon : data.getUnloadedWeaponCards()) {
                Group cardPane = GUIGameWindowHelper.createWeaponCardPane(weapon);
                cardPane.getStyleClass().add("needs-reload");
            }

            Pane actionBoardPane = (Pane) getPlayerPane().lookup(".action-board");
            try {
                if (data.isActionBoardFrenzy()) {
                    actionBoardPane.getStyleClass().add("frenzy");
                    actionBoardPane.getChildren().add(FXMLLoader.load(getClass().getResource("/gui/action-board-frenzy.fxml")));
                } else {
                    actionBoardPane.getChildren().add(FXMLLoader.load(getClass().getResource("/gui/action-board-normal.fxml")));
                }
            } catch (Exception x) {
                // nothing to do here.
            }

            Pane damageBoardPane = (Pane) getPlayerPane().lookup(".damage-board");
            if (data.isDamageBoardFrenzy()) {
                damageBoardPane.getStyleClass().add("frenzy");
            }

            Pane damagesPane = (Pane) damageBoardPane.lookup(".dmg-signs");
            for (PlayerColor damager : data.getDamages()) {
                damagesPane.getChildren().add(GUIGameWindowHelper.createDamageIcon(damager));
            }

            Pane marksPane = (Pane) damageBoardPane.lookup(".mark-signs");
            for (Map.Entry<PlayerColor, Integer> marks : data.getMarks().entrySet()) {
                for (int i = 0; i < marks.getValue(); i++) {
                    marksPane.getChildren().add(GUIGameWindowHelper.createDamageIcon(marks.getKey()));
                }
            }

            if (data.getPosition() != null) {
                // TODO position, or maybe it should get moves to map view.
            }

            Label scoreLabel = (Label) damageBoardPane.lookup(".score-amt");
            scoreLabel.setText(""+ data.getScore());

            Label nameLabel = (Label) getPlayerPane().lookup(".player-name");
            String name = nameLabel.getText();
            getPlayerPane().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                window.showDialog(new PlayerEquipDialog(name, weaponsPane, powerUpsPane));
            });
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
                Node toRemove = powerUpsPane.lookup(".equipPowerUp"+ cardID);
                if (toRemove == null) {
                    toRemove = powerUpsPane.lookup(".equipPowerUpHidden");
                }

                powerUpsPane.getChildren().remove(toRemove);
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
