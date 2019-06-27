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
        out.println("Player "+ CLIHelper.colorToString(color) +" got "+ damageAmount +"x damage and "+ markAmount +"x marks from Player "+ CLIHelper.colorToString(dealer));
    }

    @Override
    public void convertMarks(PlayerColor dealer) {
        out.println("Player "+ CLIHelper.colorToString(color) +" got marks from "+ CLIHelper.colorToString(dealer) +" converted into damage.");
    }
}
