package it.polimi.deib.newdem.adrenaline.common.controller.effects;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

public interface EffectVisitor {
    
    Player getBoundPlayer(MetaPlayer player);

}
