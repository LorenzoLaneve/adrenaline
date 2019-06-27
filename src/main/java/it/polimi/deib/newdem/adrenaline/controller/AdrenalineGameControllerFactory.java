package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.items.DeckAlreadyLoadedException;
import it.polimi.deib.newdem.adrenaline.model.items.InvalidJSONException;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponDeck;

public class AdrenalineGameControllerFactory implements GameControllerFactory {

    @Override
    public GameController makeGameController(LobbyController lobbyController) {
        return new AdrenalineGameController(lobbyController);
    }

}
