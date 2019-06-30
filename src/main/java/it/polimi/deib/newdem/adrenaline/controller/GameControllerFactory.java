package it.polimi.deib.newdem.adrenaline.controller;


/**
 * Object that create a new game controller. Please note that this interface is agnostic to the specific game controller
 */
public interface GameControllerFactory {

    /**
     * Creates a new game controller that will be hosted by the given lobby controller.
     */
    GameController makeGameController(LobbyController lobbyController);

}
