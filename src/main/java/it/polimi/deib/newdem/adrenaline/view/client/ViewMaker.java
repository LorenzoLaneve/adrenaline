package it.polimi.deib.newdem.adrenaline.view.client;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.*;
import it.polimi.deib.newdem.adrenaline.view.inet.events.LobbyDataEvent;

/**
 * Interface defining methods that create new views for the {@code ClientInstance} objects.
 * This provides an abstraction level between the client logic (i.e. network handling) and the
 * user interface. Different interfaces can be created by simply create a new implementation of this
 * interface.
 */
public interface ViewMaker {

    ConnectionView makeConnectionView();

    UsernameView makeUsernameView();

    LobbyView makeLobbyView(LobbyDataEvent lobbyData);

    GameView makeGameView();

    MapView makeMapView();

    KillTrackView makeKillTrackView();

    PlayerView makePlayerView(PlayerColor playerColor);

    DamageBoardView makeDamageBoardView(PlayerColor playerColor);

    ActionBoardView makeActionBoardView(PlayerColor color);

    TurnView makeTurnView();

    /**
     * Notifies the underlying user interface that a disconnection occurred.
     */
    void notifyDisconnection();

}
