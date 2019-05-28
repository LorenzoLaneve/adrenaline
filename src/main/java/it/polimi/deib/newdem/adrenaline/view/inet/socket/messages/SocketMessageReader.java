package it.polimi.deib.newdem.adrenaline.view.inet.socket.messages;

import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.socket.InvalidMessageException;
import it.polimi.deib.newdem.adrenaline.view.inet.socket.SocketMessage;

import java.io.DataInputStream;
import java.io.IOException;

public interface SocketMessageReader {

    /**
     * Creates a new UserEvent object representing the message read in the given input stream.
     * @throws IOException if an IO error occurs while the message is read.
     * @throws InvalidMessageException if the data in the stream does not follow the protocol.
     */
    UserEvent make(DataInputStream input) throws IOException, InvalidMessageException;


    /**
     * Creates an object that  a user event from a socket's stream.
     * @throws InvalidMessageException if the given type is not supported by the used protocol.
     * @see SocketMessage for possible message types.
     */
    static SocketMessageReader fromMessageType(int type) throws InvalidMessageException {
        switch (type) {
            case SocketMessage.DAMAGE_BOARD_FLIP:
                return new DamageBoardFlipReader();
            case SocketMessage.DEATH_PLAYER:
                return new DeathPlayerMessageReader();
            case SocketMessage.ENTER_LOBBY:
                return new EnterLobbyMessageReader();
            case SocketMessage.EXIT_LOBBY:
                return new ExitLobbyMessageReader();
            case SocketMessage.GAME_END:
                return new GameEndMessageReader();
            case SocketMessage.GAME_START:
                return new GameStartMessageReader();
            case SocketMessage.LEAVE_MAP_PLAYER:
                return new LeaveMapPlayerReader();
            case SocketMessage.LOBBY_TIMER_UPDATE:
                return new LobbyTimerUpdateReader();
            case SocketMessage.MAP_TILE_DATA:
                return new MapTileDataReader();
            case SocketMessage.MOVE_PLAYER:
                return new MovePlayerReader();
            case SocketMessage.PLAYER_ACQUIRE_POWERUP:
                return new PlayerAcquirePowerUpReader();
            case SocketMessage.PLAYER_ACQUIRE_WEAPON:
                return new PlayerAcquireWeaponReader();
            case SocketMessage.PLAYER_ACTIVE:
                return new PlayerActiveReader();
            case SocketMessage.PLAYER_CONVERT_MARKS:
                return new PlayerConvertMarksReader();
            case SocketMessage.PLAYER_DAMAGE:
                return new PlayerDamageReader();
            case SocketMessage.PLAYER_NAME:
                return new PlayerNameReader();
            case SocketMessage.PLAYER_SCORE:
                return new PlayerScoreReader();
            case SocketMessage.SPAWN_DROP:
                return new SpawnDropReader();
            case SocketMessage.SPAWN_PLAYER:
                return new SpawnPlayerReader();
            case SocketMessage.SPAWN_WEAPON:
                return new SpawnPlayerReader();
            case SocketMessage.UPDATE_USER_NAME:
                return new UpdateUsernameMessageReader();
            case SocketMessage.MAP_SPAWNPOINT_DATA:
                return new MapSpawnPointDataReader();
            default:
                throw new InvalidMessageException("type is not a valid message type.");
        }
    }
}
