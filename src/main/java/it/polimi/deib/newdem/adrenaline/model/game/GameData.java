package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameData implements Serializable {
    /**
     * Data representation of the current game state. Intended for communication with the views,
     * this does not include non-visible information (e.g. who is the active player)
     */

    public enum DropType {
        RED_AMMO,
        BLUE_AMMO,
        YELLOW_AMMO,
        POWER_UP
    }

    public class UserColorPair implements Serializable {

        private String username;
        private PlayerColor color;
        private boolean isOnline;

        UserColorPair(String username, PlayerColor color, boolean isOnline) {
            this.username = username;
            this.color = color;
            this.isOnline = isOnline;
        }

        public String getUsername() {
            return username;
        }

        public PlayerColor getColor() {
            return color;
        }

        public boolean isOnline() { return isOnline; }

    }

    private static final String EMPTY_USERS_MSG = "No users in this GameData";

    private ArrayList<UserColorPair> users;
    private boolean finalized;

    GameData() {
        this.users = new ArrayList<>();
    }

    public void setFinalized() {
        if(finalized) throw new IllegalStateException();
        finalized = true;
    }

    void addUser(String name, PlayerColor color, boolean isOnline) {
        this.users.add(new UserColorPair(name, color, isOnline));
    }

    /**
     * Returns a list of players, in the order they should play and starting with the first player.
     */
    public List<UserColorPair> getPlayers() {
        if(null == users) throw new IllegalStateException(EMPTY_USERS_MSG);
        return new ArrayList<>(users);
    }

    public boolean isFinalized() {
        return finalized;
    }

}
