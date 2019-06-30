package it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs;


import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIGameWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class PlayerEquipDialog implements Dialog {

    private String name;

    private FlowPane weapons;
    private FlowPane powerUps;

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

    @Override
    public void close() {
        // TODO
    }

}
