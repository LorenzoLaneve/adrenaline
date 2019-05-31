package it.polimi.deib.newdem.adrenaline.controller.effects;

public class DirectionalDamageEffect extends ConcreteEffect {

    private boolean ignoreWalls;

    private int damageAmount;
    private int markAmount;

    private int minDistance;
    private int maxDistance;

    public DirectionalDamageEffect(int id, int dmgAmt, int mrkAmt, boolean ignoreWalls, int minDistance, int maxDistance) {
        super(id);

        this.damageAmount = dmgAmt;
        this.markAmount = mrkAmt;
        this.ignoreWalls = ignoreWalls;

        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
    }

    @Override
    public void apply(EffectVisitor visitor) {
        //TODO
    }
}
