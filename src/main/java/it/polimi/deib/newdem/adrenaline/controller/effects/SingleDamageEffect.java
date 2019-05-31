package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelectorFactory;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class SingleDamageEffect extends ConcreteEffect {

    private MetaPlayer metaPlayer;
    private PlayerSelectorFactory selectorMaker;

    private int damageAmount;
    private int markAmount;

    private boolean mandatory;


    public SingleDamageEffect(int id, int dmgAmt, int mrkAmt, MetaPlayer metaPlayer, PlayerSelectorFactory selectorMaker, boolean mandatory) {
        super(id);
        this.damageAmount = dmgAmt;
        this.markAmount = mrkAmt;

        this.metaPlayer = metaPlayer;
        this.selectorMaker = selectorMaker;

        this.mandatory = mandatory;
    }

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        Player attacked = visitor.getBoundPlayer(metaPlayer, selectorMaker.makeSelector(visitor), mandatory);


    }
}
