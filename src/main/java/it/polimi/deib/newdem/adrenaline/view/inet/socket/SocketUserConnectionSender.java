package it.polimi.deib.newdem.adrenaline.view.inet.socket;

import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.events.PlayerReconnectEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;

import java.io.DataOutputStream;
import java.io.IOException;

import static it.polimi.deib.newdem.adrenaline.view.inet.socket.SocketMessage.*;

public class SocketUserConnectionSender implements UserConnectionSender {

    private DataOutputStream output;
    private static final String IO_ERROR_MSG = "An I/O error occurred during the socket writing.";

    public SocketUserConnectionSender(DataOutputStream output) {
        this.output = output;
    }



    @Override
    public void sendUpdateUsernameEvent(UpdateUsernameEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.UPDATE_USER_NAME);

            StreamHelper.writeString(output, event.getNewUsername());

        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }


    @Override
    public void sendEnterLobbyEvent(EnterLobbyEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.ENTER_LOBBY);

            StreamHelper.writeString(output, event.getUsername());

        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendExitLobbyEvent(ExitLobbyEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.EXIT_LOBBY);

            StreamHelper.writeString(output, event.getUsername());

        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendLobbyTimerUpdateEvent(LobbyTimerUpdateEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.LOBBY_TIMER_UPDATE);

            output.writeInt(event.getSecondsLeft());

        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendMovePlayerEvent(MovePlayerEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.MOVE_PLAYER);

            StreamHelper.writePlayerColor(output, event.getPlayerColor());
            StreamHelper.writeTilePosition(output,event.getDestination());


        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendSpawnPlayerEvent(SpawnPlayerEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.SPAWN_PLAYER);

            StreamHelper.writePlayerColor(output, event.getPlayerColor());
            StreamHelper.writeTilePosition(output,event.getSpawnPoint());

        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendSpawnDropEvent(SpawnDropEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.SPAWN_DROP);

            StreamHelper.writeTilePosition(output, event.getTilePosition());

            StreamHelper.writeDropType(output, event.getDrop1());
            StreamHelper.writeDropType(output, event.getDrop2());
            StreamHelper.writeDropType(output, event.getDrop3());


        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendDeathPlayerEvent(DeathPlayerEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.DEATH_PLAYER);

            StreamHelper.writePlayerColor(output, event.getPlayerColor());


        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendLeaveMapPlayerEvent(LeaveMapPlayerEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.LEAVE_MAP_PLAYER);

            StreamHelper.writePlayerColor(output, event.getPlayerColor());


        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendMapTileDataEvent(MapTileDataEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.MAP_TILE_DATA);

            StreamHelper.writeTileList(output, event.getTileDataPosition());


        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendMapSpawnPointDataEvent(MapSpawnPointDataEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.MAP_SPAWNPOINT_DATA);

            StreamHelper.writeTileList(output, event.getSpawnPointTileDataPosition());


        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendGameStartEvent(GameStartEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.GAME_START);
        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendGameEndEvent(GameEndEvent event) throws ConnectionException {
        try {
            output.writeInt(SocketMessage.GAME_END);
        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendSpawnWeaponEvent(SpawnWeaponEvent event) throws ConnectionException {
        try{
            output.writeInt(SocketMessage.SPAWN_WEAPON);

            StreamHelper.writeTilePosition(output, event.getTilePosition());
            output.writeInt(event.getWeaponCardID());

        } catch (IOException x) {
            throw new ConnectionException("An I/O error occurred during the socket writing.");
        }
    }

    @Override
    public void sendPlayerDamageEvent(PlayerDamageEvent event) throws ConnectionException {
        try{
            output.writeInt(PLAYER_DAMAGE);

            StreamHelper.writePlayerColor(output, event.getAttacker()); // attacker
            StreamHelper.writePlayerColor(output, event.getDamagedPlayer()); // defender
            output.writeInt(event.getDamageAmount()); // dmg amount
            output.writeInt(event.getMarkAmount()); // mrk amount
        }
        catch (IOException e) {
            throw new ConnectionException(IO_ERROR_MSG);
        }
    }

    @Override
    public void sendPlayerConvertMarksEvent(PlayerConvertMarksEvent event) throws ConnectionException {
        try {
            output.writeInt(PLAYER_CONVERT_MARKS);

            StreamHelper.writePlayerColor(output, event.getDamagedPlayer());
        }
        catch (IOException e) {
            throw new ConnectionException(IO_ERROR_MSG);
        }
    }

    @Override
    public void sendPlayerDiscardPowerUpEvent(PlayerDiscardPowerUpEvent event) throws ConnectionException {
        //TODO
    }

    @Override
    public void sendDiscardWeaponEvent(PlayerDiscardWeaponEvent event) throws ConnectionException {
        //TODO
    }

    @Override
    public void sendPlayerDidReceiveDamageEvent(PlayerDidReceiveDamageEvent event) throws ConnectionException {
        //TODO
    }

    @Override
    public void sendPlayerDidReceiveMarkEvent(PlayerDidReceiveMarkEvent event) throws ConnectionException {
        //TODO
    }

    @Override
    public void sendPlayerDidReceiveAmmoSetEvent(PlayerDidReceiveAmmoSetEvent event) throws ConnectionException {
        //TODO
    }

    @Override
    public void sendPlayerDidRemoveAmmoSetEvent(PlayerDidRemoveAmmoSetEvent event) throws ConnectionException {
        //TODO
    }

    @Override
    public void sendAcquiredDropEvent(AcquireDropEvent event) throws ConnectionException {
        //TODO
    }

    @Override
    public void sendPlayerNameEvent(PlayerNameEvent event) throws ConnectionException {
        try{
            output.writeInt(PLAYER_NAME);

            StreamHelper.writePlayerColor(output, event.getPlayerColor());
            StreamHelper.writeString(output, event.getNewName());
        }
        catch (IOException e) {
            throw new ConnectionException(IO_ERROR_MSG);
        }
    }

    @Override
    public void sendPlayerActiveEvent(PlayerActiveEvent event) throws ConnectionException {
        // TODO
        try{
            output.writeInt(PLAYER_ACTIVE);

            StreamHelper.writePlayerColor(output, event.getPlayerColor());
        }
        catch (IOException e) {
            throw new ConnectionException(IO_ERROR_MSG);
        }
    }

    @Override
    public void sendPlayerScoreEvent(PlayerScoreEvent event) throws ConnectionException {
        // TODO
        try{
            output.writeInt(PLAYER_SCORE);

            StreamHelper.writePlayerColor(output, event.getPlayerColor());
            output.writeInt(event.getNewScore());
        }
        catch (IOException e) {
            throw new ConnectionException(IO_ERROR_MSG);
        }
    }

    @Override
    public void sendPlayerAcquirePowerUpEvent(PlayerAcquirePowerUpEvent event) throws ConnectionException {
        try {
            output.writeInt(PLAYER_ACQUIRE_POWERUP);

            output.writeInt( event.getPowerUpCardID() );
        }
        catch (IOException e) {
            throw new ConnectionException(IO_ERROR_MSG);
        }
    }

    @Override
    public void sendPlayerAcquireWeaponEvent(PlayerAcquireWeaponEvent event) throws ConnectionException {
        try{
            output.writeInt(PLAYER_ACQUIRE_WEAPON);

            output.writeInt(event.getWeaponCardID());
        }
        catch (IOException e) {
            throw new ConnectionException(IO_ERROR_MSG);
        }
    }

    @Override
    public void sendDamageBoardFlipEvent(DamageBoardFlipEvent event) throws ConnectionException {
        try{
            output.writeInt(SocketMessage.DAMAGE_BOARD_FLIP);

            StreamHelper.writePlayerColor(output, event.getColor());
        }
        catch (IOException e) {
            throw new ConnectionException(IO_ERROR_MSG);
        }
    }

    @Override
    public void sendKillTrackAddKillEvent(KillTrackAddKillEvent event) throws ConnectionException {
        try{
            output.writeInt(SocketMessage.KILLTRACK_ADD_KILL);

            StreamHelper.writePlayerColor(output, event.getColor());
            output.writeInt(event.getAmount());
        }
        catch (IOException e) {
            throw new ConnectionException(IO_ERROR_MSG);
        }
    }

    @Override
    public void sendPlayerDisconnectEvent(PlayerDisconnectEvent event) throws ConnectionException {
        // TODO
    }

    @Override
    public void sendPlayerReconnectEvent(PlayerReconnectEvent event) throws ConnectionException {
        // TODO
    }

    @Override
    public void sendPlayerSelectionRequest(PlayerSelectionRequest event) throws ConnectionException {
        // TODO
    }
}
