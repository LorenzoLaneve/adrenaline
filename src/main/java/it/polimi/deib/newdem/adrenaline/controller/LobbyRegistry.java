package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LobbyRegistry {

    private List<LobbyController> lobbies;

    private Map<User, LobbyController> userLocations;

    private LobbyController firstLobbyController;

    private ServerInstance core;

    public LobbyRegistry(ServerInstance core) {
        this.lobbies = new ArrayList<>();
        this.userLocations = new HashMap<>();

        this.core = core;
    }

    private synchronized LobbyController getAvailableLobbyController() {
        if (firstLobbyController != null && !firstLobbyController.acceptsNewUsers()) {
            firstLobbyController = null;
        }

        if (firstLobbyController == null) {
            firstLobbyController = new LobbyControllerImpl(core.getCurrentConfig());
            lobbies.add(firstLobbyController);
        }

        return firstLobbyController;
    }

    private LobbyController getOrCreateLobbyController(User user) {
        //TODO persistence
        return getAvailableLobbyController();
    }

    public void assignLobby(User user) {
        LobbyController lobby = getOrCreateLobbyController(user);
        lobby.addUser(user);
        userLocations.put(user, lobby);

        core.getLogger().info(String.format("User %s was assigned to lobby %s.", user.hashCode(), lobby.hashCode()));
    }

    public void removeUser(User user) {
        LobbyController lobby = getLobbyByUser(user);
        if (lobby != null && lobby.acceptsNewUsers()) {
            userLocations.remove(user);
            lobby.removeUser(user);
        }
    }

    public LobbyController getLobbyByUser(User user) {
        return userLocations.get(user);
    }

}
