package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_MULTIPLYPeer;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomContext;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomEffectContext;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomsContainer;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectContext;
import it.polimi.deib.newdem.adrenaline.controller.effects.PlasmaGunEffect;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;

public interface InteractionContext extends AtomsContainer {

    void pushInteraction(Interaction interaction);

    AtomEffectContext getEffectContext();

    List<Player> getDamagedPlayers();

    void setVictim(Player victim);

    Player getVictim();
}
