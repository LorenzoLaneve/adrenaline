package it.polimi.deib.newdem.adrenaline.model.game.rigged;

import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.game.GameParameters;
import it.polimi.deib.newdem.adrenaline.model.items.Deck;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

public class GameRigged extends GameImpl {

    public GameRigged(GameParameters parameters) {
        super(parameters);
    }

    public void setWeaponDeck(Deck<WeaponCard> deck) {
        this.weaponDeck = deck;
    }

    public void setPowerupDeck(Deck<PowerUpCard> deck) {
        this.powerUpDeck = deck;
    }

    public void setDropDeck(Deck<DropInstance> deck) {
        this.dropDeck = deck;
    }
}
