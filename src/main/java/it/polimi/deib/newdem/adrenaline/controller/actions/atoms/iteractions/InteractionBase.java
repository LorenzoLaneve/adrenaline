package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

public abstract class InteractionBase implements Interaction {

    protected InteractionContext context;

    public InteractionBase(InteractionContext context) {
        this.context = context;
    }
}
