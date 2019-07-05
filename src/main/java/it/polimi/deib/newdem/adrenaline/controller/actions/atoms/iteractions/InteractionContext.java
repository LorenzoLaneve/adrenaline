package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_MULTIPLYPeer;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomContext;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomEffectContext;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomsContainer;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectContext;
import it.polimi.deib.newdem.adrenaline.controller.effects.PlasmaGunEffect;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;

/**
 * Everything an {@code Interaction} is allowed to ask of the context it
 * is created with. This may be passed on to other {@code Interaction}s.
 */
public interface InteractionContext extends AtomsContainer {

    /**
     * Add an {@code Interaction} to this context's stack.
     * @param interaction to add to this context's stack
     */
    void pushInteraction(Interaction interaction);

    /**
     * Retrieve the {@code EffectContext} for the effects applied by the
     * requesting interaction
     * @return context to apply effects in
     */
    AtomEffectContext getEffectContext();

    /**
     * Retrieve a list of all the {@code Player}s that have been damaged
     * as a result of the application of this context's effect.
     *
     * @return damaged players
     */
    List<Player> getDamagedPlayers();

    /**
     * Assigns this context's victim for future use by other {@code Interaction}s
     * @param victim who was dealt damage
     */
    void setVictim(Player victim);

    /**
     * Retrieves the singular victim protagonist of this effect, if present.
     *
     * @return the victim
     */
    Player getVictim();
}
