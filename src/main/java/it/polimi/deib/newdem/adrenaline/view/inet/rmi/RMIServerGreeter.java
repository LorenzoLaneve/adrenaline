package it.polimi.deib.newdem.adrenaline.view.inet.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerGreeter extends Remote {

    RMIServerEndpoint connect() throws RemoteException;

}
