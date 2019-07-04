package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.DamageBoardView;

import java.io.PrintStream;

public class CLIDamageBoardView implements DamageBoardView {

    private PlayerColor color;

    private PrintStream out;

    public CLIDamageBoardView(PlayerColor color, PrintStream out) {
        this.color = color;
        this.out = out;
    }

    @Override
    public void registerDamage(int damageAmount, int markAmount, PlayerColor dealer) {
        if (damageAmount > 0) {
            out.println("Player " + CLIHelper.colorToString(color) + " got " + damageAmount + "x damage from Player " + CLIHelper.colorToString(dealer));
        }
        if (markAmount != 0) {
            out.println("Player " + CLIHelper.colorToString(color) + " got " + markAmount + "x marks from Player " + CLIHelper.colorToString(dealer));
        }
    }

    @Override
    public void popDamage() {
        out.println("Player "+ CLIHelper.colorToString(color) +"'s last damage was removed.");
    }

    @Override
    public void goFrenzy() {
        out.println("Player "+ CLIHelper.colorToString(color) +"'s damage board switched to frenzy mode.");
    }

    @Override
    public void clearBoard() {
        out.println("Player "+ CLIHelper.colorToString(color) +"'s damage board was cleared.");
    }
}
