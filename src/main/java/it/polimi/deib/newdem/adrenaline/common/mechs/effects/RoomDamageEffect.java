package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.Action;

public class RoomDamageEffect extends DamageEffect {

    private TileSelectorFactory selectorMaker;

    public RoomDamageEffect(int id, int dmgAmt, int mrkAmt, TileSelectorFactory selectorMaker) {
        super(id, dmgAmt, mrkAmt);
        this.selectorMaker = selectorMaker;
        //TODO
    }
    @Override
    public void apply(Action action) {
        //TODO
    }

}
