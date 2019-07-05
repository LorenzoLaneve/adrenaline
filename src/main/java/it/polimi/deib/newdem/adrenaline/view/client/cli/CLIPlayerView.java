package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerData;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.view.PlayerView;

import java.io.PrintStream;
import java.util.Map;

/**
 * Command Line Interface implementation of {@code PlayerView}
 * @see PlayerView for the semantics of the view methods.
 */
public class CLIPlayerView implements PlayerView {

    private PlayerColor color;

    private PrintStream out;

    public CLIPlayerView(PlayerColor color, PrintStream out) {
        this.color = color;
        this.out = out;
    }


    @Override
    public void setPlayerData(PlayerData data) {
        Map<AmmoColor, Integer> ammos = data.getAmmos();
        out.print("Player "+ CLIHelper.colorToString(color) +" has ");
        out.print(ammos.get(AmmoColor.RED) +" red ammo(s), ");
        out.print(ammos.get(AmmoColor.BLUE) +" blue ammo(s), and ");
        out.println(ammos.get(AmmoColor.YELLOW) +" yellow ammo(s).");

        out.println("Player "+ CLIHelper.colorToString(color) +" has the following powerups:");
        for (Integer powerUp : data.getPowerUpCards()) {
            out.println(powerUp +" - "+ CLIHelper.getPowerUpName(powerUp));
        }

        out.println("Player "+ CLIHelper.colorToString(color) +" has the following weapons:");
        for (Integer powerUp : data.getReadyWeaponCards()) {
            out.println("+ "+ powerUp +" - "+ CLIHelper.getWeaponName(powerUp) + " (loaded)");
        }
        for (Integer powerUp : data.getUnloadedWeaponCards()) {
            out.println("+ "+ powerUp +" - "+ CLIHelper.getWeaponName(powerUp) + " (unloaded)");
        }


        out.println("Player "+ CLIHelper.colorToString(color) +"'s action board is "+ (!data.isActionBoardFrenzy() ? "NOT " : "") +"in frenzy mode.");
        out.println("Player "+ CLIHelper.colorToString(color) +"'s damage board is "+ (!data.isDamageBoardFrenzy() ? "NOT " : "") +"in frenzy mode.");

        out.println("Player "+ CLIHelper.colorToString(color) +" has the following damages:");
        for (PlayerColor damager : data.getDamages()) {
            out.print(CLIHelper.colorToString(damager).charAt(0) +" ");
        }
        out.println();

        out.println("Player "+ CLIHelper.colorToString(color) +" has the following marks:");
        for (Map.Entry<PlayerColor, Integer> marks : data.getMarks().entrySet()) {
            out.println("+ "+ marks.getValue() +" from "+ CLIHelper.colorToString(marks.getKey()));
        }
        out.println();

        if (data.getPosition() != null) {
            out.println("Player " + CLIHelper.colorToString(color) + " is at location " + CLIHelper.tilePositionToString(data.getPosition()));
        } else {
            out.println("Player " + CLIHelper.colorToString(color) + " is not in map.");
        }
        out.println("Player "+ CLIHelper.colorToString(color) +"'s current score is "+ data.getScore());
    }

    @Override
    public void setName(String name) {
        out.println("Player "+ CLIHelper.colorToString(color) +" is now "+ name);
    }

    @Override
    public void setScore(int score) {
        out.println("Player "+ CLIHelper.colorToString(color) +"'s score is now "+ score);
    }

    @Override
    public void addPowerUpCard(int cardID) {
        if (cardID < 0) {
            out.println("Player "+ CLIHelper.colorToString(color) +" acquired a new power up card.");
        } else {
            out.println("Player "+ CLIHelper.colorToString(color) +" acquired a new power up with ID "+ cardID);
        }
    }

    @Override
    public void removePowerUpCard(int cardID) {
        out.println("Player "+ CLIHelper.colorToString(color) +" has discarded power up with ID "+ cardID);
    }

    @Override
    public void addWeaponCard(int cardID) {
        out.println("Player "+ CLIHelper.colorToString(color) +" acquired a new Weapon Card:");
        out.println(cardID +" - "+ CLIHelper.getWeaponName(cardID));
    }

    @Override
    public void removeWeaponCard(int cardID) {
        out.println("Player "+ CLIHelper.colorToString(color) +" discarded the following Weapon Card(s):");
        out.println(cardID +" - "+ CLIHelper.getWeaponName(cardID));
    }

    @Override
    public void addAmmoSet(int yellowAmount, int redAmount, int blueAmount) {
        out.println("Player "+ CLIHelper.colorToString(color) +" acquired the following ammo(s):");
        out.println("+ "+ redAmount +" red ammos");
        out.println("+ "+ blueAmount +" blue ammos");
        out.println("+ "+ yellowAmount+" yellow ammos");
    }

    @Override
    public void removeAmmoSet(int yellowAmount, int redAmount, int blueAmount) {
        out.println("Player "+ CLIHelper.colorToString(color) +" discarded the following ammo(s):");
        out.println("- "+ redAmount +" red ammos");
        out.println("- "+ blueAmount +" blue ammos");
        out.println("- "+ yellowAmount+" yellow ammos");
    }

    @Override
    public void reloadWeaponCard(int cardID) {
        out.println("The following weapon(s) are now reloaded and ready to be used again:");
        out.println(cardID +" - "+ CLIHelper.getWeaponName(cardID));
    }

    @Override
    public void unloadWeaponCard(int cardID) {
        out.println("The following weapon(s) have been used and now need to be reloaded:");
        out.println(cardID +" - "+ CLIHelper.getWeaponName(cardID));
    }
}
