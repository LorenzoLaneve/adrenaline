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
 * Dialog that makes the user choose among the weapons whose IDs are passed to the constructor.
 * The choice will be notified to the {@code Listener} object passed to the constructor.
 */
public class WeaponSelectionDialog implements Dialog {

    @FunctionalInterface
    public interface Listener {

        void weaponChosen(Integer cardID);

    }

    private List<Integer> weapons;

    private Listener listener;


    public WeaponSelectionDialog(Listener listener, List<Integer> weapons) {
        this.weapons = new ArrayList<>(weapons);
        this.listener = listener;
    }

    @Override
    public Node createDialogPane(GUIGameWindow window) {
        try {
            Pane dialogPane = FXMLLoader.load(getClass().getResource("/gui/weapon-selection.fxml"));

            Pane powerUpsViewer = (Pane) dialogPane.lookup("#weaponsViewer");
            for (Integer cardID : weapons) {
                Group cardPane = GUIHelper.createWeaponCardPane(cardID);
                cardPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> listener.weaponChosen(cardID));

                powerUpsViewer.getChildren().add(cardPane);
            }

            Button endTurnButton = (Button) dialogPane.lookup(".no-card-button");
            endTurnButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> listener.weaponChosen(null));

            return dialogPane;
        } catch (Exception x) {
            return null;
        }
    }

}
