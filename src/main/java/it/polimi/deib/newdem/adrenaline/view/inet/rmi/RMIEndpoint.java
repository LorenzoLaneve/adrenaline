package it.polimi.deib.newdem.adrenaline.view.inet.rmi;

import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * An object that represents an end point that delivers events to the other side.
 */
public interface RMIEndpoint extends Remote {

    /**
     * Reads an incoming event from the other side end point, eventually blocking the
     * current thread if no messages are sent.
     * @throws RemoteException if any RMI-related error occurs.
     */
    UserEvent readEvent() throws RemoteException;

}
