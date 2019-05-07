package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.model.game.Player;

public interface EffectVisitor {
    
    Player getBoundPlayer(MetaPlayer player);

}
