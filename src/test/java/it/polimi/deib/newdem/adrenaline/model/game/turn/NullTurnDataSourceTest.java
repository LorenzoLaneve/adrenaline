package it.polimi.deib.newdem.adrenaline.model.game.turn;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NullTurnDataSourceTest {

    NullTurnDataSource tds;

    @Before
    public void setUp() throws Exception {
        tds = new NullTurnDataSource();
    }

    @Test
    public void testChooseCard() throws Exception {
        tds.choosePowerUpCard(new ArrayList<>());
    }

    @Test
    public void testChooseAction() throws Exception {
        tds.requestAction(new ArrayList<>());
    }
}