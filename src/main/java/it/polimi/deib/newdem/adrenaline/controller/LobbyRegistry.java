package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.List;

public class LobbyRegistry {

    private List<LobbyController> waitingLobbies;

    private LobbyController firstLobbyController;

    private ServerInstance core;

    public LobbyRegistry(ServerInstance core){
        //TODO
    }

    public LobbyController getAvailableLobbyController() {
        if (firstLobbyController != null) {
            Lobby lobby = firstLobbyController.getLobby();
            if (!lobby.acceptsNewUsers()) {
                firstLobbyController = null;
            }
        }

        if (firstLobbyController == null) {
            firstLobbyController = new LobbyControllerImpl(core.getCurrentConfig());
            waitingLobbies.add(firstLobbyController);
        }

        return firstLobbyController;
    }

    public LobbyController getLobbyController(User user) {
        //TODO persistence
        return getAvailableLobbyController();
    }

}
