package it.polimi.deib.newdem.adrenaline.controller.effects;

public class DirectionalTileDamageEffect extends DamageEffect {

    private boolean ignoreWalls;
    private int minDistance;
    private int maxDistance;

    public DirectionalTileDamageEffect(int id, int dmgAmt, int mrkAmt, boolean ignoreWalls, int minDistance, int maxDistance) {
        super(id, dmgAmt, mrkAmt);
        //TODO
    }

    @Override
    public void apply(EffectVisitor visitor) {
        //TODO
    }
}