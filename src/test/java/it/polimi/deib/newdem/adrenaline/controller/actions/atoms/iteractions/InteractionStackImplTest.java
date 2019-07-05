package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.model.game.MockPlayer;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertNull;
/**
 * Unit tests for {@code InteractionStackImp}
 *
 * @see it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.InteractionStackImpl
 */
public class InteractionStackImplTest {

    /**
     * Tests set and get victim fucntionality
     * @throws Exception
     */
    @Test
    public void testVictim() throws Exception {
        InteractionStackImpl isi = new InteractionStackImpl(new MockInteractionContext());
        Player victim = new MockPlayer();
        isi.setVictim(victim);
        assertNull(isi.getVictim());

        isi.registerContext(new MockAtomEffectContext());

        isi.setVictim(victim);
        assertNull(isi.getVictim());
    }
}
