package it.polimi.deib.newdem.adrenaline.view.inet.socket;

public final class SocketMessage {

    private SocketMessage() {}


    public static final int UPDATE_USER_NAME = 0x0001;

    public static final int ENTER_LOBBY = 0x0002;

    public static final int DEATH_PLAYER = 0x0003;

    public static final int EXIT_LOBBY = 0x0004;

    public static final int LEAVE_MAP_PLAYER = 0x0005;

    public static final int LOBBY_TIMER_UPDATE = 0x0006;

    public static final int MOVE_PLAYER = 0x0007;

    public static final int MAP_SPAWNPOINT_DATA = 0x0008;

    public static final int MAP_TILE_DATA = 0x0009;

    public static final int SPAWN_DROP = 0x000A;

    public static final int SPAWN_PLAYER = 0x000B;

    public static final int GAME_START = 0x000C;

    public static final int GAME_END = 0x000D;

    public static final int SPAWN_WEAPON = 0x000E;

    public static final int DAMAGE_BOARD_FLIP = 0X000F;

    public static final int PLAYER_ACQUIRE_POWERUP = 0X0010;

    public static final int PLAYER_ACQUIRE_WEAPON = 0x0011;

    public static final int PLAYER_ACTIVE = 0x0012;

    public static final int PLAYER_CONVERT_MARKS = 0x0013;

    public static final int PLAYER_DAMAGE = 0x0014;

    public static final int PLAYER_NAME = 0x0015;

    public static final int PLAYER_SCORE = 0x0016;
}
