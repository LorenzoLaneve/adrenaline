package it.polimi.deib.newdem.adrenaline.view.inet.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerGreeter extends Remote {

    /**
     * Tries to connect to the RMI server.
     * @param clientEndpoint The client's end point used by the server to listen for client to server events.
     * @return A new end point that the client needs to use to listen for server to client events.
     */
    RMIEndpoint connect(RMIEndpoint clientEndpoint) throws RemoteException;

}
