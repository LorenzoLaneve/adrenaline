package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.MapView.DropType;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class SpawnDropEvent implements UserEvent {

    private DropType drop1;
    private DropType drop2;
    private DropType drop3;
    private TilePosition tilePosition;

    public SpawnDropEvent(DropType drop1, DropType drop2, DropType drop3, TilePosition tilePosition){
        this.drop1 = drop1;
        this.drop2 = drop2;
        this.drop3 = drop3;

        this.tilePosition = tilePosition;
    }

    public DropType getDrop1() {
        return drop1;
    }

    public DropType getDrop2() {
        return drop2;
    }

    public DropType getDrop3() {
        return drop3;
    }

    public TilePosition getTilePosition() {
        return tilePosition;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
