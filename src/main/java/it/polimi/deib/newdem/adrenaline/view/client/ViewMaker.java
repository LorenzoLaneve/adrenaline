package it.polimi.deib.newdem.adrenaline.view.client;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.*;
import it.polimi.deib.newdem.adrenaline.view.inet.events.LobbyDataEvent;

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

}
