package it.polimi.deib.newdem.adrenaline.controller;

public interface GameControllerFactory {

    GameController makeGameController(LobbyController lobbyController);

    /**
     * Returns a game manager factory of the appropriate class according to the given configuration.
     */
    static GameControllerFactory create() {
        return new AdrenalineGameControllerFactory();
    }

}
