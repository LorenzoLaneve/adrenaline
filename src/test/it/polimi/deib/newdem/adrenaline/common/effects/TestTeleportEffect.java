package it.polimi.deib.newdem.adrenaline.common.effects;


import it.polimi.deib.newdem.adrenaline.common.controller.effects.TeleportEffect;
import it.polimi.deib.newdem.adrenaline.common.model.game.changes.MovementGameChange;
import org.junit.Before;
import org.junit.Test;

public class TestTeleportEffect {

    private TestVisitor visitor;

    @Test
    @Before
    public void setupMyTest(){
        visitor = new TestVisitor();
    }

    /**
     * -get usert inpiut
     * -
     */


    public void testApply() {
/*
        TeleportEffect tpe = new TeleportEffect(7);

        tpe.apply(visitor);

        visitor.getLs().get(1).equals(
                new MovementGameChange(p1, t1, t2)
        );

*/
    }

}
