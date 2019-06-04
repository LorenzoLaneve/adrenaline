package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.MapView.DropType;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

public class AcquireDropEvent implements UserEvent {

    private DropType drop1;
    private DropType drop2;
    private DropType drop3;

    private TilePosition tilePosition;

    private PlayerColor player;

    public AcquireDropEvent(DropType drop1, DropType drop2, DropType drop3, TilePosition tile, PlayerColor player) {
        this.drop1 = drop1;
        this.drop2 = drop2;
        this.drop3 = drop3;
        this.player = player;
        this.tilePosition = tile;
    }

    public DropType getDrop3() {
        return drop3;
    }

    public PlayerColor getPlayer() {
        return player;
    }

    public DropType getDrop2() {
        return drop2;
    }

    public TilePosition getTilePosition() {
        return tilePosition;
    }

    public DropType getDrop1() {
        return drop1;
    }


    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.dropDidGetAcquired(connection, this);
    }

}
