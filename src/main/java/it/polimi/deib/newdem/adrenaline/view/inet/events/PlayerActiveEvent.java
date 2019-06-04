package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

public class PlayerActiveEvent implements UserEvent {

    private PlayerColor playerColor;

    public PlayerActiveEvent(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }


    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidBecomeActive(connection, this);
    }

}
