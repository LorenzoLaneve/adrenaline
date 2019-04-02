package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;

public interface EffectVisitor {
    
    Player getBoundPlayer(MetaPlayer player);

}
