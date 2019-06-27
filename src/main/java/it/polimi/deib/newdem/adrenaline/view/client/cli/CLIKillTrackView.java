package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.KillTrackView;

import java.io.PrintStream;

public class CLIKillTrackView implements KillTrackView {

    private PrintStream out;

    public CLIKillTrackView(PrintStream out) {
        this.out = out;
    }

    @Override
    public void registerKill(PlayerColor pColor, int amount) {
        out.println("Player "+ CLIHelper.colorToString(pColor) +" got a kill! x"+ amount);
    }

    @Override
    public void goFrenzy() {
        out.println("Game turned into frenzy mode!");
    }
}
