package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.DamageBoardView;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GUIDamageBoardView implements DamageBoardView {

    private GUIGameWindow window;

    private PlayerColor color;

    public GUIDamageBoardView(GUIGameWindow window, PlayerColor color) {
        this.window = window;
        this.color = color;
    }


    private void handleMarks(int markAmount, PlayerColor dealer) {
        Pane playerSlot = (Pane) window.getScene().lookup(".player-slot."+ GUIHelper.toStyleClass(color));
        Pane markSigns = (Pane) playerSlot.lookup(".mark-signs");
        if (markAmount >= 0) {
            for (int i = 0; i < markAmount; i++) {
                markSigns.getChildren().add(GUIHelper.createDamageIcon(dealer));
            }
        } else {
            List<Node> marks = new ArrayList<>();
            List<Node> children = new ArrayList<>(markSigns.getChildren());
            Collections.reverse(children);
            for (Node child : children) {
                if (child.getStyleClass().contains(GUIHelper.toStyleClass(dealer))) {
                    marks.add(child);
                    if (marks.size() >= -markAmount) break;
                }
            }
            markSigns.getChildren().removeAll(marks);
        }
    }


    @Override
    public void registerDamage(int damageAmount, int markAmount, PlayerColor dealer) {
        Platform.runLater(() -> {
            Pane playerSlot = (Pane) window.getScene().lookup(".player-slot."+ GUIHelper.toStyleClass(color));

            Pane dmgSigns = (Pane) playerSlot.lookup(".dmg-signs");
            for (int i = 0; i < damageAmount; i++) {
                dmgSigns.getChildren().add(GUIHelper.createDamageIcon(dealer));
            }

            handleMarks(markAmount, dealer);
        });
    }

    @Override
    public void popDamage() {
        Platform.runLater(() -> {
            Pane playerSlot = (Pane) window.getScene().lookup(".player-slot."+ GUIHelper.toStyleClass(color));

            Pane dmgSigns = (Pane) playerSlot.lookup(".dmg-signs");
            dmgSigns.getChildren().remove(dmgSigns.getChildren().size() - 1);
        });
    }

    @Override
    public void goFrenzy() {
        Platform.runLater(() -> {
            Pane damageBoardPane = (Pane) window.getScene().lookup(".player-slot."+ GUIHelper.toStyleClass(color) +" .damage-board");
            damageBoardPane.getStyleClass().add("frenzy");
        });
    }

    @Override
    public void clearBoard() {
        Platform.runLater(() -> {
            Pane playerSlot = (Pane) window.getScene().lookup(".player-slot."+ GUIHelper.toStyleClass(color));

            Pane dmgSigns = (Pane) playerSlot.lookup(".dmg-signs");
            dmgSigns.getChildren().clear();
        });
    }

}
