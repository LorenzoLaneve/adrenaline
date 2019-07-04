package it.polimi.deib.newdem.adrenaline.model.game;

/**
 * A game change is an object that manages the state transition of the game,
 * providing methods to revert the transitions.
 */
public interface GameChange {

    /**
     * Applies the game change to the given Game object.
     */
    void update(Game game);

    /**
     * Reverts the changes made by this game change to the given Game object.
     */
    void revert(Game game);
}
