package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.Action;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A base for all other turns.
 *
 * Defines a turn's structure and implements most common functionality.
 *
 * Extenders of this class will likely override the methods {@code perform*Actions()}
 */
public abstract class TurnBaseImpl implements Turn {

    private Player activePlayer;
    private TurnDataSource turnDataSource;
    private boolean runClosingAction;
    private boolean allowClosingPowerup;

    /**
     * Creates a new turn bound to the given {@code Player}
     * @param activePlayer
     */
    public TurnBaseImpl(Player activePlayer) {
        this.activePlayer = activePlayer;
        runClosingAction = true;
        allowClosingPowerup = true;
    }

    @Override
    public void bindDataSource(TurnDataSource turnDataSource){
        this.turnDataSource = turnDataSource;
    }

    @Override
    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * This method defines a rigid seuqence in which some blocks of actions must be executed.
     *
     * Extenders may redefine what to do in each block, but they may not change this
     * fundamental structure.
     */
    @Override
    public final void execute() {
        turnDataSource.pushActor(getActivePlayer());
        try {
            try {
                performInitialActions();
                performCoreActions();
                if(runClosingAction) {
                    performClosingActions();
                }
            } catch (TurnInterruptedException e) {
                // do nothing, terminate gracefully.
            }
        } finally {
            turnDataSource.popActor(getActivePlayer());
        }
    }

    /**
     * Performs the initial actions for this turn.
     *
     * This method must be defined by extenders.
     *
     * @throws TurnInterruptedException if this turn is terminated abruptly for any reason
     */
    protected abstract void performInitialActions() throws TurnInterruptedException;


    /**
     * Performs the core actions for this turn.
     *
     * This method may be redefined by extenders.
     *
     * @throws TurnInterruptedException if this turn is terminated abruptly for any reason
     */
    protected void performCoreActions() throws TurnInterruptedException {
        int executedActions = 0;

        while (executedActions < activePlayer.getMovesAmount()) {

            ActionType aType = askActionToHuman();

            Action action = (new ConcreteActionFactory(aType)).makeAction(activePlayer, turnDataSource);
            try {
                action.start();
                boolean isPup = aType.covers(new ActionType(AtomicActionType.USE_POWERUP));
                if(!isPup) {
                    executedActions++;
                }
            }
            catch (UndoException e) {
                // do not increment executedActions
            }
        }
    }


    /**
     * Performs the initial actions for this turn.
     *
     * This method may be redefined by extenders.
     *
     * @throws TurnInterruptedException if this turn is terminated abruptly for any reason
     */
    protected void performClosingActions() throws TurnInterruptedException {

        // use powerup
        if(!activePlayer.getInventory().getPowerUpByTrigger(PowerUpTrigger.CALL).isEmpty() && allowClosingPowerup) {
            try{
                // here I can use one or more pups
                ActionFactory powerUpActionFactory = new ConcreteActionFactory(AtomicActionType.USE_POWERUP);
                Action powerUpAction = powerUpActionFactory.makeAction(activePlayer, turnDataSource);
                powerUpAction.start();
            }
            catch (UndoException e) {
                // terminate gracefully
            }
        }


        // reload
        if(activePlayer.canReload() && !activePlayer.getActionBoard().isFrenzy()) {
            try {
                ActionType aType = turnDataSource.requestAction(Arrays.asList(new ActionType(AtomicActionType.RELOAD)));

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

    /**
     * Helper that asks for a powerup to a human
     *
     * @param availableChoiches powerups to choose from
     * @return chosen powerup
     */
    protected PowerUpCard askPowerUpToHuman(List<PowerUpCard> availableChoiches)  {
        PowerUpCard chosenCard = null;
        do {
            try{
                chosenCard = getDataSource().choosePowerUpCard(availableChoiches);
            }
            catch (UndoException e) {
                // undo not allowed here. Repeat.
            }
        }
        while (null == chosenCard);
        return chosenCard;
    }

    @Override
    public TurnDataSource getDataSource(){
        return turnDataSource;
    }

    @Override
    public void setRunClosingActions(boolean flag) {
        runClosingAction = flag;
    }

    @Override
    public void setAllowClosingPowerUps(boolean flag) {
        allowClosingPowerup = flag;
    }

    private ActionType askActionToHuman() throws TurnInterruptedException {
        ActionType aType = null;
        try{
            aType = turnDataSource.requestAction(
                    activePlayer.getMoves()
                            .stream()
                            .map(ActionFactory::getType)
                            .collect(Collectors.toList())
            );

            if (null == aType) {
                // user wishes to terminate turn
                throw new TurnTerminatedByUserException();
            }
        }
        catch (UndoException e) {
            // this should never happen
            throw new IllegalStateException();
        }

        return aType;
    }

}
