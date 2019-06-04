package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

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

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidUpdateName(connection, this);
    }

}
