package it.polimi.deib.newdem.adrenaline;

import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.model.game.ColorUserPair;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.game.GameParameters;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.DeckAlreadyLoadedException;
import it.polimi.deib.newdem.adrenaline.model.items.InvalidJSONException;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponDeck;
import it.polimi.deib.newdem.adrenaline.model.map.TestingMapBuilder;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TestingUtils {

    public static void loadSingleton() {
        try {
            WeaponDeck.loadCardsFromJson(GameImpl.WEAPON_DECK_PATH);
        }
        catch (DeckAlreadyLoadedException e) { /* ok */ }
        catch (InvalidJSONException e) { throw new IllegalStateException(); }
    }

    public static Game makeTestGame(PlayerColor ... color) {
        loadSingleton();
        GameParameters gp = GameParameters.fromConfig(Config.getDefaultConfig());
        gp.setGameMap(TestingMapBuilder.getNewMap(TestingUtils.class));
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
}
