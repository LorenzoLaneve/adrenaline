package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class MovePlayerEvent implements UserEvent {

    private PlayerColor playerColor;
    private TilePosition destination;

    public MovePlayerEvent(PlayerColor playerColor, TilePosition destination){
        this.playerColor = playerColor;
        this.destination = destination;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public TilePosition getDestination() {
        return destination;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
