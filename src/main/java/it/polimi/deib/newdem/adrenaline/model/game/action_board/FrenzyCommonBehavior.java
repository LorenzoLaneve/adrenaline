package it.polimi.deib.newdem.adrenaline.model.game.action_board;

public abstract class FrenzyCommonBehavior implements ActionBoardBehavior {

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
