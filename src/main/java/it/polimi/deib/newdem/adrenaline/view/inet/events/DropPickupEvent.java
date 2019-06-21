package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class DropPickupEvent implements UserEvent {

    private GameData.DropType drop1;
    private GameData.DropType drop2;
    private GameData.DropType drop3;

    private TilePosition tilePosition;

    private PlayerColor player;

    public DropPickupEvent(GameData.DropType drop1, GameData.DropType drop2, GameData.DropType drop3, TilePosition tile, PlayerColor player) {
        this.drop1 = drop1;
        this.drop2 = drop2;
        this.drop3 = drop3;
        this.player = player;
        this.tilePosition = tile;
    }

    public GameData.DropType getDrop3() {
        return drop3;
    }

    public PlayerColor getPlayer() {
        return player;
    }

    public GameData.DropType getDrop2() {
        return drop2;
    }

    public TilePosition getTilePosition() {
        return tilePosition;
    }

    public GameData.DropType getDrop1() {
        return drop1;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
