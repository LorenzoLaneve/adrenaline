package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.List;

public interface GameController {

    void setupGame(List<User> users);

    void recoverGame();

    void runGame();

    void userDidLeaveLobby(User user);

    void userDidReenterLobby(User user);

}
