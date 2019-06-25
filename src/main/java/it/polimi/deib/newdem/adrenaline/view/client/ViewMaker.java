package it.polimi.deib.newdem.adrenaline.view.client;

import it.polimi.deib.newdem.adrenaline.view.ConnectionView;
import it.polimi.deib.newdem.adrenaline.view.GameView;
import it.polimi.deib.newdem.adrenaline.view.LobbyView;
import it.polimi.deib.newdem.adrenaline.view.UsernameView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.LobbyDataEvent;

public interface ViewMaker {

    ConnectionView makeConnectionView();

    UsernameView makeUsernameView();

    LobbyView makeLobbyView(LobbyDataEvent lobbyData);

    GameView makeGameView();

}
