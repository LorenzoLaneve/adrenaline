package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.KillTrackView;

import java.io.PrintStream;

public class CLIKillTrackView implements KillTrackView {

    private PrintStream out;

    public CLIKillTrackView(PrintStream out) {
        this.out = out;
    }

    @Override
    public void restoreView(KillTrackData data) {
        out.println("These points are on the kill track:");

        for (KillTrackData.KillData cell : data.getKills()) {
            out.println("+ "+ CLIHelper.colorToString(cell.getKiller()) +" x"+ cell.getAmount());
        }
    }

    @Override
    public void registerKill(PlayerColor pColor, int amount) {
        out.println("Player "+ CLIHelper.colorToString(pColor) +" got a kill! x"+ amount);
    }

    @Override
    public void undoLastKill() {
        out.println("Last kill in the kill track has been undone.");
    }

}
