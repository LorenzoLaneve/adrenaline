package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;

public class NegatedPlayerSelectorFactory implements PlayerSelectorFactory {

    private PlayerSelectorFactory innerSelectorFactory;

    public NegatedPlayerSelectorFactory(PlayerSelectorFactory innerSelectorFactory) {
        this.innerSelectorFactory = innerSelectorFactory;
    }


    @Override
    public PlayerSelector makeSelector(EffectVisitor visitor) {
        return null;
    }
}
