package it.polimi.deib.newdem.adrenaline.view.client;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.*;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserEventLocker;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;

import java.util.EnumMap;

public class GameClientManager {

    private PlayerColor currentPlayer;

    private ViewMaker viewMaker;

    private UserConnection connection;

    private GameView gameView;
    private MapView mapView;
    private KillTrackView killTrackView;

    private EnumMap<PlayerColor, PlayerView> playerViews;
    private EnumMap<PlayerColor, ActionBoardView> actionBoardViews;
    private EnumMap<PlayerColor, DamageBoardView> damageBoardViews;

    private Thread turnThread;


    private <T extends UserEvent> T waitForEvent(Class<T> eventClass) {
        UserEventLocker<T> eventLocker = new UserEventLocker<>();
        try {
            return eventLocker.waitOnEvent(eventClass, connection);
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
            throw new ClosedException("Close requested.");
        }
    }


    public GameClientManager(ViewMaker viewMaker, UserConnection connection) {
        this.viewMaker = viewMaker;
        this.connection = connection;
    }

    private PlayerView getPlayerView(PlayerColor color) {
        return playerViews.get(color);
    }

    private ActionBoardView getActionView(PlayerColor color) {
        return actionBoardViews.get(color);
    }

    private DamageBoardView getDamageBoard(PlayerColor color) {
        return damageBoardViews.get(color);
    }

    public void loadData() {
        gameView = viewMaker.makeGameView();
        mapView = viewMaker.makeMapView();
        killTrackView = viewMaker.makeKillTrackView();

        playerViews = new EnumMap<>(PlayerColor.class);
        actionBoardViews = new EnumMap<>(PlayerColor.class);
        damageBoardViews = new EnumMap<>(PlayerColor.class);

        GameData gameData = waitForEvent(GameDataEvent.class).getData();
        gameView.setGameData(gameData);

        for (GameData.UserColorPair player : gameData.getPlayers()) {
            playerViews.put(player.getColor(), viewMaker.makePlayerView(player.getColor()));
            actionBoardViews.put(player.getColor(), viewMaker.makeActionBoardView(player.getColor()));
            damageBoardViews.put(player.getColor(), viewMaker.makeDamageBoardView(player.getColor()));
        }

        killTrackView.restoreView(waitForEvent(KillTrackDataEvent.class).getData());
        mapView.updateView(waitForEvent(MapDataEvent.class).getData());

        connection.subscribeEvent(PlayerDataEvent.class, (conn, e) -> playerViews.get(e.getData().getColor()).setPlayerData(e.getData()));
    }

