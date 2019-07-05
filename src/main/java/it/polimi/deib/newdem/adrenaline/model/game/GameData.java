package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Data representation of the current game state. Intended for communication with the views,
 * this does not include non-visible information (e.g. who is the active player)
 */
public class GameData implements Serializable {

    /**
     * Data representation for a drop tile's element
     */
    public enum DropType {
        RED_AMMO,
        BLUE_AMMO,
        YELLOW_AMMO,
        POWER_UP
    }

    /**
     * Serializable implementation of an association between user and color
     *
     * @see ColorUserPair
     */
    public class UserColorPair implements Serializable {

        private String username;
        private PlayerColor color;
        private boolean isOnline;

        /**
         * Creates a new {@code UserColorPair} with the given parameters
         * @param username user
         * @param color user's color
         * @param isOnline is the user currently online
         */
        UserColorPair(String username, PlayerColor color, boolean isOnline) {
            this.username = username;
            this.color = color;
            this.isOnline = isOnline;
        }

        /**
         * Retireves this user's name
         * @return username
         */
        public String getUsername() {
            return username;
        }

        /**
         * Retrieves the player's color
         * @return color
         */
        public PlayerColor getColor() {
            return color;
        }

        /**
         * Retrieves the user's online status
         * @return online status
         */
        public boolean isOnline() { return isOnline; }

    }

    private static final String EMPTY_USERS_MSG = "No users in this GameData";

    private ArrayList<UserColorPair> users;
    private boolean finalized;

    /**
     * Creates a new empty {@code GameData} object
     */
    GameData() {
        this.users = new ArrayList<>();
    }

    /**
     * Marks this gameData object as finalized, meaning it can no longer
     * be mutated.
     */
    public void setFinalized() {
        if(finalized) throw new IllegalStateException();
        finalized = true;
    }

    /**
     * Adds a user to this {@code GameData}
     *
     * @param name username
     * @param color player color
     * @param isOnline user online status
     */
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

    /**
     * Checks if this {@code GameData} object is finalized
     * @return is this finalized
     */
    public boolean isFinalized() {
        return finalized;
    }

}
