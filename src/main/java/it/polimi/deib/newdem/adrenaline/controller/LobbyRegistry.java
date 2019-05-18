package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.ArrayList;
import java.util.List;

public class LobbyRegistry {

    private List<LobbyController> lobbies;

    private LobbyController firstLobbyController;

    private ServerInstance core;

    public LobbyRegistry(ServerInstance core){
        this.lobbies = new ArrayList<>();
    }

    public LobbyController getAvailableLobbyController() {
        if (firstLobbyController != null && !firstLobbyController.acceptsNewUsers()) {
            firstLobbyController = null;
        }

        if (firstLobbyController == null) {
            firstLobbyController = new LobbyControllerImpl(core.getCurrentConfig());
            lobbies.add(firstLobbyController);
        }

        return firstLobbyController;
    }

    public LobbyController getLobbyController(User user) {
        //TODO persistence
        return getAvailableLobbyController();
    }

}
