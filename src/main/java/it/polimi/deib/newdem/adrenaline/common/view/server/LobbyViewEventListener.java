package it.polimi.deib.newdem.adrenaline.common.view.server;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;

public interface LobbyViewEventListener {

    void userDidLoadLobby(User user, Lobby lobby);

    void userWillExitLobby(User user, Lobby lobby);

}
