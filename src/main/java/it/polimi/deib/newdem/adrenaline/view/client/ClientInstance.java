package it.polimi.deib.newdem.adrenaline.view.client;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.model.mgmt.UserListener;
import it.polimi.deib.newdem.adrenaline.view.*;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserEventLocker;
import it.polimi.deib.newdem.adrenaline.view.inet.UserEventSubscriber;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;
import it.polimi.deib.newdem.adrenaline.view.inet.rmi.RMIEndpointImpl;
import it.polimi.deib.newdem.adrenaline.view.inet.rmi.RMIServerGreeter;
import it.polimi.deib.newdem.adrenaline.view.inet.rmi.RMIUserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.sockets.SocketUserConnection;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientInstance implements AutoCloseable, UserListener {

    private ViewMaker viewMaker;

    private UserConnection clientConnection;

    private boolean closeOnConnection;

    private Thread clientThread;

    private GameClientManager gameManager;


    public ClientInstance(ViewMaker viewMaker) {
        this.viewMaker = viewMaker;
    }

    public void start() {
        this.clientThread = new Thread(this::runClient);
        this.clientThread.start();

        try {
            this.clientThread.join();
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
            // nothing to do here.
        }
    }

    private void runClient() {
        try {
            createConnection();

            this.closeOnConnection = true;
            clientConnection.getUser().addListener(this);

            askUsername();
            waitLobby();
            playGame();
            this.closeOnConnection = false;
        } catch (ClosedException x) {
            // ok
        }
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

    private void askUsername() {
        UserEventLocker<UpdateUsernameRequest> locker = new UserEventLocker<>();

        try {
            locker.waitOnEvent(UpdateUsernameRequest.class, clientConnection);
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
            throw new ClosedException("Close requested.");
        }

        UsernameView usernameView = viewMaker.makeUsernameView();

        boolean usernameOk;
        do {
            String username = usernameView.prompt();

            clientConnection.sendEvent(new UpdateUsernameResponse(username));

            UserEventLocker<RegisterUsernameEvent> responseLocker = new UserEventLocker<>();
            try {
                usernameOk = responseLocker.waitOnEvent(RegisterUsernameEvent.class, clientConnection).usernameAccepted();
            } catch (InterruptedException x) {
                Thread.currentThread().interrupt();
                throw new ClosedException("Close requested.");
            }

            if (!usernameOk) {
                usernameView.reportError("This username is already taken or invalid.");
            }
        } while (!usernameOk);

        usernameView.reportSuccess();
    }

    private void waitLobby() {
        UserEventLocker<LobbyDataEvent> lobbyDataReceiver = new UserEventLocker<>();

        LobbyView lobbyView;
        try {
            lobbyView = viewMaker.makeLobbyView(lobbyDataReceiver.waitOnEvent(LobbyDataEvent.class, clientConnection));
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
            throw new ClosedException("Close requested.");
        }

        UserEventSubscriber<EnterLobbyEvent> addSub = ((conn, event) -> lobbyView.addUser(event.getUsername()));
        UserEventSubscriber<ExitLobbyEvent> remSub = ((conn, event) -> lobbyView.removeUser(event.getUsername()));
        UserEventSubscriber<LobbyTimerStartEvent> timerStartSub = ((conn, event) -> lobbyView.startTimer(event.getSecondsLeft()));
        UserEventSubscriber<LobbyTimerUpdateEvent> timerUpdateSub = ((conn, event) -> lobbyView.syncTimer(event.getSecondsLeft()));
        UserEventSubscriber<LobbyTimerAbortEvent> timerAbortSub = ((conn, event) -> lobbyView.abortTimer());

        clientConnection.subscribeEvent(EnterLobbyEvent.class, addSub);
        clientConnection.subscribeEvent(ExitLobbyEvent.class, remSub);
        clientConnection.subscribeEvent(LobbyTimerStartEvent.class, timerStartSub);
        clientConnection.subscribeEvent(LobbyTimerUpdateEvent.class, timerUpdateSub);
        clientConnection.subscribeEvent(LobbyTimerAbortEvent.class, timerAbortSub);

        UserEventLocker<GameStartEvent> gameStartLocker = new UserEventLocker<>();
        try {
            gameStartLocker.waitOnEvent(GameStartEvent.class, clientConnection);
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
            throw new ClosedException("Close requested.");
        }
        lobbyView.startGame();

        clientConnection.unsubscribeEvent(EnterLobbyEvent.class, addSub);
        clientConnection.unsubscribeEvent(ExitLobbyEvent.class, remSub);
        clientConnection.unsubscribeEvent(LobbyTimerStartEvent.class, timerStartSub);
        clientConnection.unsubscribeEvent(LobbyTimerUpdateEvent.class, timerUpdateSub);
        clientConnection.unsubscribeEvent(LobbyTimerAbortEvent.class, timerAbortSub);

    }

    private void playGame() {
        gameManager = new GameClientManager(viewMaker, clientConnection);

        gameManager.loadData();
        gameManager.linkViews();

        UserEventLocker<GameEndEvent> gameEndLocker = new UserEventLocker<>();
        try {
            gameEndLocker.waitOnEvent(GameEndEvent.class, clientConnection);
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
            throw new ClosedException("Close requested.");
        }

    }

    @Override
    public void close() {
        if (clientConnection != null) {
            clientConnection.close();
        }

        clientThread.interrupt();
        if (gameManager != null) gameManager.interrupt();
    }

    @Override
    public void userDidChangeConnection(User user, UserConnection oldConnection, UserConnection newConnection) {
        if (!user.isConnected() && closeOnConnection) {
            clientThread.interrupt();
            viewMaker.notifyDisconnection();

            if (gameManager != null) gameManager.interrupt();
        }
    }

    @Override
    public void userDidChangeName(User user, String name) {
        // nothing to do here.
    }

}
