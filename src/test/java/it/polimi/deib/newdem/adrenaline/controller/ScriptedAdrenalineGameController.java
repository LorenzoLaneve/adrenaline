package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSource;


public class ScriptedAdrenalineGameController extends AdrenalineGameController{

    private TurnDataSource source;

    public ScriptedAdrenalineGameController(LobbyController lobbyController) {
        super(lobbyController);
    }

    public void injectTurnScripts(TurnDataSource turnDataSource) {
        this.source = turnDataSource;
    }

}
