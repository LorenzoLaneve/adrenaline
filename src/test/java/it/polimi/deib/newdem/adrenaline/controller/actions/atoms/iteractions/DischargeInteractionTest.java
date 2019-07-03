package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.MockWeapon;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.turn.ScriptedDataSource;
import org.junit.Before;
import org.junit.Test;


public class DischargeInteractionTest {

    InteractionStackImpl interactionStack;
    ScriptedAtomEffectContext atomEffectContext;

    @Before
    public void setUp() throws Exception {
        TestingUtils.loadSingleton();
        // interactionStack = new MockInteractionContext();
        interactionStack = new InteractionStackImpl(null);
        atomEffectContext = new ScriptedAtomEffectContext();
    }

    @Test
    public void testExecute() throws Exception {
        /*
        interactionStack.pushInteraction(new DischargeInteraction(interactionStack, new NullWeapon()));
        interactionStack.resolve();*/
    }
}