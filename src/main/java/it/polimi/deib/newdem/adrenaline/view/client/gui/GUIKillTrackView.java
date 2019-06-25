package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.KillTrackView;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

public class GUIKillTrackView implements KillTrackView {

    private GUIGameWindow window;

    private int currentSlot;

    public GUIKillTrackView(GUIGameWindow window) {
        this.window = window;

        this.currentSlot = 1;
    }

    @Override
    public void registerKill(PlayerColor pColor, int amount) {
        Platform.runLater(() -> {
            Pane slot = (Pane) window.getScene().lookup("#killTrackSlot"+ currentSlot++);

            for (int i = 0; i < amount; i++) {
                slot.getChildren().add(GUIGameWindowHelper.createDamageIcon(pColor));
            }
        });
    }

    @Override
    public void goFrenzy() {
        // nothing to do here...
    }

}
