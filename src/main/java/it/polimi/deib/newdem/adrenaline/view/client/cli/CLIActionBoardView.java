package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.ActionBoardView;

import java.io.PrintStream;

/**
 * Command Line Interface implementation of {@code ActionBoardView}
 * @see ActionBoardView for the semantics of the view methods.
 */
public class CLIActionBoardView implements ActionBoardView {

    private PlayerColor color;
    private PrintStream out;

    public CLIActionBoardView(PlayerColor color, PrintStream out) {
        this.color = color;
        this.out = out;
    }

    @Override
    public void flipActionBoard() {
        out.println("Player "+ CLIHelper.colorToString(color) +"'s action board switched to frenzy mode.");
    }

}
