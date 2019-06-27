package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

import java.io.Serializable;

public class PlayerTilePair implements Serializable {

    private TilePosition tile;
    private PlayerColor player;

    PlayerTilePair(TilePosition tile, PlayerColor player) {
        this.tile = tile;
        this.player = player;
    }

    public TilePosition getTile() {
        return tile;
    }

    public PlayerColor getPlayer() {
        return player;
    }

}
