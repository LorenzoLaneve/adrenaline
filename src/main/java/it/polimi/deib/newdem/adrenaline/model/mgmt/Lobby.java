package it.polimi.deib.newdem.adrenaline.model.mgmt;

import java.util.List;

public interface Lobby {

    /**
     * Adds the given user to the lobby.
     */
    void addUser(User user);

    /**
     * Removes the given user from the lobby.
     */
    void removeUser(User user);

    /**
     * Returns a list of the users that are in the lobby at the moment of the call.
     * Please note that no assumption will be made on the order.
     */
    List<User> getUsers();

    /**
     * Returns the minimum number of players required by the lobby to start the game.
     */
    int getMinPlayers();

    /**
     * Returns the number of players that will cause the lobby to immediately start the game.
     */
    int getMaxPlayers();

}
