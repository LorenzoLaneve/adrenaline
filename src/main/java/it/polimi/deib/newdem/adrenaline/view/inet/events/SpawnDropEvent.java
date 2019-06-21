package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class SpawnDropEvent implements UserEvent {

    private GameData.DropType drop1;
    private GameData.DropType drop2;
    private GameData.DropType drop3;
    private TilePosition tilePosition;

    public SpawnDropEvent(GameData.DropType drop1, GameData.DropType drop2, GameData.DropType drop3, TilePosition tilePosition){
        this.drop1 = drop1;
        this.drop2 = drop2;
        this.drop3 = drop3;

        this.tilePosition = tilePosition;
    }

    public GameData.DropType getDrop1() {
        return drop1;
    }

    public GameData.DropType getDrop2() {
        return drop2;
    }

    public GameData.DropType getDrop3() {
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
