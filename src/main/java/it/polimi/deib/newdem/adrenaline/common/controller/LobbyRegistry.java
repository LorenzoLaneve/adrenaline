package it.polimi.deib.newdem.adrenaline.common.controller;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;

import java.util.List;

public class LobbyRegistry {

    private List<LobbyController> waitingLobbies;

    private ServerInstance core;

    public LobbyRegistry(ServerInstance core){
        //TODO
    }

    public LobbyController getFirstLobbyManager(){
        //TODO
        return null;
    }

    public LobbyController getLobbyManager(User user){
        //TODO
        return null;
    }

}
