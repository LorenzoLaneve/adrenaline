package it.polimi.deib.newdem.adrenaline.view.inet.rmi;

import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerEndpoint extends Remote {

    UserEvent readEvent() throws RemoteException;

    void writeEvent(UserEvent event) throws RemoteException;

}
