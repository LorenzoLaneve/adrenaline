package it.polimi.deib.newdem.adrenaline.common.model.items;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestDeck {

    private List<MockCard> arg;

    public class MockCard {
        private String name;

        MockCard(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @Before
    public void initTest(){
        MockCard m1, m2;

        m1 = new MockCard("Alex");
        m2 = new MockCard("Betty");

        this.arg = Arrays.asList(m1,m2);
    }

    @Test
    public void testConstructorPositive() {
        new Deck<>(this.arg);
    }

    @Test
    public void testDrawPositive() throws NoDrawableCardException {
        Deck<MockCard> d = new Deck<>(arg);

        MockCard c1 = d.draw();
        if (!arg.contains(c1)) {
            fail();
        }

        MockCard c2 = d.draw();
        // if(arg.contains(c2) && !c2.equals(c1)) {success();}
        if (!arg.contains(c2) || c2.equals(c1)) {
            fail();
        }
    }

    @Test
    public void testDiscardPositive() throws NoDrawableCardException {
        Deck<MockCard> d = new Deck<>(arg);

        d.discard(d.draw());
        d.discard(d.draw());
        d.discard(d.draw());
    }

    @Test
    public void testIsEmptyPositive() throws NoDrawableCardException {
        Deck<MockCard> d = new Deck<>(arg);

        assertFalse(d.isEmpty());

        d.draw();
        d.draw();

        assertTrue(d.isEmpty());
    }

    @Test
    public void testReshufflePositive() throws NoDrawableCardException {
        Deck<MockCard> d = new Deck<>(arg);
        MockCard m1, m2;

        d.discard(d.draw());
        d.discard(d.draw());
        m1 = d.draw();
        m2 = d.draw();

        if(m1.equals(m2) ||
            !arg.contains(m1) ||
            !arg.contains(m2)
        ) { fail(); }

        MockCard m11, m12, m13, m1a, m1b, m1c;
        m11 = new MockCard("Alpha");
        m12 = new MockCard("Bravo");
        m13 = new MockCard("Charlie");
        List<MockCard> arg1 = Arrays.asList(m11,m12,m13);

        Deck<MockCard> d1 = new Deck<>(arg1);

        d1.discard(d1.draw());
        m1a = d1.draw();

        // 1 active card (m1a); 1 discarded card, 1 drawable card
        d1.reshuffle();
        m1b = d1.draw();
        m1c = d1.draw();

        if(!(
                arg1.contains(m1a) &&
                arg1.contains(m1b) &&
                arg1.contains(m1c) &&
                        !m1a.equals(m1b) &&
                        !m1a.equals(m1c) &&
                        !m1b.equals(m1c) &&
                        d1.isEmpty()
        )) { fail(); }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeNull() {
        new Deck<MockCard>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeEmpty() {
        new Deck<>(new ArrayList<MockCard>());
    }

    @Test(expected = NoDrawableCardException.class)
    public void testDrawNegative() throws NoDrawableCardException {
        Deck<MockCard> d = new Deck<>(arg);
            d.draw();
            d.draw();
            d.draw();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDiscardNegativeForeign() {
        Deck<MockCard> d = new Deck<>(arg);

        d.discard(new MockCard("Delta"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDiscardNegativeNull() {
        Deck<MockCard> d = new Deck<>(arg);

        d.discard(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDiscardNegativeDouble() throws NoDrawableCardException {
        Deck<MockCard> d = new Deck<>(arg);

        MockCard m1 = d.draw();

        d.discard(m1);
        d.discard(m1);
    }

    @Test
    public void testIsEmptyNegative() {
        // this method can't fail
    }

    @Test
    public void testReshuffleNegative() {
        // this method can't fail
    }

    @After
    public void clean() {
        // do nothing
    }
}