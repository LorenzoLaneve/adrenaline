package it.polimi.deib.newdem.adrenaline.view.inet.socket;

import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;

import java.io.DataOutputStream;
import java.io.IOException;

public class SocketUserConnectionSender implements UserConnectionSender {

    private DataOutputStream output;


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
    public void sendPlayerNameEvent(PlayerNameEvent event) throws ConnectionException {
        // TODO
    }

    @Override
    public void sendPlayerActiveEvent(PlayerActiveEvent event) throws ConnectionException {
        // TODO
    }

    @Override
    public void sendPlayerScoreEvent(PlayerScoreEvent event) throws ConnectionException {
        // TODO
    }

    @Override
    public void sendPlayerAcquirePowerUpEvent(PlayerAcquirePowerUpEvent event) throws ConnectionException {
        // TODO
    }

    @Override
    public void sendPlayerAcquireWeaponEvent(PlayerAcquireWeaponEvent event) throws ConnectionException {
        // TODO
    }

}
