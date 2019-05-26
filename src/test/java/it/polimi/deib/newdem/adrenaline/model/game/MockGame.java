package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public class MockGame implements Game {

    @Override
    public void init() {

    }

    @Override
    public boolean shouldGoFrenzy() {
        return false;
    }

    @Override
    public Turn getNextTurn() {
        return null;
    }

    @Override
    public Map getMap() {
        return null;
    }

    @Override
    public void setListener(GameListener listener) {

    }

    @Override
    public Player getPlayerFromColor(PlayerColor color) {
        return null;
    }

    @Override
    public boolean isInFrenzy() {
        return false;
    }

    @Override
    public void concludeTurn(Turn turn) {
    }

    @Override
    public void setUserForColor(User user, PlayerColor color) {

    }

    @Override
    public User getUserByPlayer(Player player) {
        return null;
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public int getTurnTime() {
        return 0;
    }
}
