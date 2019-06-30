package it.polimi.deib.newdem.adrenaline.view.client.cli;

import com.sun.org.apache.regexp.internal.RE;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceiptData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.TurnView;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class CLITurnView implements TurnView {

    private PrintStream out;

    private CLIReader in;

    public CLITurnView(PrintStream out, CLIReader in) {
        this.out = out;
        this.in = in;
    }


    @Override
    public void startTurn(PlayerColor actor) {
        out.println("It's Player "+ CLIHelper.colorToString(actor) +"'s turn.");
    }

    @Override
    public void endTurn(PlayerColor actor) {
        out.println("Player "+ CLIHelper.colorToString(actor) +"'s turn is over.");
    }


    private static String getActionName(AtomicActionType atom) {
        switch (atom) {
            case MOVE4:
                return "Move x4";
            case MOVE3:
                return "Move x3";
            case MOVE2:
                return "Move x2";
            case MOVE1:
                return "Move x1";
            case GRAB:
                return "Pick Up";
            case SHOOT:
                return "Use Weapon";
            case RELOAD:
                return "Reload Weapon";
            case USE_POWERUP:
                return "Use Power Up";
        }
        return null;
    }

    private static String getActionName(ActionType actionType) {
        StringBuilder builder = new StringBuilder();
        for (AtomicActionType atom : actionType.getAtomicTypes()) {
            builder.append(", ");
            builder.append(getActionName(atom));
        }
        return builder.toString().substring(2);
    }

    @Override
    public ValOrUndo<ActionType> chooseAction(List<ActionType> availableActions) {
        out.println("Choose an action among the following:");

        int i = 0;
        for (ActionType action : availableActions) {
            out.println("+ "+ i++ +" - "+ getActionName(action));
        }

        int choice = -1;
        do {
            out.println("Enter the ID of the action or [u]ndo: ");
            String line = in.nextLine();
            if (line.equalsIgnoreCase("u"))
                return new ValOrUndo<>();

            try {
                choice = Integer.valueOf(line);
            } catch (Exception x) {
                // nothing to do here.
            }

        } while (choice < 0 || choice >= availableActions.size());
        return new ValOrUndo<>(availableActions.get(choice));
    }

    @Override
    public ValOrUndo<Integer> chooseWeaponCard(List<Integer> cardIDs) {
        out.println("Choose a weapon among the following:");
        for (int cardID : cardIDs) {
            out.println("+ "+ cardID +" - "+ CLIHelper.getWeaponName(cardID));
        }

        String line;
        do {
            out.println("Enter the ID of the weapon or [u]ndo: ");
            line = in.nextLine();
            if (line.equalsIgnoreCase("u"))
                return new ValOrUndo<>();

        } while (!cardIDs.stream().map(x -> Integer.toString(x)).collect(Collectors.toList()).contains(line));
        return new ValOrUndo<>(Integer.valueOf(line));
    }

    @Override
    public ValOrUndo<Integer> choosePowerUpCard(List<Integer> cardIDs) {
        out.println("Choose a power up among the following:");
        for (int cardID : cardIDs) {
            out.println("+ "+ cardID +" - "+ CLIHelper.getPowerUpName(cardID));
        }

        String line;
        do {
            out.println("Enter the ID of the power up or [u]ndo: ");
            line = in.nextLine();
            if (line.equalsIgnoreCase("u"))
                return new ValOrUndo<>();

        } while (!cardIDs.stream().map(x -> Integer.toString(x)).collect(Collectors.toList()).contains(line));
        return new ValOrUndo<>(Integer.valueOf(line));
    }


    private String metaPlayerToString(MetaPlayer player) {
        switch (player) {
            case ATTACKER:
                return "Attacker";
            case RED:
                return "RED";
            case BLUE:
                return "BLUE";
            case GREEN:
                return "GREEN";
            case YELLOW:
                return "YELLOW";
        }
        return null;
    }

    private PlayerColor stringToColor(String color) {
        switch (color.toLowerCase()) {
            case "yellow":
                return PlayerColor.YELLOW;
            case "cyan":
                return PlayerColor.CYAN;
            case "gray":
                return PlayerColor.GRAY;
            case "green":
                return PlayerColor.GREEN;
            case "magenta":
                return PlayerColor.MAGENTA;
        }
        return null;
    }

    @Override
    public ValOrUndo<PlayerColor> choosePlayer(MetaPlayer metaPlayer, List<PlayerColor> legalPlayers, boolean forceChoice) {
        out.println("Choose a player for "+ metaPlayerToString(metaPlayer) +" among the following:");
        for (PlayerColor playerColor : legalPlayers) {
            out.println("+ "+ CLIHelper.colorToString(playerColor));
        }

        PlayerColor chosenPlayer = null;
        do {
            out.println("Enter the ID of the power up or [u]ndo: ");
            String line = in.nextLine();
            if (line.equalsIgnoreCase("u"))
                return new ValOrUndo<>();

            chosenPlayer = stringToColor(line);
        } while (chosenPlayer == null || !legalPlayers.contains(chosenPlayer));
        return new ValOrUndo<>(chosenPlayer);
    }

    @Override
    public ValOrUndo<TilePosition> chooseTile(List<TilePosition> legalTiles, boolean forceChoice) {
        out.println("Choose a tile among the following:");
        for (TilePosition tile : legalTiles) {
            out.println("+ "+ CLIHelper.tilePositionToString(tile));
        }

        TilePosition chosenTile = null;
        do {
            out.println("Enter the X coord of the tile or [u]ndo: ");
            String lineX = in.nextLine();
            if (lineX.equalsIgnoreCase("u"))
                return new ValOrUndo<>();

            out.println("Enter the Y coord of the tile or [u]ndo: ");
            String lineY = in.nextLine();
            if (lineY.equalsIgnoreCase("u"))
                return new ValOrUndo<>();

            try {
                chosenTile = new TilePosition(Integer.valueOf(lineX), Integer.valueOf(lineY));
            } catch (Exception x) {
                chosenTile = null;
            }

        } while (chosenTile == null || !legalTiles.contains(chosenTile));
        return new ValOrUndo<>(chosenTile);
    }


    private String getFragmentName(int cardID, int fragID) {
        return "frag ID "+ fragID; // TODO
    }

    @Override
    public ValOrUndo<Integer> chooseCardFragment(Integer cardID, List<Integer> fragments, boolean forceChoice) {
        out.println("Choose a fragment among the following:");
        for (int fragID : fragments) {
            out.println("+ "+ fragID +" - "+ getFragmentName(cardID, fragID));
        }

        Integer chosenFrag = null;
        do {
            out.println("Enter the ID of the fragment or [u]ndo: ");
            String line = in.nextLine();
            if (line.equalsIgnoreCase("u"))
                return new ValOrUndo<>();

            try {
                chosenFrag = Integer.valueOf(line);
            } catch (Exception x) {
                chosenFrag = null;
            }
        } while (chosenFrag == null || !fragments.contains(chosenFrag));
        return new ValOrUndo<>(chosenFrag);
    }




    private static AmmoColor getEquivalentAmmo(int cardID) {
        // FIXME this should not be done as it is not general. It should be better to use a json file for the client.
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


    private static Boolean stringToYesNo(String line) {
        switch (line.toLowerCase()) {
            case "y":
                return true;
            case "n":
                return false;
        }
        return null;
    }

    @Override
    public ValOrUndo<PaymentReceiptData> choosePayment(PaymentInvoice invoice, AmmoSet playerInv, List<Integer> cardIDs, int fragmentToPay) {

        List<Integer> selectedPowerUps = new ArrayList<>();

        EnumMap<AmmoColor, Integer> ammosToPay = new EnumMap<>(AmmoColor.class);
        ammosToPay.put(AmmoColor.RED, invoice.getRedAmmos());
        ammosToPay.put(AmmoColor.BLUE, invoice.getBlueAmmos());
        ammosToPay.put(AmmoColor.YELLOW, invoice.getYellowAmmos());

        out.println("You have the following payment invoice:");
        out.println("+ "+ ammosToPay.get(AmmoColor.RED) +" red ammo(s) (You have "+ playerInv.getRedAmmos() +")");
        out.println("+ "+ ammosToPay.get(AmmoColor.BLUE) +" blue ammo(s) (You have "+ playerInv.getBlueAmmos() +")");
        out.println("+ "+ ammosToPay.get(AmmoColor.YELLOW) +" yellow ammo(s) (You have "+ playerInv.getYellowAmmos() +")");

        for (int cardID : cardIDs) if (ammosToPay.get(getEquivalentAmmo(cardID)) > 0) {
            Boolean choice = null;
            do {
                out.println("Do you want to use "+ CLIHelper.getPowerUpName(cardID) +" to pay? [y/n]");
                choice = stringToYesNo(in.nextLine());
            } while (choice == null);

            if (choice) {
                selectedPowerUps.add(cardID);
            }
        }

        out.println("You need to pay an ammo of any color:");
        if (ammosToPay.get(AmmoColor.RED) > playerInv.getRedAmmos()) {
            Boolean choice;
            do {
                out.println("Do you want to use a red ammo to pay? [y/n]");
                choice = stringToYesNo(in.nextLine());
            } while (choice == null);

            if (choice) {
                ammosToPay.put(AmmoColor.RED, ammosToPay.get(AmmoColor.RED) + 1);
                return new ValOrUndo<>(new PaymentReceiptData(
                        ammosToPay.get(AmmoColor.RED),
                        ammosToPay.get(AmmoColor.BLUE),
                        ammosToPay.get(AmmoColor.YELLOW),
                        selectedPowerUps));
            }
        }

        if (ammosToPay.get(AmmoColor.BLUE) > playerInv.getBlueAmmos()) {
            Boolean choice;
            do {
                out.println("Do you want to use a blue ammo to pay? [y/n]");
                choice = stringToYesNo(in.nextLine());
            } while (choice == null);

            if (choice) {
                ammosToPay.put(AmmoColor.BLUE, ammosToPay.get(AmmoColor.BLUE) + 1);
                return new ValOrUndo<>(new PaymentReceiptData(
                        ammosToPay.get(AmmoColor.RED),
                        ammosToPay.get(AmmoColor.BLUE),
                        ammosToPay.get(AmmoColor.YELLOW),
                        selectedPowerUps));
            }
        }

        if (ammosToPay.get(AmmoColor.YELLOW) > playerInv.getYellowAmmos()) {
            Boolean choice;
            do {
                out.println("Do you want to use a yellow ammo to pay? [y/n]");
                choice = stringToYesNo(in.nextLine());
            } while (choice == null);

            if (choice) {
                ammosToPay.put(AmmoColor.YELLOW, ammosToPay.get(AmmoColor.YELLOW) + 1);
                return new ValOrUndo<>(new PaymentReceiptData(
                        ammosToPay.get(AmmoColor.RED),
                        ammosToPay.get(AmmoColor.BLUE),
                        ammosToPay.get(AmmoColor.YELLOW),
                        selectedPowerUps));
            }
        }

        List<Integer> remainingCards = new ArrayList<>(cardIDs);
        cardIDs.removeAll(selectedPowerUps);

        for (int cardID : remainingCards) {
            Boolean choice;
            do {
                out.println("Do you want to use "+ CLIHelper.getPowerUpName(cardID) +" to pay? [y/n]");
                choice = stringToYesNo(in.nextLine());
            } while (choice == null);

            if (choice) {
                selectedPowerUps.add(cardID);
                return new ValOrUndo<>(new PaymentReceiptData(
                        ammosToPay.get(AmmoColor.RED),
                        ammosToPay.get(AmmoColor.BLUE),
                        ammosToPay.get(AmmoColor.YELLOW),
                        selectedPowerUps));
            }
        }

        out.println("The payment will be cancelled.");
        return new ValOrUndo<>(null);
    }
}
