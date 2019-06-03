package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

public class SameTilePlayerSelector implements PlayerSelector {

    private Player sourcePlayer;

    public SameTilePlayerSelector(Player sourcePlayer) {
        this.sourcePlayer = sourcePlayer;
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        return player.getTile() == sourcePlayer.getTile();
    }

}
