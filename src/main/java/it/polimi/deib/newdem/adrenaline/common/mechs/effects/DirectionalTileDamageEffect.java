package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.Action;

public class DirectionalTileDamageEffect extends DamageEffect {

    private boolean ignoreWalls;
    private int minDistance;
    private int maxDistance;

    public DirectionalTileDamageEffect(int id, int dmgAmt, int mrkAmt, boolean ignoreWalls, int minDistance, int maxDistance) {
        super(id, dmgAmt, mrkAmt);
        //TODO
    }

    @Override
    public void apply(Action action) {
        //TODO
    }
}
