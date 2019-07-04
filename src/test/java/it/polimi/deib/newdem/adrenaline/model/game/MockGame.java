package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrack;
import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackData;
import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackListener;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.items.Deck;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.ArrayList;
import java.util.List;

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
    public void setGameListener(GameListener listener) {

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

    @Override
    public void setKillTrackListener(KillTrackListener listener) {

    }

    @Override
    public List<Player> getPlayers(){
        return new ArrayList<>(0);
    }

    @Override
    public void declareOver() {

    }

    @Override
    public Deck<PowerUpCard> getPowerUpDeck() {
        return null;
    }

    @Override
    public GameData generateGameData() {
        return null;
    }

    @Override
    public KillTrackData generateKillTrackData() {
        return null;
    }

    @Override
    public GameResults generateResults() {
        return null;
    }

    @Override
    public void concludeGame() {

    }

    @Override
    public KillTrack getKillTrack() {
        return null;
    }

    @Override
    public Deck<WeaponCard> getWeaponDeck() {
        return null;
    }
}
