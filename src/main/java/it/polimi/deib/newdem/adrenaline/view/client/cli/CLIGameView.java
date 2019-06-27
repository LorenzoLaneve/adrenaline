package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.GameView;

import java.io.PrintStream;

public class CLIGameView implements GameView {

    private PrintStream out;

    public CLIGameView(PrintStream out) {
        this.out = out;
    }

    @Override
    public void setGameData(GameData data) {
        // TODO
    }

    @Override
    public void disablePlayer(PlayerColor color) {
        // TODO
    }

    @Override
    public void enablePlayer(PlayerColor color) {
        out.println("Player "+ CLIHelper.colorToString(color) +" player turn.");
    }

}
