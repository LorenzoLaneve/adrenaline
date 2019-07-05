package it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs;

import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIGameWindow;
import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIHelper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

/**
 * Dialog that asks the user to choose an ammo or power up among the ones passed to the constructor.
 * The choice will be notified to the {@code Listener} set with {@code setListener}.
 */
public class AmmoSelectionDialog implements Dialog {

    @FunctionalInterface
    public interface Listener {

        /**
         * The user made a choice. If the choice is a power up then
         * {@code ammo} is {@code null} and vice versa.
         */
        void ammoChosen(AmmoColor ammo, Integer powerUp);

    }


    private Listener listener;

    private String reason;

    private List<Integer> powerUps;

    private AmmoSet playerInv;


    /**
     * Creates an ammo selection dialog making the user choose a resource among the ones they have,
     * which are specified in {@code playerInv} and {@code powerUps}.
     * @param reason A user-readable string explaining why this choice is prompted.
     */
    public AmmoSelectionDialog(AmmoSet playerInv, List<Integer> powerUps, String reason) {
        this.playerInv = new AmmoSet(playerInv.getRedAmmos(), playerInv.getYellowAmmos(), playerInv.getBlueAmmos());
        this.powerUps = new ArrayList<>(powerUps);
        this.reason = reason;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }


    private StackPane createStackPane(int i) {
        int cardID = powerUps.get(i);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(GUIHelper.createPowerUpCardPane(cardID));
        stackPane.getChildren().add(new Pane());

        stackPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            listener.ammoChosen(null, cardID);
        });

        return stackPane;
    }

    @Override
    public Node createDialogPane(GUIGameWindow window) {
        try {
            Pane dialogPane = FXMLLoader.load(getClass().getResource("/gui/ammo-selection.fxml"));

            Label title = (Label) dialogPane.lookup(".overlay-dialog-title");
            title.setText("Choose an ammo to pay for "+ reason);

            Pane powerUpsViewer = (Pane) dialogPane.lookup("#powerUpsViewer");
            for (int i = 0; i < powerUps.size(); i++) {
                powerUpsViewer.getChildren().add(createStackPane(i));
            }

            Label redAmmosLabel = (Label) dialogPane.lookup("#redChoice");
            if (playerInv.getRedAmmos() <= 0) {
                redAmmosLabel.setVisible(false);
            } else {
                redAmmosLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    listener.ammoChosen(AmmoColor.RED, null);
                });
            }

            Label blueAmmosLabel = (Label) dialogPane.lookup("#blueChoice");
            if (playerInv.getBlueAmmos() <= 0) {
                blueAmmosLabel.setVisible(false);
            } else {
                blueAmmosLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    listener.ammoChosen(AmmoColor.BLUE, null);
                });
            }

            Label yellowAmmosLabel = (Label) dialogPane.lookup("#yellowChoice");
            if (playerInv.getYellowAmmos() <= 0) {
                yellowAmmosLabel.setVisible(false);
            } else {
                yellowAmmosLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    listener.ammoChosen(AmmoColor.YELLOW, null);
                });
            }

            Button cancelButton = (Button) dialogPane.lookup(".cancel-button");
            cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                listener.ammoChosen(null, null);
            });

            return dialogPane;
        } catch (Exception x) {
            return null;
        }
    }

}