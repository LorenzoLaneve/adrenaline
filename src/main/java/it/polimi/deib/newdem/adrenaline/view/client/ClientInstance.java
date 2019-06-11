package it.polimi.deib.newdem.adrenaline.view.client;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UpdateUsernameRequest;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UpdateUsernameResponse;
import it.polimi.deib.newdem.adrenaline.view.inet.rmi.RMIEndpointImpl;
import it.polimi.deib.newdem.adrenaline.view.inet.rmi.RMIServerGreeter;
import it.polimi.deib.newdem.adrenaline.view.inet.rmi.RMIUserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.sockets.SocketUserConnection;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientInstance {

    private ViewMaker viewMaker;

    private UserConnection clientConnection;


    public ClientInstance(ViewMaker viewMaker) {
        this.viewMaker = viewMaker;
    }

    public void start() {
        createConnection();

        clientConnection.subscribeEvent(UpdateUsernameRequest.class, this::usernameRequested);


    }

    private void usernameRequested(UserConnection connection, UpdateUsernameRequest request) {
        connection.unsubscribeEvent(UpdateUsernameRequest.class, this::usernameRequested);

        connection.sendEvent(new UpdateUsernameResponse("Pippo"));
    }

    private void createConnection() {
        ConnectionView connectionView = viewMaker.makeConnectionView();

        boolean connectionIsOk = false;
        do {
            connectionView.prompt();

            try {
                if (connectionView.getConnectionType() == ConnectionView.ConnectionType.RMI) {
                    Registry serverRegistry = LocateRegistry.getRegistry(connectionView.getServerHost(), connectionView.getServerPort());

                    RMIServerGreeter serverGreeter = (RMIServerGreeter) serverRegistry.lookup("adrenaline");

                    RMIEndpointImpl localEndpoint = new RMIEndpointImpl();

                    clientConnection = new RMIUserConnection(serverGreeter, localEndpoint, new User());
                } else {
                    clientConnection = new SocketUserConnection(connectionView.getServerHost(), connectionView.getServerPort(), new User());
                }

                clientConnection.start();

                connectionIsOk = true;
            } catch (Exception x) {
                connectionView.reportError(x.getMessage());
            }

        } while (!connectionIsOk);

        connectionView.reportSuccess();
    }





}
