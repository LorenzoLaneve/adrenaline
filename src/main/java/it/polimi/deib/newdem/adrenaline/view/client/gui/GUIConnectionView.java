package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.view.client.ClosedException;
import it.polimi.deib.newdem.adrenaline.view.ConnectionView;
import javafx.application.Platform;

/**
 * Java FX implementation of {@code ConnectionView}
 * @see ConnectionView for the semantics of the view methods.
 */
public class GUIConnectionView implements ConnectionView, GUIConnectionPromptReceiver {

    private String serverHost;
    private int serverPort;

    private ConnectionType connectionType;

    private boolean dataDelivered;

    private boolean windowShown;

    private GUIConnectionWindow window;


    public GUIConnectionView() {
        this.windowShown = false;
    }

    @Override
    public String getServerHost() {
        return serverHost;
    }

    @Override
    public int getServerPort() {
        return serverPort;
    }

    @Override
    public ConnectionType getConnectionType() {
        return connectionType;
    }

    @Override
    public synchronized void prompt() {
        dataDelivered = false;

        if (!windowShown) {
            Platform.runLater(this::openWindow);
            windowShown = true;
        }

        try {
            while (!dataDelivered) wait();
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
            throw new ClosedException("Close requested by JavaFX.");
        }
    }

    @Override
    public void reportError(String message) {
        Platform.runLater(() -> window.showError(message));
    }

    @Override
    public void reportSuccess() {
        Platform.runLater(this::closeWindow);
    }


    private void openWindow() {
        this.window = new GUIConnectionWindow(this);
        this.window.show();
    }

    private void closeWindow() {
        this.window.hide();
        this.window = null;
    }

    @Override
    public synchronized void deliverData(String host, int port, ConnectionType cType) {
        dataDelivered = true;

        this.serverHost = host;
        this.serverPort = port;
        this.connectionType = cType;

        notifyAll();
    }

}
