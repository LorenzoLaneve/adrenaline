package it.polimi.deib.newdem.adrenaline.model.game.action_board;

/**
 * A null object to allow testing and default values.
 *
 * All of this methods do not report to anything.
 */
public class NullActionBoardListener implements ActionBoardListener {

    /**
     * This method does not report to anything
     */
    @Override
    public void boardDidFlip() {
        // nothing to do here.
    }

}
