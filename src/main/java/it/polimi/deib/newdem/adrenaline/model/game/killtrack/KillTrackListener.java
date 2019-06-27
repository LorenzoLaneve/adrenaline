package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public interface KillTrackListener {

    void playerDidKill(Player player, int amount);

    void killTrackDidUndoLastKill();

}
