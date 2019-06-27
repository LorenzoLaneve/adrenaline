package it.polimi.deib.newdem.adrenaline;

import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.items.DeckAlreadyLoadedException;
import it.polimi.deib.newdem.adrenaline.model.items.InvalidJSONException;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponDeck;

public class TestingUtils {

    public static void loadSingleton() {
        try {
            WeaponDeck.loadCardsFromJson(GameImpl.WEAPON_DECK_PATH);
        }
        catch (DeckAlreadyLoadedException e) { /* ok */ }
        catch (InvalidJSONException e) { throw new IllegalStateException(); }

    }
}
