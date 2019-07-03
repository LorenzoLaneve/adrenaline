package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.InterruptExecutionException;
import it.polimi.deib.newdem.adrenaline.controller.actions.Action;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponSet;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TurnBaseImpl implements Turn {

    private Player activePlayer;
    private TurnDataSource turnDataSource;
    private boolean runClosingAction;
    private boolean allowClosingPowerup;

    public TurnBaseImpl(Player activePlayer) {
        this.activePlayer = activePlayer;
        runClosingAction = true;
        allowClosingPowerup = true;
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
        // refilling tiles and assigning scores are outsourced to Game::concludeTurn
    }

    protected abstract void performInitialActions() throws TurnInterruptedException;

    protected void performClosingActions() throws TurnInterruptedException {

        // use powerup
        if(!activePlayer.getInventory().getCallablePowerUps().isEmpty() && allowClosingPowerup) {
            try{
                // here I can use one or more pups
                // start a pupAction
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

    protected void performCoreActions() throws TurnInterruptedException {
        int executedActions = 0;

        while (executedActions < activePlayer.getMovesAmount()) {
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
        }
    }

    protected PowerUpCard askPowerUpToHuman(List<PowerUpCard> availableChoiches) throws InterruptExecutionException {
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
}
