package it.polimi.deib.newdem.adrenaline.view.inet;

import it.polimi.deib.newdem.adrenaline.view.inet.events.*;
import it.polimi.deib.newdem.adrenaline.view.inet.events.PlayerScoreEvent;
import it.polimi.deib.newdem.adrenaline.view.server.*;

public interface UserConnectionReceiver {


    /**
     * The user sent an update username event.
     * @param connection the connection that received the event.
     * @param event an {@code UpdateUsernameEvent} object containing all the information about the event.
     */
    void userDidUpdateUsername(UserConnection connection, UpdateUsernameEvent event);

    /**
     * A new user entered the lobby.
     * @param connection the connection that received the event.
     * @param event an {@code EnterLobbyEvent} object containing all the information about the event.
     */
    void userDidEnterLobby(UserConnection connection, EnterLobbyEvent event);

    /**
     * A user left the lobby.
     * @param connection the connection that received the event.
     * @param event an {@code ExitLobbyEvent} object containing all the information about the event.
     */
    void userDidExitLobby(UserConnection connection, ExitLobbyEvent event);

    /**
     * The lobby timer has been updated.
     * @param connection the connection that received the event.
     * @param event an {@code LobbyTimerUpdateEvent} object containing all the information about the event.
     */
    void lobbyDidUpdateTimer(UserConnection connection, LobbyTimerUpdateEvent event);


    /**
     * Notifies that a connection has been closed, either by the local or the remote host.
     * @param connection the connection that has been closed.
     */
    void connectionDidClose(UserConnection connection);
    
    void playerDidMove(UserConnection connection, MovePlayerEvent event);
    
    void playerDidSpawn(UserConnection connection, SpawnPlayerEvent event);

    void dropDidSpawn(UserConnection connection, SpawnDropEvent event);

    void playerDidDie(UserConnection connection, DeathPlayerEvent event);

    void playerDidLeaveMap(UserConnection connection, LeaveMapPlayerEvent event);

    void mapDidSendTileData(UserConnection connection, MapTileDataEvent event);

    void mapDidSendSpawnPointData(UserConnection connection, MapSpawnPointDataEvent event);

    void gameWillStart(UserConnection connection, GameStartEvent event);

    void gameWillEnd(UserConnection connection, GameEndEvent event);

    void playerDidUpdateUsername(UserConnection connection, PlayerNameEvent event);

    void playerDidBecomeActive(UserConnection connection, PlayerActiveEvent event);

    void playerDidUpdateScore(UserConnection connection, PlayerScoreEvent event);

    void playerDidAcquirePowerUp(UserConnection connection, PlayerAcquirePowerUpEvent event);

    void playerDidAcquireWeapon(UserConnection connection, PlayerAcquireWeaponEvent event);

    void weaponDidSpawn(UserConnection connection, SpawnWeaponEvent event);

    void playerDidTakeDamage(UserConnection connection, PlayerDamageEvent event);

    void playerDidConvertMarks(UserConnection connection, PlayerConvertMarksEvent event);

    void playerDidRemovePowerUp(UserConnection connection, PlayerDiscardPowerUpEvent event);

    void playerDidRemoveWeapon(UserConnection connection, PlayerDiscardWeaponEvent event);

    void playerDidReceiveDamage(UserConnection connection, PlayerDidReceiveDamageEvent event);

    void playerDidReceiveMark(UserConnection connection, PlayerDidReceiveMarkEvent event);

    void playerDidReceiveAmmoSet(UserConnection connection, PlayerDidReceiveAmmoSetEvent event);

    void playerDidRemoveAmmoSet(UserConnection connection, PlayerDidRemoveAmmoSetEvent event);
}
