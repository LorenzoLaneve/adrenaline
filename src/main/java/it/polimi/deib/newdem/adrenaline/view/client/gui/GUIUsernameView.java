package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.view.client.ClosedException;
import it.polimi.deib.newdem.adrenaline.view.UsernameView;
import javafx.application.Platform;

/**
 * Java FX implementation of {@code UsernameView}
 * @see UsernameView for the semantics of the view methods.
 */
public class GUIUsernameView implements UsernameView, GUIUsernamePromptReceiver {

    private GUILobbyWindow window;

    private boolean windowShown;

    private String username;


    public GUIUsernameView() {
        this.windowShown = false;
    }

    public void setWindow(GUILobbyWindow window) {
        this.window = window;
    }


    @Override
    public synchronized String prompt() {
        username = null;

        if (!windowShown) {
            Platform.runLater(this::openWindow);
            windowShown = true;
        }

        try {
            while (username == null) wait();
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
            throw new ClosedException("Close requested by JavaFX.");
        }

        return username;
    }

    private void openWindow() {
        this.window.show();
    }

    @Override
    public void reportError(String message) {
        Platform.runLater(() -> window.showUsernameError(message));
    }

    @Override
    public void reportSuccess() {
        Platform.runLater(() -> window.closeUsernameForm());
    }

    @Override
    public synchronized void deliverUsername(String username) {
        this.username = username;
        notifyAll();
    }

}
