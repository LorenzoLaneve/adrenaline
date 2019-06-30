package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.*;
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

    @Override
    public MapView makeMapView() {
        return new GUIMapView(gameWindow);
    }

    @Override
    public KillTrackView makeKillTrackView() {
        return new GUIKillTrackView(gameWindow);
    }

    @Override
    public PlayerView makePlayerView(PlayerColor playerColor) {
        return new GUIPlayerView(gameWindow, playerColor);
    }

    @Override
    public DamageBoardView makeDamageBoardView(PlayerColor playerColor) {
        return new GUIDamageBoardView(gameWindow, playerColor);
    }

    @Override
    public ActionBoardView makeActionBoardView(PlayerColor color) {
        return new GUIActionBoardView(gameWindow, color);
    }

    @Override
    public TurnView makeTurnView() {
        return new GUITurnView(gameWindow);
    }

}
