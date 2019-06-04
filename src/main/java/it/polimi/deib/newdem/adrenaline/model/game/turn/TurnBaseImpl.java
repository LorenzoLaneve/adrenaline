package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.TimeoutException;
import it.polimi.deib.newdem.adrenaline.controller.actions.Action;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;
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
            ActionType aType = turnDataSource.chooseAction(
                    activePlayer.getMoves()
                            .stream()
                            .map(ActionFactory::getType)
                            .collect(Collectors.toList()));

            Action action = (new ConcreteActionFactory(aType)).makeAction(activePlayer);
            try {
                action.start();
                executedActions++;
            }
            catch (UndoException e) {
                // do wht now?
            }
            finally {}
            // catch (UndoException | TimeoutException | InterruptedException e){
                // do stuff
                // undo stuff
                // terminate
            // }

            /*
            ListeerPool = new ListenerPool(legalMoves.foreach(getTriggerListener));
            MyEvent = ListenerPool.waitAny();
            MyEvent.process();
            ExecutedActions += MyEvent.isSuccesful() ? 1 : 0;
            */
        }
    }

    protected TurnDataSource getDataSource(){
        return turnDataSource;
    }
}