    public void linkViews() {
        // FIXME ViewLinker class that links views to connection events.

        connection.subscribeEvent(PlayerDisconnectEvent.class, (conn, event) ->
                gameView.disablePlayer(event.getPlayerColor()));
        connection.subscribeEvent(PlayerReconnectEvent.class, (conn, event) ->
                gameView.enablePlayer(event.getPlayerColor()));

        connection.subscribeEvent(SpawnDropEvent.class, (conn, e) ->
                mapView.addDrops(e.getTilePosition(), e.getDrop1(), e.getDrop2(), e.getDrop3()));
        connection.subscribeEvent(DespawnDropEvent.class, (conn, e) ->
                mapView.removeDrops(e.getTilePosition()));
        connection.subscribeEvent(MovePlayerEvent.class, (conn, e) ->
                mapView.movePlayer(e.getPlayerColor(), e.getDestination()));
        connection.subscribeEvent(SpawnPlayerEvent.class, (conn, e) ->
                mapView.spawnPlayer(e.getPlayerColor(), e.getSpawnPoint()));
        connection.subscribeEvent(DeathPlayerEvent.class, (conn, e) ->
                mapView.killPlayer(e.getPlayerColor()));
        connection.subscribeEvent(LeaveMapPlayerEvent.class, (conn, e) ->
                mapView.removePlayer(e.getPlayerColor()));
        connection.subscribeEvent(SpawnWeaponEvent.class, (conn, e) ->
                mapView.addWeapon(e.getTilePosition(), e.getWeaponCardID()));
        connection.subscribeEvent(DespawnWeaponEvent.class, (conn, e) ->
                mapView.removeWeapon(e.getTilePosition(), e.getCardID()));

        connection.subscribeEvent(PlayerScoreEvent.class, (conn, e) ->
                getPlayerView(e.getPlayerColor()).setScore(e.getNewScore()));
        connection.subscribeEvent(PlayerAcquirePowerUpEvent.class, (conn, e) ->
                getPlayerView(e.getPlayerColor()).addPowerUpCard(e.getPowerUpCardID()));
        connection.subscribeEvent(PlayerDiscardPowerUpEvent.class, (conn, e) ->
                getPlayerView(e.getColor()).removePowerUpCard(e.getCardID()));
        connection.subscribeEvent(PlayerAcquireWeaponEvent.class, (conn, e) ->
                getPlayerView(e.getPlayerColor()).addWeaponCard(e.getWeaponCardID()));
        connection.subscribeEvent(PlayerDiscardWeaponEvent.class, (conn, e) ->
                getPlayerView(e.getPlayer()).removeWeaponCard(e.getCardID()));
        connection.subscribeEvent(PlayerReceiveAmmoEvent.class, (conn, e) ->
                getPlayerView(e.getPlayer()).addAmmoSet(e.getYellowAmount(), e.getRedAmount(), e.getBlueAmount()));
        connection.subscribeEvent(PlayerDiscardAmmoEvent.class, (conn, e) ->
                getPlayerView(e.getPlayer()).removeAmmoSet(e.getYellowAmount(), e.getRedAmount(), e.getBlueAmount()));
        connection.subscribeEvent(WeaponUnloadEvent.class, (conn, e) ->
                getPlayerView(e.getOwnerColor()).unloadWeaponCard(e.getCardID()));
        connection.subscribeEvent(WeaponReloadEvent.class, (conn, e) ->
                getPlayerView(e.getOwnerColor()).reloadWeaponCard(e.getCardID()));

        connection.subscribeEvent(ActionBoardFlipEvent.class, (conn, e) ->
                getActionView(e.getColor()).flipActionBoard());

        connection.subscribeEvent(PlayerDamageEvent.class, (conn, e) ->
                getDamageBoard(e.getDamagedPlayer()).registerDamage(e.getDamageAmount(), e.getMarkAmount(), e.getAttacker()));
        connection.subscribeEvent(PlayerPopDamageEvent.class, (conn, e) ->
                getDamageBoard(e.getColor()).popDamage());
        connection.subscribeEvent(DamageBoardFlipEvent.class, (conn, e) ->
                getDamageBoard(e.getColor()).goFrenzy());
        connection.subscribeEvent(DamageBoardClearEvent.class, (conn, e) ->
                getDamageBoard(e.getPlayerColor()).clearBoard());

        connection.subscribeEvent(KillTrackAddKillEvent.class, (conn, e) ->
                killTrackView.registerKill(e.getColor(), e.getAmount()));
        connection.subscribeEvent(KillTrackUndoKillEvent.class, (conn, e) ->
                killTrackView.undoLastKill());

        connection.subscribeEvent(TurnStartEvent.class, (conn, e) -> handleTurn(e.getTurnActor()));
    }


    private void handleTurn(PlayerColor actor) {
        turnThread = Thread.currentThread();

        TurnView tView = viewMaker.makeTurnView();

        TurnClientManager turnManager = new TurnClientManager(connection, tView, actor);
        turnManager.start();
    }

    public void interrupt() {
        if (turnThread != null) {
            turnThread.interrupt();
        }
    }

    public void waitForEnd() {
        UserEventLocker<GameEndEvent> gameEndLocker = new UserEventLocker<>();

        GameEndEvent event;
        try {
            event = gameEndLocker.waitOnEvent(GameEndEvent.class, connection);
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
            throw new ClosedException("Close requested.");
        }

        gameView.endGame(event.getResults());
    }
}
