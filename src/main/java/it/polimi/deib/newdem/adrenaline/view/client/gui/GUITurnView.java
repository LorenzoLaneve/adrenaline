package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceiptData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.TurnView;
import it.polimi.deib.newdem.adrenaline.view.client.gui.dialogs.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.*;

public class GUITurnView implements TurnView {

    private GUIGameWindow window;

    private List<PlayerColor> playersOnHold;

    private PlayerColor activePlayer;

    private GUILocker<?> currentLocker;


    public GUITurnView(GUIGameWindow window) {
        this.window = window;
        this.playersOnHold = new ArrayList<>();
        this.activePlayer = null;
    }



    private Label getStatusLabel(PlayerColor color) {
        return (Label) window.getScene().lookup(".player-slot."+ GUIGameWindowHelper.toStyleClass(color) +" .status");
    }


    @Override
    public void startTurn(PlayerColor actor) {
        if (activePlayer != null) {
            playersOnHold.add(activePlayer);
        }
        this.activePlayer = actor;

        Platform.runLater(() -> {
            getStatusLabel(actor).setText("It's their turn!");

            for (PlayerColor color : playersOnHold) {
                getStatusLabel(color).setText("Waiting...");
            }
        });
    }

    @Override
    public void endTurn(PlayerColor actor) {
        if (activePlayer == actor) {
            if (!playersOnHold.isEmpty()) {
                this.activePlayer = playersOnHold.remove(playersOnHold.size() - 1);
            }
        } else {
            playersOnHold.remove(actor);
        }

        if (currentLocker != null) {
            currentLocker.interrupt();
        }

        Platform.runLater(() -> {
            Label statusLabel = getStatusLabel(actor);
            if (!statusLabel.getStyleClass().contains("offline")) {
                getStatusLabel(actor).setText(" ");
            }
        });
    }


    @Override
    public ValOrUndo<ActionType> chooseAction(List<ActionType> availableActions) {
        GUILocker<ValOrUndo<ActionType>> locker = new GUILocker<>();
        this.currentLocker = locker;

        Dialog selectionDialog = new ActionSelectionDialog(choice -> {
            locker.deliver(new ValOrUndo<>(choice));
        }, availableActions);

        Platform.runLater(() -> {
            window.showDialog(selectionDialog);
        });

        try {
            return locker.waitForValue();
        } finally {
            Platform.runLater(() -> {
                window.closeDialog();
            });
        }
    }

    @Override
    public ValOrUndo<Integer> chooseWeaponCard(List<Integer> cardIDs) {
        GUILocker<ValOrUndo<Integer>> locker = new GUILocker<>();
        this.currentLocker = locker;

        Dialog selectionDialog = new WeaponSelectionDialog(choice -> {
            locker.deliver(new ValOrUndo<>(choice));
        }, cardIDs);

        Platform.runLater(() -> {
            window.showDialog(selectionDialog);
        });

        try {
            return locker.waitForValue();
        } finally {
            Platform.runLater(() -> {
                window.closeDialog();
            });
        }
    }

    @Override
    public ValOrUndo<Integer> choosePowerUpCard(List<Integer> cardIDs) {
        GUILocker<ValOrUndo<Integer>> locker = new GUILocker<>();
        this.currentLocker = locker;

        Dialog selectionDialog = new PowerUpSelectionDialog(choice -> {
            locker.deliver(new ValOrUndo<>(choice));
        }, cardIDs);

        Platform.runLater(() -> {
            window.showDialog(selectionDialog);
        });

        try {
            return locker.waitForValue();
        } finally {
            Platform.runLater(() -> {
                window.closeDialog();
            });
        }
    }


    private static String metaPlayerToString(MetaPlayer player) {
        switch (player) {
            case RED:
                return "RED";
            case GREEN:
                return "GREEN";
            case YELLOW:
                return "YELLOW";
            case BLUE:
                return "BLUE";
            case ATTACKER:
                return "ATTACKER";
        }
        return null;
    }

