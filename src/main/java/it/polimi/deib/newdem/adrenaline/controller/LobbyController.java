package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public interface LobbyController {

    Lobby getLobby();

    void addUser(User user);

    void removeUser(User user);

    int getMinPlayers();

    int getMaxPlayers();

    LobbyTimerListener getTimerListener();

    boolean acceptsNewUsers();

}
