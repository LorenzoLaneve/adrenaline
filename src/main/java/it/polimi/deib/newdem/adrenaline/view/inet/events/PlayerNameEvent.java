package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The given player changed their name to the given name.
 * @see UserEvent to see what this class is used for.
 */
public class PlayerNameEvent implements UserEvent {

    private PlayerColor color;

    private String name;

    /**
     * An event indicating that the player with the given color has the given name (server bound event).
     */
    public PlayerNameEvent(PlayerColor color, String name) {
        this.color = color;
        this.name = name;
    }


    public PlayerColor getPlayer() {
        return color;
    }

    public String getName() {
        return name;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
