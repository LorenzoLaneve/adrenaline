package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.action_board.ActionBoardListener;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.view.ActionBoardView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.ActionBoardFlipEvent;

/**
 * A virtual view is a view object that acts as an adapter between model/controller and views,
 * translating the model objects into plain data objects usable by views.
 * This way the view is completely separated from the model and we do not need to clone/reflect
 * model objects into the client.
 *
 * Note: the VirtualGameView is also used to give information about the in-game users to the other
 * virtual views.
 */
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
        gameView.sendEvent(new ActionBoardFlipEvent(owner.getColor()));
    }
}
