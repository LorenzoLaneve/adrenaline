package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.DamageBoardView;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class GUIDamageBoardView implements DamageBoardView {

    private GUIGameWindow window;

    private PlayerColor color;

    public GUIDamageBoardView(GUIGameWindow window, PlayerColor color) {
        this.window = window;
        this.color = color;
    }


    @Override
    public void registerDamage(int damageAmount, int markAmount, PlayerColor dealer) {
        Platform.runLater(() -> {
            Pane playerSlot = (Pane) window.getScene().lookup(".player-slot."+ GUIGameWindowHelper.toStyleClass(color));

            Pane markSigns = (Pane) playerSlot.lookup(".mark-signs");
            Pane dmgSigns = (Pane) playerSlot.lookup(".dmg-signs");

            for (int i = 0; i < damageAmount; i++) {
                dmgSigns.getChildren().add(GUIGameWindowHelper.createDamageIcon(dealer));
            }

            for (int i = 0; i < markAmount; i++) {
                markSigns.getChildren().add(GUIGameWindowHelper.createDamageIcon(dealer));
            }
        });
    }

    @Override
    public void convertMarks(PlayerColor dealer) {
        Platform.runLater(() -> {
            List<Node> marks = new ArrayList<>();

            Pane playerSlot = (Pane) window.getScene().lookup(".player-slot."+ GUIGameWindowHelper.toStyleClass(color));

            Pane markSigns = (Pane) playerSlot.lookup(".mark-signs");
            Pane dmgSigns = (Pane) playerSlot.lookup(".dmg-signs");
            for (Node child : markSigns.getChildren()) {
                if (child.getStyleClass().contains(GUIGameWindowHelper.toStyleClass(dealer))) {
                    marks.add(child);
                }
            }

            markSigns.getChildren().removeAll(marks);
            dmgSigns.getChildren().addAll(marks);
        });
    }

    @Override
    public void popDamage() {
        Platform.runLater(() -> {
            Pane playerSlot = (Pane) window.getScene().lookup(".player-slot."+ GUIGameWindowHelper.toStyleClass(color));

            Pane dmgSigns = (Pane) playerSlot.lookup(".dmg-signs");
            dmgSigns.getChildren().remove(dmgSigns.getChildren().size() - 1);
        });
    }

    @Override
    public void goFrenzy() {
        Platform.runLater(() -> {
            Pane damageBoardPane = (Pane) window.getScene().lookup(".player-slot."+ GUIGameWindowHelper.toStyleClass(color) +" .damage-board");
            damageBoardPane.getStyleClass().add("frenzy");
        });
    }

    @Override
    public void clearBoard() {
        Platform.runLater(() -> {
            Pane playerSlot = (Pane) window.getScene().lookup(".player-slot."+ GUIGameWindowHelper.toStyleClass(color));

            Pane dmgSigns = (Pane) playerSlot.lookup(".dmg-signs");
            dmgSigns.getChildren().clear();

            Pane markSigns = (Pane) playerSlot.lookup(".mark-signs");
            markSigns.getChildren().clear();
        });
    }

}
