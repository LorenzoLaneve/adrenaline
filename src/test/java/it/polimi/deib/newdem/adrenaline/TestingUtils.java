package it.polimi.deib.newdem.adrenaline;

import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.model.game.ColorUserPair;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.game.GameParameters;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.rigged.GameRigged;
import it.polimi.deib.newdem.adrenaline.model.items.DeckAlreadyLoadedException;
import it.polimi.deib.newdem.adrenaline.model.items.InvalidJSONException;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponDeck;
import it.polimi.deib.newdem.adrenaline.model.map.TestingMapBuilder;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Static utilities for testing
 */
public class TestingUtils {

    /**
     * Loads all the singletons for this project
     */
    public static void loadSingleton() {
        try {
            WeaponDeck.loadCardsFromJson(GameImpl.WEAPON_DECK_PATH);
        }
        catch (DeckAlreadyLoadedException e) { /* ok */ }
        catch (InvalidJSONException e) { throw new IllegalStateException(); }
    }

    /**
     * Builds a new {@code Game} with minimal but payable features
     * @param color of the playing players. The amount of values corresponds to the
     *              amount of playing players.
     * @return a new game
     */
    public static Game makeTestGame(PlayerColor ... color) {
        loadSingleton();
        GameParameters gp = GameParameters.fromConfig(Config.getDefaultConfig());
        gp.setGameMap(TestingMapBuilder.getNewMap());
        gp.setColorUserOrder(
                Arrays.stream(color)
                        .map(c -> new ColorUserPair(c, new User()))
                .collect(Collectors.toList())
        );
        gp.setMinPlayers(color.length);
        Game g = new GameImpl(gp);
        g.init();
        return g;
    }

    /**
     * Builds a new {@code GameRigged} with minimal but payable features
     * @param color of the playing players. The amount of values corresponds to the
     *              amount of playing players.
     * @return a new rigged game
     */
    public static GameRigged makeRiggedGame(PlayerColor ... color) {
        loadSingleton();
        GameParameters gp = GameParameters.fromConfig(Config.getDefaultConfig());
        gp.setGameMap(TestingMapBuilder.getNewMap());
        gp.setColorUserOrder(
                Arrays.stream(color)
                        .map(c -> new ColorUserPair(c, new User()))
                        .collect(Collectors.toList())
        );
        gp.setMinPlayers(color.length);
        gp.setTurnTime(1);
        GameRigged g = new GameRigged(gp);
        g.init();
        return g;
    }
}
