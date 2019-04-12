package it.polimi.deib.newdem.adrenaline.common.controller;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.LobbyState;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;

public interface LobbyController {

    Lobby getLobby();

    LobbyListener getListener();

    void setListener(LobbyListener lst);

    void changeState(LobbyState state);

    void addUser(User user);

    void removeUser(User user);
}
