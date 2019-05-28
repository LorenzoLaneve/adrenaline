package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.view.inet.events.PlayerReconnectEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;

import java.util.ArrayList;
import java.util.List;

public class ServerConnectionReceiver implements UserConnectionReceiver {

    private List<MapViewEventListener> mapListeners;

    public ServerConnectionReceiver() {
        this.mapListeners = new ArrayList<>();
    }

    public void addMapListener(MapViewEventListener listener) {
        this.mapListeners.add(listener);
    }


    @Override
    public void userDidUpdateUsername(UserConnection connection, UpdateUsernameEvent event) {
        // TODO
    }

    @Override
    public void userDidEnterLobby(UserConnection connection, EnterLobbyEvent event) {
        // TODO
    }

    @Override
    public void userDidExitLobby(UserConnection connection, ExitLobbyEvent event) {
        // TODO
    }

    @Override
    public void lobbyDidUpdateTimer(UserConnection connection, LobbyTimerUpdateEvent event) {
        // TODO
    }

    @Override
    public void connectionDidClose(UserConnection connection) {
        // TODO
    }

    @Override
    public void playerDidMove(UserConnection connection, MovePlayerEvent event) {
        // TODO
    }

    @Override
    public void playerDidSpawn(UserConnection connection, SpawnPlayerEvent event) {
        // TODO
    }

    @Override
    public void dropDidSpawn(UserConnection connection, SpawnDropEvent event) {
        // TODO
    }

    @Override
    public void playerDidDie(UserConnection connection, DeathPlayerEvent event) {
        // TODO
    }

    @Override
    public void playerDidLeaveMap(UserConnection connection, LeaveMapPlayerEvent event) {
        // TODO
    }

    @Override
    public void mapDidSendTileData(UserConnection connection, MapTileDataEvent event) {
        // TODO
    }

    @Override
    public void mapDidSendSpawnPointData(UserConnection connection, MapSpawnPointDataEvent event) {
        // TODO
    }

    @Override
    public void gameWillStart(UserConnection connection, GameStartEvent event) {
        // TODO
    }

    @Override
    public void gameWillEnd(UserConnection connection, GameEndEvent event) {
        // TODO
    }

    @Override
    public void playerDidUpdateUsername(UserConnection connection, PlayerNameEvent event) {
        // TODO
    }

    @Override
    public void playerDidBecomeActive(UserConnection connection, PlayerActiveEvent event) {
        // TODO
    }

    @Override
    public void playerDidUpdateScore(UserConnection connection, PlayerScoreEvent event) {
        // TODO
    }

    @Override
    public void playerDidAcquirePowerUp(UserConnection connection, PlayerAcquirePowerUpEvent event) {
        // TODO
    }

    @Override
    public void playerDidAcquireWeapon(UserConnection connection, PlayerAcquireWeaponEvent event) {
        // TODO
    }

    @Override
    public void weaponDidSpawn(UserConnection connection, SpawnWeaponEvent event) {
        // TODO
    }

    @Override
    public void playerDidTakeDamage(UserConnection connection, PlayerDamageEvent event) {
        // TODO
    }

    @Override
    public void playerDidConvertMarks(UserConnection connection, PlayerConvertMarksEvent event) {
        // TODO
    }

    @Override
    public void playerDidRemovePowerUp(UserConnection connection, PlayerDiscardPowerUpEvent event) {
        // TODO
    }

    @Override
    public void playerDidRemoveWeapon(UserConnection connection, PlayerDiscardWeaponEvent event) {
        // TODO
    }

    @Override
    public void playerDidReceiveDamage(UserConnection connection, PlayerDidReceiveDamageEvent event) {
        // TODO
    }

    @Override
    public void playerDidReceiveMark(UserConnection connection, PlayerDidReceiveMarkEvent event) {
        // TODO
    }

    @Override
    public void playerDidReceiveAmmoSet(UserConnection connection, PlayerDidReceiveAmmoSetEvent event) {
        // TODO
    }

    @Override
    public void playerDidRemoveAmmoSet(UserConnection connection, PlayerDidRemoveAmmoSetEvent event) {
        // TODO
    }

    @Override
    public void dropDidGetAcquired(UserConnection connection, AcquireDropEvent event) {
        // TODO
    }

    @Override
    public void damageBoardDidFlip(UserConnection connection, DamageBoardFlipEvent event) {
        // TODO
    }

    @Override
    public void killTrackDidAddTear(UserConnection connection, KillTrackAddKillEvent event) {
        // TODO
    }

    @Override
    public void playerDidDisconnect(UserConnection connection, PlayerDisconnectEvent event) {
        // TODO
    }

    @Override
    public void playerDidReconnect(UserConnection connection, PlayerReconnectEvent event) {
        // TODO
    }

}
