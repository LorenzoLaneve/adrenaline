package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.Action;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
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
        turnDataSource.pushActor(getActivePlayer());
        performInitialActions();
        performCoreActions();
        turnDataSource.popActor(getActivePlayer());
        // performClosingActions(); outsourced to game.concludeTurn()
    }

    protected abstract void performInitialActions();

    protected void performCoreActions() {
        int executedActions = 0;

        while (executedActions < activePlayer.getMovesAmount()) {
            ActionType aType = null;
            do {
                try{
                    aType = turnDataSource.requestAction(
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
                boolean isPup = aType.covers(new ActionType(AtomicActionType.USE_POWERUP));
                if(!isPup) {
                    // something's wrong here
                    executedActions++;
                }
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

    @Override
    public TurnDataSource getDataSource(){
        return turnDataSource;
    }
}
