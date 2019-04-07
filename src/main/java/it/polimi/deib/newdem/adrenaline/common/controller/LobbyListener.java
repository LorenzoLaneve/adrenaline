package it.polimi.deib.newdem.adrenaline.common.controller;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;

public interface LobbyListener {

    void userDidEnterLobby(User user, Lobby lobby);

    void userDidExitLobby(User user, Lobby lobby);
}
