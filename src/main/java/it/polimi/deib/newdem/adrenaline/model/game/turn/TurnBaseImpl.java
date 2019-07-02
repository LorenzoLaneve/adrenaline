package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.Action;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.Arrays;
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
        performClosingActions();
        turnDataSource.popActor(getActivePlayer());
        // performClosingActions(); outsourced to game.concludeTurn()
    }

    protected abstract void performInitialActions();

    protected void performClosingActions() {

        // use powerup
        ActionType aType = null;
        if(!activePlayer.getInventory().getCallablePowerUps().isEmpty()) {
            try{
                aType = turnDataSource.requestAction(Arrays.asList(new ActionType(AtomicActionType.USE_POWERUP)));

                if(null == aType) { throw new UndoException(); }

                Action action = new ConcreteActionFactory(aType).makeAction(activePlayer, turnDataSource);
                action.start();
            }
            catch (UndoException e) {
                // terminate gracefully
            }
        }

        // reload
        if(!activePlayer.getInventory().getDischargedWeapons().isEmpty()) {
            try {
                aType = turnDataSource.requestAction(Arrays.asList(new ActionType(AtomicActionType.RELOAD)));

                if (null == aType) {
                    throw new UndoException();
                }

                Action reloadAction = new ConcreteActionFactory(aType).makeAction(activePlayer, turnDataSource);
                reloadAction.start();
            } catch (UndoException e) {
                // terminate gracefully
            }
        }
    }

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
            // TODO
            // aType == null -> user wishes to terminate turn?

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
                System.out.println("Hello");
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
