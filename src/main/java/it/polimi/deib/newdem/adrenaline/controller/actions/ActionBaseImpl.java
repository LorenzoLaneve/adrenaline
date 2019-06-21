package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public class ActionBaseImpl implements Action {

    private ActionDataSource listener;

    private Player actor;


    public ActionBaseImpl(Player actor) {
        this.actor = actor;
    }

    @Override
    public void start() throws UndoException {
        //visit(getEffect());
    }

    @Override
    public Player getActor() {
        return actor;
    }

    @Override
    public Effect getEffect() {
        return null;
    }

}
