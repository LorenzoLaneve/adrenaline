package it.polimi.deib.newdem.adrenaline.common.effects;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.common.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

import java.util.List;

public class TestVisitor implements EffectVisitor {

    List<GameChange> ls;

    public List<GameChange> getLs() {
        return ls;
    }

    @Override
    public Player getBoundPlayer(MetaPlayer player) {
        return null;
    }
}
