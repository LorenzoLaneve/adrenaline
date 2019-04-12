package it.polimi.deib.newdem.adrenaline.common.controller;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketUserModule implements IncomingUserModule {

    private ServerSocket serverSocket;

    public SocketUserModule(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void init() {
        //TODO
    }

    @Override
    public User newUser() {
        //TODO
        return null;
    }

    @Override
    public void close() {
        //TODO
    }
}
