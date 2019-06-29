package it.polimi.deib.newdem.adrenaline.view.client;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerData;
import it.polimi.deib.newdem.adrenaline.model.map.MapData;
import it.polimi.deib.newdem.adrenaline.view.GameView;
import it.polimi.deib.newdem.adrenaline.view.MapView;
import it.polimi.deib.newdem.adrenaline.view.PlayerView;
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

    private EnumMap<PlayerColor, PlayerView> playerViews;


    private <T extends UserEvent> T waitForEvent(Class<T> eventClass) {
        UserEventLocker<T> gameDataLocker = new UserEventLocker<>();
        try {
            return gameDataLocker.waitOnEvent(eventClass, connection);
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

    public void loadData() {
        gameView = viewMaker.makeGameView();
        mapView = viewMaker.makeMapView();
        playerViews = new EnumMap<>(PlayerColor.class);

        GameData gameData = waitForEvent(GameDataEvent.class).getData();
        gameView.setGameData(gameData);

        for (GameData.UserColorPair player : gameData.getPlayers())
            playerViews.put(player.getColor(), viewMaker.makePlayerView(player.getColor()));

        connection.subscribeEvent(PlayerDataEvent.class, (conn, e) -> playerViews.get(e.getData().getColor()).setPlayerData(e.getData()));

        mapView.updateView(waitForEvent(MapDataEvent.class).getData());
    }

    public void linkViews() {
        connection.subscribeEvent(PlayerDisconnectEvent.class, (conn, event) ->
                gameView.disablePlayer(event.getPlayerColor()));
        connection.subscribeEvent(PlayerReconnectEvent.class, (conn, event) ->
                gameView.enablePlayer(event.getPlayerColor()));

        connection.subscribeEvent(SpawnDropEvent.class, (conn, e) ->
                mapView.addDrops(e.getTilePosition(), e.getDrop1(), e.getDrop2(), e.getDrop3()));
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
        connection.subscribeEvent(DropPickupEvent.class, (conn, e) ->
                mapView.acquireDrop(e.getTilePosition(), e.getPlayer(), e.getDrop1(), e.getDrop2(), e.getDrop3()));

        connection.subscribeEvent(PlayerActiveEvent.class, (conn, e) ->
                getPlayerView(e.getPlayerColor()).takeControl());
        connection.subscribeEvent(PlayerScoreEvent.class, (conn, e) ->
                getPlayerView(e.getPlayerColor()).setScore(e.getNewScore()));
        connection.subscribeEvent(PlayerAcquirePowerUpEvent.class, (conn, e) ->
                getPlayerView(e.getPlayerColor()).addPowerUpCard(e.getPowerUpCardID()));
        connection.subscribeEvent(PlayerDiscardPowerUpEvent.class, (conn, e) ->
                getPlayerView(e.getColor()).removePowerUpCard(e.getCardID()));
        connection.subscribeEvent(PlayerAcquireWeaponEvent.class, (conn, e) ->
                getPlayerView(e.getPlayerColor()).addWeaponCard(e.getWeaponCardID()));
        connection.subscribeEvent(PlayerDiscardWeaponEvent.class, (conn, e) ->
                getPlayerView(e.getPlayer()).removePowerUpCard(e.getCardID()));
        connection.subscribeEvent(PlayerReceiveAmmoEvent.class, (conn, e) ->
                getPlayerView(e.getPlayer()).addAmmoSet(e.getYellowAmount(), e.getRedAmount(), e.getBlueAmount()));
        connection.subscribeEvent(PlayerDiscardAmmoEvent.class, (conn, e) ->
                getPlayerView(e.getPlayer()).removeAmmoSet(e.getYellowAmount(), e.getRedAmount(), e.getBlueAmount()));

        connection.subscribeEvent(TurnStartEvent.class, (conn, e) -> setupTurn());
    }


    private void setupTurn() {



    }

}
