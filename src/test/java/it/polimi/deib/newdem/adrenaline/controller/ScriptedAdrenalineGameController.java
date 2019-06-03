package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.turn.ScriptedDataSource;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSource;

import java.util.HashMap;

public class ScriptedAdrenalineGameController extends AdrenalineGameController{

    private TurnDataSource source;

    public ScriptedAdrenalineGameController(LobbyController lobbyController) {
        super(lobbyController);
    }

    public void injectTurnScripts(TurnDataSource turnDataSource) {
        this.source = turnDataSource;
    }

    @Override
    protected void buildTurnDataSources(Game game) {
        playerDataSourceMap = new HashMap<>();
        for(Player p : game.getPlayers()){
            playerDataSourceMap.put(p, source);
        }
    }
}