    @Override
    public ValOrUndo<PlayerColor> choosePlayer(MetaPlayer metaPlayer, List<PlayerColor> legalPlayers, boolean forceChoice) {
        GUILocker<ValOrUndo<PlayerColor>> locker = new GUILocker<>();
        this.currentLocker = locker;

        Map<PlayerColor, EventHandler<MouseEvent>> handlerMap = new EnumMap<>(PlayerColor.class);

        Platform.runLater(() -> {
            window.setHint("Choose "+ metaPlayerToString(metaPlayer) +" player.");

            if (!forceChoice) {
                Button noChoiceButton = new Button("No player");
                noChoiceButton.getStyleClass().add("no-choice-button");
                noChoiceButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> locker.deliver(new ValOrUndo<>(null)));

                Pane controlButtons = (Pane) window.getScene().lookup(".control-buttons");
                controlButtons.getChildren().add(noChoiceButton);
            }

            for (PlayerColor player : legalPlayers) {
                Pane playerPin = (Pane) window.getScene().lookup(".player-pin."+ GUIGameWindowHelper.toStyleClass(player));
                if (playerPin != null) {
                    playerPin.getStyleClass().add("selectable");

                    EventHandler<MouseEvent> handler = e -> locker.deliver(new ValOrUndo<>(player));
                    handlerMap.put(player, handler);

                    playerPin.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
                }
            }
        });

