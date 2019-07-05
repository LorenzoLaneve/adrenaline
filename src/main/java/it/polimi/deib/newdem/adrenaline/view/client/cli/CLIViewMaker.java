package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.*;
import it.polimi.deib.newdem.adrenaline.view.client.ViewMaker;
import it.polimi.deib.newdem.adrenaline.view.inet.events.LobbyDataEvent;

import java.io.PrintStream;
import java.util.logging.Logger;

/**
 * Command Line Interface implementation of {@code ViewMaker}.
 * This is implemented simply by using the input and outstream passed to the constructor.
 * This view maker will create CLI views that will use the specified streams to communicate with
 * the user.
 */
public class CLIViewMaker implements ViewMaker {

    private Logger log;

    private PrintStream out;

    private CLIReader in;

    /**
     * Create a new CLI view maker with the given streams.
     */
    public CLIViewMaker(Logger log, PrintStream out, CLIReader in) {
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
        return new CLIUsernameView(out, in);
    }

    @Override
    public LobbyView makeLobbyView(LobbyDataEvent lobbyData) {
        return new CLILobbyView(out, lobbyData.getUsers());
    }

    @Override
    public GameView makeGameView() {
        return new CLIGameView(out);
    }

    @Override
    public MapView makeMapView() {
        return new CLIMapView(out);
    }

    @Override
    public KillTrackView makeKillTrackView() {
        return new CLIKillTrackView(out);
    }

    @Override
    public PlayerView makePlayerView(PlayerColor playerColor) {
        return new CLIPlayerView(playerColor, out);
    }

    @Override
    public DamageBoardView makeDamageBoardView(PlayerColor playerColor) {
        return new CLIDamageBoardView(playerColor, out);
    }

    @Override
    public ActionBoardView makeActionBoardView(PlayerColor color) {
        return new CLIActionBoardView(color, out);
    }

    @Override
    public TurnView makeTurnView() {
        return new CLITurnView(out, in);
    }

    @Override
    public void notifyDisconnection() {
        out.println();
        out.println("Connection to server lost.");

        System.exit(0);
    }

}
