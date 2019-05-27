package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.action_board.ActionBoardListener;
import it.polimi.deib.newdem.adrenaline.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.ActionBoardView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.DamageBoardFlipEvent;

public class VirtualActionBoardView implements ActionBoardView, ActionBoardListener {

    // does this need to know who's damageboard its flipping?


    public VirtualActionBoardView(VirtualGameView gameView, PlayerColor owner) {
        this.gameView = gameView;
        this.owner = owner;
    }

    private VirtualGameView gameView;
    private PlayerColor owner;

    @Override
    public void boardDidFlip() {
        flipActionBoard();
    }

    @Override
    public void flipActionBoard() {
        gameView.sendEvent(new DamageBoardFlipEvent());
    }
}