        try {
            return locker.waitForValue();
        } finally {
            Platform.runLater(() -> {
                for (PlayerColor player : legalPlayers) {
                    Pane playerPin = (Pane) window.getScene().lookup(".player-pin."+ GUIGameWindowHelper.toStyleClass(player));
                    if (playerPin != null) {
                        playerPin.getStyleClass().remove("selectable");
                        playerPin.removeEventHandler(MouseEvent.MOUSE_CLICKED, handlerMap.get(player));
                    }
                }

                window.setHint("");

                Button noChoiceButton = (Button) window.getScene().lookup(".no-choice-button");
                if (noChoiceButton != null) {
                    ((Pane) noChoiceButton.getParent()).getChildren().remove(noChoiceButton);
                }
            });
        }
    }

    @Override
    public ValOrUndo<TilePosition> chooseTile(List<TilePosition> legalTiles, boolean forceChoice) {
        GUILocker<ValOrUndo<TilePosition>> locker = new GUILocker<>();
        this.currentLocker = locker;

        Map<TilePosition, EventHandler<MouseEvent>> handlerMap = new HashMap<>();

        Platform.runLater(() -> {
            window.setHint("Choose a tile.");

            if (!forceChoice) {
                Button noChoiceButton = new Button("No tile");
                noChoiceButton.getStyleClass().add("no-choice-button");
                noChoiceButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> locker.deliver(new ValOrUndo<>(null)));

                Pane controlButtons = (Pane) window.getScene().lookup(".control-buttons");
                controlButtons.getChildren().add(noChoiceButton);
            }

            for (TilePosition tile : legalTiles) {
                Pane tilePane = GUIGameWindowHelper.lookupTilePane(window.getScene(), tile);
                if (tilePane != null) {
                    tilePane.getStyleClass().add("selectable");

                    EventHandler<MouseEvent> handler = e -> locker.deliver(new ValOrUndo<>(tile));
                    handlerMap.put(tile, handler);

                    tilePane.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
                }
            }
        });

        try {
            return locker.waitForValue();
        } finally {
            Platform.runLater(() -> {
                for (TilePosition tile : legalTiles) {
                    Pane tilePane = GUIGameWindowHelper.lookupTilePane(window.getScene(), tile);
                    if (tilePane != null) {
                        tilePane.getStyleClass().remove("selectable");
                        tilePane.removeEventHandler(MouseEvent.MOUSE_CLICKED, handlerMap.get(tile));
                    }
                }

                window.setHint("");

                Button noChoiceButton = (Button) window.getScene().lookup(".no-choice-button");
                if (noChoiceButton != null) {
                    ((Pane) noChoiceButton.getParent()).getChildren().remove(noChoiceButton);
                }
            });
        }
    }

    @Override
    public ValOrUndo<Integer> chooseCardFragment(Integer cardID, List<Integer> fragments, boolean forceChoice) {
        GUILocker<ValOrUndo<Integer>> locker = new GUILocker<>();
        this.currentLocker = locker;

        Platform.runLater(() -> {
            window.setHint("Choose a fragment in the card on the right.");

            if (!forceChoice) {
                Button noChoiceButton = new Button("No fragment");
                noChoiceButton.getStyleClass().add("no-choice-button");
                noChoiceButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> locker.deliver(new ValOrUndo<>(null)));

                Pane controlButtons = (Pane) window.getScene().lookup(".control-buttons");
                controlButtons.getChildren().add(noChoiceButton);
            }

            GUIGameWindowHelper.setActiveWeaponCard(window.getScene(), cardID);

            Pane activeCardSlot = (Pane) window.getScene().lookup("#activeCardSlot");
            for (int fragID : fragments) {

                Node fragButton = activeCardSlot.lookup(".effect-"+ fragID);
                fragButton.getStyleClass().add("selectable");

                fragButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> locker.deliver(new ValOrUndo<>(fragID)));
            }

        });

        try {
            return locker.waitForValue();
        } finally {
            Platform.runLater(() -> {
                Pane activeCardSlot = (Pane) window.getScene().lookup("#activeCardSlot");
                activeCardSlot.getChildren().clear();

                window.setHint("");

                Button noChoiceButton = (Button) window.getScene().lookup(".no-choice-button");
                if (noChoiceButton != null) {
                    ((Pane) noChoiceButton.getParent()).getChildren().remove(noChoiceButton);
                }
            });
        }
    }


    private String retrieveFragmentName(int fragID) {
        return "Frag ID "+ fragID; // TODO
    }

    @Override
    public ValOrUndo<PaymentReceiptData> choosePayment(PaymentInvoice invoice, AmmoSet playerInv, List<Integer> cardIDs, int fragmentToPay) {
        GUILocker<ValOrUndo<PaymentReceiptData>> locker = new GUILocker<>();
        this.currentLocker = locker;

        PaymentSelectionDialog selectionDialog = new PaymentSelectionDialog(invoice, playerInv, cardIDs, retrieveFragmentName(fragmentToPay));
        selectionDialog.setPaymentCallback(receipt -> {
            if (receipt == null) locker.deliver(new ValOrUndo<>(null));

            if (invoice.getAnyAmmos() <= 0)
                locker.deliver(new ValOrUndo<>(receipt));
            else {
                window.closeDialog();

                AmmoSet newPlayerInv = new AmmoSet(
                        playerInv.getRedAmmos() - receipt.getPayedRedAmmos(),
                        playerInv.getYellowAmmos() - receipt.getPayedYellowAmmos(),
                        playerInv.getBlueAmmos() - receipt.getPayedBlueAmmos());

                List<Integer> remainingPowerUps = new ArrayList<>(cardIDs);
                remainingPowerUps.removeAll(receipt.getPowerUps());

                showAmmoSelection(receipt, newPlayerInv, remainingPowerUps, locker);
            }
        });

        Platform.runLater(() -> {
            window.showDialog(selectionDialog);
        });


        try {
            return locker.waitForValue();
        } finally {
            Platform.runLater(() -> {
                window.closeDialog();
            });
        }
    }

    private void showAmmoSelection(PaymentReceiptData receipt, AmmoSet playerInv, List<Integer> powerUps, GUILocker<ValOrUndo<PaymentReceiptData>> locker) {
        AmmoSelectionDialog selectionDialog = new AmmoSelectionDialog(playerInv, powerUps, "");
        selectionDialog.setListener((ammo, powerUp) -> {
            int redAmmos = receipt.getPayedRedAmmos();
            int blueAmmos = receipt.getPayedBlueAmmos();
            int yellowAmmos = receipt.getPayedYellowAmmos();
            List<Integer> payedPowerUps = receipt.getPowerUps();

            if (powerUp != null) {
                payedPowerUps.add(powerUp);
            }

            switch (ammo) {
                case RED:
                    redAmmos++;
                    break;
                case BLUE:
                    blueAmmos++;
                    break;
                case YELLOW:
                    yellowAmmos++;
                    break;
                default: break;
            }

            locker.deliver(new ValOrUndo<>(new PaymentReceiptData(redAmmos, blueAmmos, yellowAmmos, payedPowerUps)));
        });

        window.showDialog(selectionDialog);
    }


}
