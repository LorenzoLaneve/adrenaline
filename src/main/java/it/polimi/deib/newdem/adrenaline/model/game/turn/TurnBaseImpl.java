package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.Action;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.stream.Collectors;

public abstract class TurnBaseImpl implements Turn {

    private Player activePlayer;
    private TurnDataSource turnDataSource;

    public TurnBaseImpl(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public void bindDataSource(TurnDataSource turnDataSource){
        this.turnDataSource = turnDataSource;
    }

    @Override
    public Player getActivePlayer() {
        return activePlayer;
    }

    @Override
    public void execute() {
        turnDataSource.turnDidStart(getActivePlayer());
        performInitialActions();
        performCoreActions();
        // performClosingActions(); outsourced to game.concludeTurn()
    }

    protected abstract void performInitialActions();

    protected void performCoreActions() {
        int executedActions = 0;
        /*

        This serves as a high-level idea of how Turn should work.
        None of it is implemented at the time of commit,
        buit it's a template worth remembering.
           */
        // TurnView new every turn
        // OR Tv forall players, passed to turn in construction
        // No turnlistener (returns data), TurnDataSource
        // TV handles dialogs

        // controller holds tds
        // @Controller
        // Turn t = game.getNetTurn();
        // t.bindDataSource(this.getTurnViewFromPlayerColor(ap.getC()));
        //
        while (executedActions < activePlayer.getMovesAmount()) {
            ActionType aType = null;
            do {
                try{
                    aType = turnDataSource.chooseAction(
                            activePlayer.getMoves()
                                    .stream()
                                    .map(ActionFactory::getType)
                                    .collect(Collectors.toList()));
                }
                catch (UndoException e) {
                    // do nothing
                }
            } while (null == aType);

            Action action = (new ConcreteActionFactory(aType)).makeAction(activePlayer, turnDataSource);
            try {
                action.start();
                executedActions++;
            }
            catch (UndoException e) {
                // do not increment executedActions
            }
            finally {}
            // catch (TimeoutException | InterruptedException e){
                // do stuff
                // undo stuff
                // terminate
            // }
        }
    }

    protected TurnDataSource getDataSource(){
        return turnDataSource;
    }
}
