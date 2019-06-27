package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.PlayerView;

import java.io.PrintStream;

public class CLIPlayerView implements PlayerView {

    private PlayerColor color;

    private PrintStream out;

    public CLIPlayerView(PlayerColor color, PrintStream out) {
        this.color = color;
        this.out = out;
    }


    @Override
    public void setName(String name) {
        out.println("User "+ name +" is player "+ CLIHelper.colorToString(color));
    }

    @Override
    public void takeControl() {
        out.println("It's player "+ CLIHelper.colorToString(color) +"'s turn.");
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
            out.println("Player "+ CLIHelper.colorToString(color) +" acquired power up with ID "+ cardID);
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
