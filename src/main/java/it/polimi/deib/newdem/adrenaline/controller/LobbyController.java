package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.LobbyListener;
import it.polimi.deib.newdem.adrenaline.model.mgmt.LobbyState;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public interface LobbyController {

    Lobby getLobby();

    LobbyListener getListener();

    void setListener(LobbyListener lst);

    void changeState(LobbyState state);

    void addUser(User user);

    void removeUser(User user);
}
