package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.view.GameView;
import it.polimi.deib.newdem.adrenaline.view.LobbyView;
import it.polimi.deib.newdem.adrenaline.view.ConnectionView;
import it.polimi.deib.newdem.adrenaline.view.UsernameView;
import it.polimi.deib.newdem.adrenaline.view.client.ViewMaker;
import it.polimi.deib.newdem.adrenaline.view.inet.events.LobbyDataEvent;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

public class CLIViewMaker implements ViewMaker {

    private Logger log;

    private PrintStream out;

    private InputStream in;

    public CLIViewMaker(Logger log, PrintStream out, InputStream in) {
        this.log = log;
        this.out = out;
        this.in = in;
    }


    @Override
    public ConnectionView makeConnectionView() {
        return new CLIConnectionView(log, out, in);
    }

    @Override
    public UsernameView makeUsernameView() {
        return new CLIUsernameView(log, out, in);
    }

    @Override
    public LobbyView makeLobbyView(LobbyDataEvent lobbyData) {
        return new CLILobbyView(out, lobbyData.getUsers());
    }

    @Override
    public GameView makeGameView() {
        return new CLIGameView(out);
    }

}
