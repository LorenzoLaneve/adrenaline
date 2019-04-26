package it.polimi.deib.newdem.adrenaline.common.controller;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.common.view.inet.socket.SocketUserConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketUserModule implements IncomingUserModule {

    private ServerSocket serverSocket;

    public SocketUserModule(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void init() {
        // nothing to init.
    }

    @Override
    public User newUser() {

        try {
            Socket clientSocket = serverSocket.accept();

            User user = new User();

            SocketUserConnection newConnection = new SocketUserConnection(clientSocket, user);
            newConnection.start();

            return user;
        } catch (IOException x) {
            return null;
        }
    }

    @Override
    public void close() {
        try {
            serverSocket.close();
        } catch (IOException x) {
            // no problem
        } finally {
            serverSocket = null;
        }
    }
}
