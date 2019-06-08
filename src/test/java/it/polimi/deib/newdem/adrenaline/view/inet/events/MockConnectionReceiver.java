package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

public class MockConnectionReceiver implements UserConnectionReceiver {

    @Override
    public void userDidEnterLobby(UserConnection connection, EnterLobbyEvent event) {

    }

    @Override
    public void userDidExitLobby(UserConnection connection, ExitLobbyEvent event) {

    }

    @Override
    public void lobbyDidUpdateTimer(UserConnection connection, LobbyTimerUpdateEvent event) {

    }

    @Override
    public void connectionDidClose(UserConnection connection) {

    }

    @Override
    public void playerDidMove(UserConnection connection, MovePlayerEvent event) {

    }

    @Override
    public void playerDidSpawn(UserConnection connection, SpawnPlayerEvent event) {

    }

    @Override
    public void dropDidSpawn(UserConnection connection, SpawnDropEvent event) {

    }

    @Override
    public void playerDidDie(UserConnection connection, DeathPlayerEvent event) {

    }

    @Override
    public void playerDidLeaveMap(UserConnection connection, LeaveMapPlayerEvent event) {

    }

    @Override
    public void mapDidSendTileData(UserConnection connection, MapTileDataEvent event) {

    }

    @Override
    public void mapDidSendSpawnPointData(UserConnection connection, MapSpawnPointDataEvent event) {

    }

    @Override
    public void gameWillStart(UserConnection connection, GameStartEvent event) {

    }

    @Override
    public void gameWillEnd(UserConnection connection, GameEndEvent event) {

    }

    @Override
    public void playerDidBecomeActive(UserConnection connection, PlayerActiveEvent event) {

    }

    @Override
    public void playerDidUpdateScore(UserConnection connection, PlayerScoreEvent event) {

    }

    @Override
    public void playerDidAcquirePowerUp(UserConnection connection, PlayerAcquirePowerUpEvent event) {

    }

    @Override
    public void playerDidAcquireWeapon(UserConnection connection, PlayerAcquireWeaponEvent event) {

    }

    @Override
    public void weaponDidSpawn(UserConnection connection, SpawnWeaponEvent event) {

    }

    @Override
    public void playerDidTakeDamage(UserConnection connection, PlayerDamageEvent event) {

    }

    @Override
    public void playerDidConvertMarks(UserConnection connection, PlayerConvertMarksEvent event) {

    }

    @Override
    public void playerDidRemovePowerUp(UserConnection connection, PlayerDiscardPowerUpEvent event) {

    }

    @Override
    public void playerDidRemoveWeapon(UserConnection connection, PlayerDiscardWeaponEvent event) {

    }

    @Override
    public void damageBoardDidFlip(UserConnection connection, DamageBoardFlipEvent event) {

    }

    @Override
    public void killTrackDidAddTear(UserConnection connection, KillTrackAddKillEvent event) {

    }

    @Override
    public void playerDidDisconnect(UserConnection connection, PlayerDisconnectEvent event) {

    }

    @Override
    public void playerDidReconnect(UserConnection connection, PlayerReconnectEvent event) {

    }

    @Override
    public void gameDidRequestPlayer(UserConnection connection, PlayerSelectionRequest request) {

    }

    @Override
    public void serverDidRejectUsername(UserConnection connection, RejectUsernameEvent event) {

    }

    @Override
    public void serverDidRequestUsername(UserConnection connection, UpdateUsernameRequest request) {

    }

    @Override
    public void lobbyDidAbortTimer(UserConnection connection, LobbyTimerAbortEvent event) {

    }

    @Override
    public void lobbyDidStartTimer(UserConnection connection, LobbyTimerStartEvent event) {

    }

    @Override
    public void lobbyDidSendData(UserConnection connection, LobbyDataEvent event) {

    }

    @Override
    public void userDidUpdateUsername(UserConnection connection, UpdateUsernameResponse event) {

    }

    @Override
    public void playerDidReceiveAmmoSet(UserConnection connection, PlayerReceiveAmmoEvent event) {

    }

    @Override
    public void playerDidRemoveAmmoSet(UserConnection connection, PlayerDiscardAmmoEvent event) {

    }

    @Override
    public void playerDidPickupDrop(UserConnection connection, DropPickupEvent event) {

    }

    @Override
    public void playerDidUpdateName(UserConnection connection, PlayerNameEvent event) {

    }
}
