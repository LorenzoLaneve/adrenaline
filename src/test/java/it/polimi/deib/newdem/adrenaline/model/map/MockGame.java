package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameListener;
import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackListener;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.items.Deck;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.List;

public class MockGame implements Game {

    private Map map;
    @Override
    public Map getMap() {
        return map;
    }

    public MockGame(Map map){
        this.map = map;
    }

    @Override
    public void setGameListener(GameListener listener) {

    }

    @Override
    public void setKillTrackListener(KillTrackListener listener) {

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
    public boolean shouldGoFrenzy() {
        return false;
    }

    @Override
    public Turn getNextTurn() {
        return null;
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
    public void init() {

    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public int getTurnTime() {
        return 0;
    }

    @Override
    public List<Player> getPlayers() {
        return null;
    }

    @Override
    public void declareOver() {

    }

    @Override
    public Deck<PowerUpCard> getPowerUpDeck() {
        return null;
    }
}
