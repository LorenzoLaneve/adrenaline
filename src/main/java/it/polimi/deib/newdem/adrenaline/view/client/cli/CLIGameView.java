package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.GameResults;
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
        out.println("Players in game:");
        for (GameData.UserColorPair pair : data.getPlayers()) {
            out.println("+ User " + pair.getUsername() + " is player " + CLIHelper.colorToString(pair.getColor()));
        }
    }

    @Override
    public void disablePlayer(PlayerColor color) {
        out.println("Player "+ CLIHelper.colorToString(color) +" disconnected.");
    }

    @Override
    public void enablePlayer(PlayerColor color) {
        out.println("Player "+ CLIHelper.colorToString(color) +" reconnected.");
    }

    @Override
    public void endGame(GameResults results) {
        out.println("The game is over!");

        out.println("\nResults: ");
        for (GameResults.PlayerScoreRecord record : results.getRecords()) {
            out.println(CLIHelper.colorToString(record.getPlayer()) +" with a score of "+ record.getScore());
        }
        out.println();
    }

}
