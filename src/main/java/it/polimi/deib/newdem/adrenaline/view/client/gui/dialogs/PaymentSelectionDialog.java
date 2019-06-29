package it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIGameWindow;
import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIGameWindowHelper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class PaymentSelectionDialog implements Dialog {

    private PaymentInvoice invoice;

    private String reason;

    private List<Integer> powerUps;

    private List<Integer> selectedPowerUps;

    private List<AmmoColor> equivalentAmmos;

    private AmmoSet playerInv;

    private EnumMap<AmmoColor, Label> receiptLabels;

    private Label powerupReceipt;


    public PaymentSelectionDialog(PaymentInvoice invoice, AmmoSet playerInv, List<Integer> powerUps, List<AmmoColor> equivalentAmmos, String reason) {
        this.invoice = invoice;
        this.playerInv = new AmmoSet(playerInv.getRedAmmos(), playerInv.getYellowAmmos(), playerInv.getBlueAmmos());
        this.powerUps = new ArrayList<>(powerUps);
        this.selectedPowerUps = new ArrayList<>();
        this.equivalentAmmos = new ArrayList<>(equivalentAmmos);
        this.receiptLabels = new EnumMap<>(AmmoColor.class);
        this.reason = reason;
    }


    private StackPane createStackPane(int i) {
        int cardID = powerUps.get(i);
        AmmoColor equivalentAmmo = equivalentAmmos.get(i);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(GUIGameWindowHelper.createPowerUpCardPane(cardID));
        stackPane.getChildren().add(new Pane());

        stackPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Label incLabel = receiptLabels.get(equivalentAmmo);
            if (!stackPane.getStyleClass().contains("selected")) {
                int requiredAmmos = Integer.valueOf(incLabel.getText());
                if (requiredAmmos > 0) {
                    stackPane.getStyleClass().add("selected");

                    incLabel.setText(""+ (requiredAmmos - 1));
                    powerupReceipt.setText(""+ (Integer.valueOf(powerupReceipt.getText()) + 1));

                    selectedPowerUps.add(cardID);
                }
            } else {
                stackPane.getStyleClass().remove("selected");

                incLabel.setText(""+ (Integer.valueOf(incLabel.getText()) + 1));
                powerupReceipt.setText(""+ (Integer.valueOf(powerupReceipt.getText()) - 1));

                selectedPowerUps.remove((Integer)cardID);
            }
        });

        return stackPane;
    }

    @Override
    public Node createDialogPane(GUIGameWindow window) {
        try {
            Pane dialogPane = FXMLLoader.load(getClass().getResource("/gui/payment-dialog.fxml"));

            Label title = (Label) dialogPane.lookup(".overlay-dialog-title");
            title.setText("Choose how to pay for "+ reason);

            Pane powerUpsViewer = (Pane) dialogPane.lookup("#powerUpsViewer");
            for (int i = 0; i < powerUps.size(); i++) {
                powerUpsViewer.getChildren().add(createStackPane(i));
            }

            Label redAmmosLabel = (Label) dialogPane.lookup("#redInvoice");
            Label blueAmmosLabel = (Label) dialogPane.lookup("#blueInvoice");
            Label yellowAmmosLabel = (Label) dialogPane.lookup("#yellowInvoice");

            redAmmosLabel.setText(""+ invoice.getRedAmmos());
            blueAmmosLabel.setText(""+ invoice.getBlueAmmos());
            yellowAmmosLabel.setText(""+ invoice.getYellowAmmos());

            redAmmosLabel = (Label) dialogPane.lookup("#redReceipt");
            blueAmmosLabel = (Label) dialogPane.lookup("#blueReceipt");
            yellowAmmosLabel = (Label) dialogPane.lookup("#yellowReceipt");
            powerupReceipt = (Label) dialogPane.lookup("#powerUpReceipt");

            redAmmosLabel.setText(""+ invoice.getRedAmmos());
            blueAmmosLabel.setText(""+ invoice.getBlueAmmos());
            yellowAmmosLabel.setText(""+ invoice.getYellowAmmos());

            receiptLabels.put(AmmoColor.RED, redAmmosLabel);
            receiptLabels.put(AmmoColor.BLUE, blueAmmosLabel);
            receiptLabels.put(AmmoColor.YELLOW, yellowAmmosLabel);

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
