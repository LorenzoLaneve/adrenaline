package it.polimi.deib.newdem.adrenaline.controller.effects;

public interface Effect {

    void apply(EffectVisitor visitor) throws UndoException;

}
