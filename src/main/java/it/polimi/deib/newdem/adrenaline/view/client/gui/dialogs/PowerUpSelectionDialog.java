package it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs;

import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIGameWindow;
import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIHelper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 * Dialog that makes the user choose among the power ups whose IDs are passed to the constructor.
 * The choice will be notified to the {@code Listener} object passed to the constructor.
 */
public class PowerUpSelectionDialog implements Dialog {

    @FunctionalInterface
    public interface Listener {

        void powerUpChosen(Integer cardID);

    }

    private List<Integer> powerUps;

    private Listener listener;


    public PowerUpSelectionDialog(Listener listener, List<Integer> powerUps) {
        this.powerUps = new ArrayList<>(powerUps);
        this.listener = listener;
    }

    @Override
    public Node createDialogPane(GUIGameWindow window) {
        try {
            Pane dialogPane = FXMLLoader.load(getClass().getResource("/gui/powerup-selection.fxml"));

            Pane powerUpsViewer = (Pane) dialogPane.lookup("#powerUpsViewer");
            for (Integer cardID : powerUps) {
                Group cardPane = GUIHelper.createPowerUpCardPane(cardID);
                cardPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> listener.powerUpChosen(cardID));

                powerUpsViewer.getChildren().add(cardPane);
            }

            Button endTurnButton = (Button) dialogPane.lookup(".no-card-button");
            endTurnButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> listener.powerUpChosen(null));

            return dialogPane;
        } catch (Exception x) {
            return null;
        }
    }

}
