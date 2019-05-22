package it.polimi.deib.newdem.adrenaline.controller;

public class AdrenalineGameControllerFactory implements GameControllerFactory {

    @Override
    public GameController makeGameController(LobbyController lobbyController) {
        return new AdrenalineGameController(lobbyController);
    }

}
