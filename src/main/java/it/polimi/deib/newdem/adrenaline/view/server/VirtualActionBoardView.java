package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.action_board.ActionBoardListener;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.view.ActionBoardView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.DamageBoardFlipEvent;

public class VirtualActionBoardView implements ActionBoardView, ActionBoardListener {

    private Player owner;

    private VirtualGameView gameView;


    public VirtualActionBoardView(Player owner, VirtualGameView gameView) {
        this.owner = owner;
        this.gameView = gameView;
    }


    @Override
    public void boardDidFlip() {
        flipActionBoard();
    }

    @Override
    public void flipActionBoard() {
        gameView.sendEvent(new DamageBoardFlipEvent(owner.getColor()));
    }
}
