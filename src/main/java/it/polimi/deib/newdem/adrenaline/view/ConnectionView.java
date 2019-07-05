package it.polimi.deib.newdem.adrenaline.view;

/**
 * A client-only view used to ask the user for a server address.
 */
public interface ConnectionView {

    enum ConnectionType {
        SOCKETS,
        RMI
    }

    /**
     * This method will block the calling thread until the user has entered the required connection data.
     * Getters in this class will return the data entered by the user once this method has returned.
     */
    void prompt();

    String getServerHost();

    int getServerPort();

    ConnectionType getConnectionType();

    /**
     * Reports to the view that the entered data could not establish a valid connection.
     * @param message A user readable message explaining the error.
     */
    void reportError(String message);

    /**
     * Reports to the view that the entered data has successfully established a connection to the
     * server.
     */
    void reportSuccess();

}
