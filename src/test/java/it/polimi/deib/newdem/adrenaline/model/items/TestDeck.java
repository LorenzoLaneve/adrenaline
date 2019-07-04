package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.model.game.ColorUserPair;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.game.GameParameters;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.server.NullVirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualKillTrackView;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestDeck {

    Map map;
    Game game;
    Deck deck;

    @Before
    public void setUp() throws Exception {
        TestingUtils.loadSingleton();
        map =  Map.createMap("TestMap.json");

        GameParameters gp = GameParameters.fromConfig(Config.getDefaultConfig());

        ColorUserPair colorUserPair1 = new ColorUserPair(PlayerColor.YELLOW, new User());
        ColorUserPair colorUserPair2 = new ColorUserPair(PlayerColor.GREEN, new User());
        ColorUserPair colorUserPair3 = new ColorUserPair(PlayerColor.GRAY, new User());
        List<ColorUserPair> listPairs = new ArrayList<>();
        listPairs.add(colorUserPair1);
        listPairs.add(colorUserPair2);
        listPairs.add(colorUserPair3);

        gp.setColorUserOrder(listPairs);

        gp.setGameMap(map);

        game = new GameImpl(gp);

        VirtualGameView vgv = new NullVirtualGameView();
        game.setGameListener(vgv);
        game.setKillTrackListener(new VirtualKillTrackView(vgv)); //???

        game.init();

        deck = game.getPowerUpDeck();

    }

    @Test
    public void draw() {
        try {
            deck.draw();
        }catch (NoDrawableCardException e){
            fail();
        }
    }

    @Test
    public void discard() {
        try {
            Card card = deck.draw();

            deck.discard(card);
        }catch (NoDrawableCardException e){
            fail();
        }
    }

    @Test
    public void isEmpty() {
        assertFalse(deck.isEmpty());
    }

    @Test
    public void reshuffle() {
        deck.reshuffle();
    }

    @Test
    public void getDrawableCardsAmount() {
        assertEquals(24, deck.getDrawableCardsAmount());
    }

    @Test
    public void getDiscardPile() {
        try {
            Card card = deck.draw();

            deck.discard(card);
        }catch (NoDrawableCardException e){
            fail();
        }

        assertEquals(1, deck.getDiscardPile().size());
    }

    @Test
    public void generateDeckData() {
        try {
            Card card = deck.draw();

            deck.discard(card);
        }catch (NoDrawableCardException e){
            fail();
        }

        DeckData deckData = deck.generateDeckData();

        assertEquals(1, deckData.getDiscardedCards().size());
    }

    @Test
    public void getCardFromId() {

        Card card = deck.getCardFromId(0);

        assertEquals(0, card.getCardID());

        try {
            card = deck.draw();

            deck.discard(card);
        }catch (NoDrawableCardException e){
            fail();
        }

        assertEquals(card.getCardID(), deck.getCardFromId(card.getCardID()).getCardID());

        try {
            card = deck.draw();

        }catch (NoDrawableCardException e){
            fail();
        }

        assertEquals(card.getCardID(), deck.getCardFromId(card.getCardID()).getCardID());
    }
}