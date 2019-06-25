package it.polimi.deib.newdem.adrenaline.view;

public interface ConnectionView {

    enum ConnectionType {
        SOCKETS,
        RMI
    }

    String getServerHost();

    int getServerPort();

    ConnectionType getConnectionType();

    void prompt();

    void reportError(String message);

    void reportSuccess();

}
