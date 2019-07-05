package it.polimi.deib.newdem.adrenaline.model.game.action_board;

/**
 * Abstract class encapsulating common behavior for all the frenzy {@code ActionBoardBehavior}s
 */
public abstract class FrenzyCommonBehavior implements ActionBoardBehavior {

    /**
     * Notifies the listener that this board has flipped
     * @param actionBoard the board that flipped
     */
    @Override
    public void onEnter(ActionBoardImpl actionBoard) {
        actionBoard.notifyFlip();
    }

    @Override
    public void onLeave(ActionBoardImpl actionBoard) {
        // do nothing
    }

    @Override
    public boolean isFrenzy() {
        return true;
    }
}
