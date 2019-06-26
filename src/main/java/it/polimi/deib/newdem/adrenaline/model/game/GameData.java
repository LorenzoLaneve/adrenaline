package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameData implements Serializable {

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

    private ArrayList<UserColorPair> users;

    GameData() {
        this.users = new ArrayList<>();
    }

    void addPlayer(String name, PlayerColor color, boolean isOnline) {
        this.users.add(new UserColorPair(name, color, isOnline));
    }


    /**
     * Returns a list of players, in the order they should play and starting with the first player.
     */
    public List<UserColorPair> getPlayers() {
        return new ArrayList<>(users);
    }

}
