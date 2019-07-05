package it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs;

import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIGameWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * Popup window showing the weapons and power up cards owned by a user.
 */
public class PlayerEquipDialog implements Dialog {

    private String name;

    private FlowPane weapons;
    private FlowPane powerUps;

    /**
     * Create a new PlayerEquipDialog
     * @param name The name of the player we are inspecting.
     * @param weapons The FlowPane object containing the image views of the owned weapon cards.
     * @param powerUps The FlowPane object containing the image views of the owned power ups.
     * @implNote weapons and powerUps panes are mantained by the GUI player views.
     */
    public PlayerEquipDialog(String name, FlowPane weapons, FlowPane powerUps) {
        this.name = name;
        this.weapons = weapons;
        this.powerUps = powerUps;
    }

    @Override
    public Node createDialogPane(GUIGameWindow window) {
        try {
            Pane dialogPane = FXMLLoader.load(getClass().getResource("/gui/player-equip.fxml"));

            Label title = (Label) dialogPane.lookup(".overlay-dialog-title");
            title.setText(name +"'s Equipment");

            Pane weaponViewer = (Pane) dialogPane.lookup("#weaponCardsViewer");
            weaponViewer.getChildren().add(weapons);

            Pane powerUpsViewer = (Pane) dialogPane.lookup("#powerUpsViewer");
            powerUpsViewer.getChildren().add(powerUps);

            return dialogPane;
        } catch (Exception x) {
            return null;
        }
    }

}
