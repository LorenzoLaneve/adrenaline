package it.polimi.deib.newdem.adrenaline.model.game;


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
