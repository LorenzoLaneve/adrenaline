package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.view.GameView;
import it.polimi.deib.newdem.adrenaline.view.LobbyView;
import it.polimi.deib.newdem.adrenaline.view.ConnectionView;
import it.polimi.deib.newdem.adrenaline.view.UsernameView;
import it.polimi.deib.newdem.adrenaline.view.client.ViewMaker;
import it.polimi.deib.newdem.adrenaline.view.inet.events.LobbyDataEvent;
import javafx.application.Application;

public class GUIViewMaker implements ViewMaker {

    private final Application application;

    private GUILobbyWindow lobbyWindow;

    private GUIGameWindow gameWindow;

    public GUIViewMaker(Application application) {
        this.application = application;
    }

    @Override
    public ConnectionView makeConnectionView() {
        return new GUIConnectionView();
    }

    @Override
    public UsernameView makeUsernameView() {
        GUIUsernameView newView = new GUIUsernameView();

        lobbyWindow = new GUILobbyWindow(newView);
        newView.setWindow(lobbyWindow);
        return newView;
    }

    @Override
    public LobbyView makeLobbyView(LobbyDataEvent lobbyData) {
        return new GUILobbyView(lobbyWindow, lobbyData.getUsers());
    }

    @Override
    public GameView makeGameView() {
        this.gameWindow = new GUIGameWindow();

        return new GUIGameView(gameWindow);
    }

}
