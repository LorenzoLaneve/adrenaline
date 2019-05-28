package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public abstract class TurnBaseImpl implements Turn {

    private Player activePlayer;
    public TurnBaseImpl(Player activePlayer) {
        this.activePlayer = activePlayer;
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

        while (ExecutedActions < activePlayer.getMovesAmount()) {
            ActionType aType = turnListener.chooseAction(
                    activePlayer.getMoves()
                            .stream()
                            .map(ActionFactory::getType)
                            .collect(Collectors.toList()));

            (new ConcreteActionFactory(aType)).makeAction(activePlayer).start();
            */
            /*
            List<ActionFactory> legalMoves = activePlayer.getMoves();
            ListeerPool = new ListenerPool(legalMoves.foreach(getTriggerListener));
            MyEvent = ListenerPool.waitAny();
            MyEvent.process();
            ExecutedActions += MyEvent.isSuccesful() ? 1 : 0;
            */
    }
    protected abstract void endOfTurn();
}
