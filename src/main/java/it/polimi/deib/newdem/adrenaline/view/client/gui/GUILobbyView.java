package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.view.LobbyView;
import javafx.application.Platform;

import java.util.List;

public class GUILobbyView implements LobbyView {

    private GUILobbyWindow lobbyWindow;

    public GUILobbyView(GUILobbyWindow lobbyWindow, List<String> userData) {
        this.lobbyWindow = lobbyWindow;

        Platform.runLater(() -> {
            for (String name : userData) {
                lobbyWindow.addUser(name);
            }
        });
    }

    @Override
    public void addUser(String name) {
        Platform.runLater(() -> lobbyWindow.addUser(name));
    }

    @Override
    public void removeUser(String name) {
        Platform.runLater(() -> lobbyWindow.removeUser(name));
    }

    @Override
    public void startTimer(int seconds) {
        Platform.runLater(() -> lobbyWindow.startTimer(seconds));
    }

    @Override
    public void syncTimer(int seconds) {
        Platform.runLater(() -> lobbyWindow.syncTimer(seconds));
    }

    @Override
    public void abortTimer() {
        Platform.runLater(() -> lobbyWindow.abortTimer());
    }

    @Override
    public void startGame() {
        Platform.runLater(() -> lobbyWindow.hide());
    }

}
