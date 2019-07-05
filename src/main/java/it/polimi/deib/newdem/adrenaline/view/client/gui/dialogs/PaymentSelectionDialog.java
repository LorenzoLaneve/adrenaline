package it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceiptData;
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
import java.util.EnumMap;
import java.util.List;

/**
 * Dialog that asks the use to choose how to pay, by choosing among the resources that are
 * passed to the constructor. The choice is passed to the {@code Listener} set with {@code setPaymentCallback}.
 */
public class PaymentSelectionDialog implements Dialog {

    @FunctionalInterface
    public interface Listener {

        void paymentChosen(PaymentReceiptData receipt);

    }

    private Listener listener;

    private PaymentInvoice invoice;

    private String reason;

    private List<Integer> powerUps;

    private List<Integer> selectedPowerUps;

    private AmmoSet playerInv;

    private EnumMap<AmmoColor, Label> receiptLabels;

    private Label powerupReceipt;


    private static AmmoColor getEquivalentAmmo(int cardID) {
        switch (cardID % 6) {
            case 0:
            case 1:
                return AmmoColor.BLUE;
            case 2:
            case 3:
                return AmmoColor.RED;
            case 4:
            case 5:
                return AmmoColor.YELLOW;
            default:
                return null;
        }
    }


    public PaymentSelectionDialog(PaymentInvoice invoice, AmmoSet playerInv, List<Integer> powerUps, String reason) {
        this.invoice = invoice;
        this.playerInv = new AmmoSet(playerInv.getRedAmmos(), playerInv.getYellowAmmos(), playerInv.getBlueAmmos());
        this.powerUps = new ArrayList<>(powerUps);
        this.selectedPowerUps = new ArrayList<>();
        this.receiptLabels = new EnumMap<>(AmmoColor.class);
        this.reason = reason;
    }

    public void setPaymentCallback(Listener listener) {
        this.listener = listener;
    }


    private StackPane createStackPane(int i) {
        int cardID = powerUps.get(i);
        AmmoColor equivalentAmmo = getEquivalentAmmo(cardID);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(GUIHelper.createPowerUpCardPane(cardID));
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

            final Label redReceipt = redAmmosLabel;
            final Label blueReceipt = blueAmmosLabel;
            final Label yellowReceipt = yellowAmmosLabel;
            Button payButton = (Button) dialogPane.lookup(".pay-button");
            payButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                boolean ok = true;

                int redAmmos = Integer.valueOf(redReceipt.getText());
                if (redAmmos > playerInv.getRedAmmos()) {
                    redReceipt.setStyle("-fx-fill-color: red;");
                    ok = false;
                } else {
                    redReceipt.setStyle("-fx-fill-color: black;");
                }

                int blueAmmos = Integer.valueOf(blueReceipt.getText());
                if (blueAmmos > playerInv.getBlueAmmos()) {
                    blueReceipt.setStyle("-fx-fill-color: red;");
                    ok = false;
                } else {
                    blueReceipt.setStyle("-fx-fill-color: black;");
                }

                int yellowAmmos = Integer.valueOf(yellowReceipt.getText());
                if (yellowAmmos > playerInv.getYellowAmmos()) {
                    yellowReceipt.setStyle("-fx-fill-color: red;");
                    ok = false;
                } else {
                    yellowReceipt.setStyle("-fx-fill-color: black;");
                }

                if (ok) {
                    listener.paymentChosen(new PaymentReceiptData(redAmmos, blueAmmos, yellowAmmos, selectedPowerUps));
                }
            });

            Button cancelButton = (Button) dialogPane.lookup(".cancel-button");
            cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                listener.paymentChosen(null);
            });

            return dialogPane;
        } catch (Exception x) {
            return null;
        }
    }

}
