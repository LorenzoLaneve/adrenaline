package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.view.LobbyView;

import java.io.PrintStream;
import java.util.List;

public class CLILobbyView implements LobbyView {

    private PrintStream out;

    public CLILobbyView(PrintStream out, List<String> userData) {
        this.out = out;

        out.println("Users in lobby: ");
        for (String user : userData) {
            out.println("+ "+ user);
        }
        out.println();
    }


    @Override
    public void addUser(String name) {
        out.println(String.format("User %s joined the lobby.", name));
    }

    @Override
    public void removeUser(String name) {
        out.println(String.format("User %s left the lobby.", name));
    }

    @Override
    public void startTimer(int seconds) {
        out.println(String.format("Timer has started, %d seconds left until the game starts.", seconds));
    }

    @Override
    public void syncTimer(int seconds) {
        out.println(String.format("Game will start in %d seconds.", seconds));
    }

    @Override
    public void abortTimer() {
        out.println("Timer has been aborted due to insufficient players.");
    }

}
