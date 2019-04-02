package it.polimi.deib.newdem.adrenaline.common.controller.effects;

public class DirectionalDamageEffect extends DamageEffect {

    private boolean ignoreWalls;
    private int minDistance;
    private int maxDistance;

    public DirectionalDamageEffect(int id, int dmgAmt, int mrkAmt, boolean ignoreWalls, int minDistance, int maxDistance) {
        super(id, dmgAmt, mrkAmt);
        //TODO
    }

    @Override
    public void apply(EffectVisitor visitor) {
        //TODO
    }
}
