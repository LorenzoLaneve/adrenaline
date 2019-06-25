package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.DamageBoardView;
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
        Pane playerSlot = (Pane) window.getScene().lookup(".player-slot."+ GUIGameWindowHelper.toStyleClass(color));

        Pane markSigns = (Pane) playerSlot.lookup(".mark-signs");
        Pane dmgSigns = (Pane) playerSlot.lookup(".dmg-signs");

        for (int i = 0; i < damageAmount; i++) {
            dmgSigns.getChildren().add(GUIGameWindowHelper.createDamageIcon(dealer));
        }

        for (int i = 0; i < markAmount; i++) {
            markSigns.getChildren().add(GUIGameWindowHelper.createDamageIcon(dealer));
        }
    }

    @Override
    public void convertMarks(PlayerColor dealer) {
        List<Node> marks = new ArrayList<>();

        Pane playerSlot = (Pane) window.getScene().lookup(".player-slot."+ GUIGameWindowHelper.toStyleClass(dealer));

        Pane markSigns = (Pane) playerSlot.lookup(".mark-signs");
        Pane dmgSigns = (Pane) playerSlot.lookup(".dmg-signs");
        for (Node child : playerSlot.getChildren()) {
            if (child.getStyleClass().contains(GUIGameWindowHelper.toStyleClass(dealer))) {
                marks.add(child);
            }
        }

        markSigns.getChildren().removeAll(marks);
        dmgSigns.getChildren().addAll(marks);
    }

}
