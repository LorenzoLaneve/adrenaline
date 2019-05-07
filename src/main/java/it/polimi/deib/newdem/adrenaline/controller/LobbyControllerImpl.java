package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.LobbyListener;
import it.polimi.deib.newdem.adrenaline.model.mgmt.LobbyState;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public class LobbyControllerImpl implements LobbyController {

    private Thread mainThread;
    private LobbyListener listener;

    @Override
    public Lobby getLobby() {
        //TODO
        return null;
    }

    @Override
    public LobbyListener getListener() {
        //TODO
        return null;
    }

    @Override
    public void setListener(LobbyListener lst) {
        //TODO
    }

    @Override
    public void changeState(LobbyState state) {
        //TODO
    }

    @Override
    public void addUser(User user) {
        //TODO
    }

    @Override
    public void removeUser(User user) {
        //TODO
    }

    public LobbyControllerImpl(){
        //TODO
    }
}
