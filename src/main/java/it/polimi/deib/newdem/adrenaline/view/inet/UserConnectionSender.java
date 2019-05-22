package it.polimi.deib.newdem.adrenaline.view.inet;

import it.polimi.deib.newdem.adrenaline.view.inet.events.*;
import it.polimi.deib.newdem.adrenaline.view.inet.events.PlayerScoreEvent;

public interface UserConnectionSender {

    void sendUpdateUsernameEvent(UpdateUsernameEvent event) throws ConnectionException;

    void sendEnterLobbyEvent(EnterLobbyEvent event) throws ConnectionException;

    void sendExitLobbyEvent(ExitLobbyEvent event) throws ConnectionException;

    void sendLobbyTimerUpdateEvent(LobbyTimerUpdateEvent event) throws ConnectionException;

    void sendMovePlayerEvent(MovePlayerEvent event) throws  ConnectionException;

    void sendSpawnPlayerEvent(SpawnPlayerEvent event) throws ConnectionException;

    void sendSpawnDropEvent(SpawnDropEvent event) throws ConnectionException;

    void sendDeathPlayerEvent(DeathPlayerEvent event) throws ConnectionException;

    void sendLeaveMapPlayerEvent(LeaveMapPlayerEvent event) throws ConnectionException;

    void sendMapTileDataEvent(MapTileDataEvent event) throws ConnectionException;

    void sendMapSpawnPointDataEvent(MapSpawnPointDataEvent event) throws  ConnectionException;

    void sendGameStartEvent(GameStartEvent event) throws ConnectionException;

    void sendGameEndEvent(GameEndEvent event) throws ConnectionException;

    void sendPlayerNameEvent(PlayerNameEvent event) throws ConnectionException;

    void sendPlayerActiveEvent(PlayerActiveEvent event) throws ConnectionException;

    void sendPlayerScoreEvent(PlayerScoreEvent event) throws ConnectionException;

    void sendPlayerAcquirePowerUpEvent(PlayerAcquirePowerUpEvent event) throws ConnectionException;

    void sendPlayerAcquireWeaponEvent(PlayerAcquireWeaponEvent event) throws ConnectionException;

    void sendSpawnWeaponEvent(SpawnWeaponEvent spawnWeaponEvent) throws ConnectionException;
}
