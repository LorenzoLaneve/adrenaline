package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public interface LobbyViewEventListener {

    void userDidLoadLobby(User user, Lobby lobby);

    void userWillExitLobby(User user, Lobby lobby);

}
