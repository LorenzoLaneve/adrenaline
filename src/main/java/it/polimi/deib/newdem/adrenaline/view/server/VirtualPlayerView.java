package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerListener;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.view.PlayerView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;

public class VirtualPlayerView implements PlayerView, PlayerListener {

    private VirtualGameView gameView;

    private Player player;

    public VirtualPlayerView(VirtualGameView gameView, Player player) {
        this.gameView = gameView;
        this.player = player;
    }


    @Override
    public void setName(String name) {
        gameView.sendEvent(new PlayerNameEvent(player.getColor(), name));
    }

    @Override
    public void takeControl() {
        gameView.sendEvent(new PlayerActiveEvent(player.getColor()));
    }

    @Override
    public void setScore(int score) {
        gameView.sendEvent(new PlayerScoreEvent(player.getColor(), score));
    }

    @Override
    public void addPowerUpCard(int cardID) {
        for (PlayerColor color : gameView.getPlayers()) {
            if (color == player.getColor()) {
                gameView.sendEvent(new PlayerAcquirePowerUpEvent(player.getColor(), cardID));
            } else {
                gameView.sendEvent(new PlayerAcquirePowerUpEvent(player.getColor(), PowerUpCard.HIDDEN));
            }
        }
    }

    @Override
    public void removePowerUpCard(int cardID) {
        for (PlayerColor color : gameView.getPlayers()) {
            if (color == player.getColor()) {
                gameView.sendEvent(new PlayerDiscardPowerUpEvent(player.getColor(), cardID));
            } else {
                gameView.sendEvent(new PlayerDiscardPowerUpEvent(player.getColor(), PowerUpCard.HIDDEN));
            }
        }
    }

    @Override
    public void addWeaponCard(int cardID) {
        gameView.sendEvent(new PlayerAcquireWeaponEvent(player.getColor(), cardID));
    }

    @Override
    public void removeWeaponCard(int cardID) {
        gameView.sendEvent(new PlayerDiscardWeaponEvent(player.getColor(), cardID));
    }

    @Override
    public void takeDamage(int dmgAmount, PlayerColor dealer) {
        gameView.sendEvent(new PlayerDidReceiveDamageEvent(player.getColor(), dealer, dmgAmount));

    }

    @Override
    public void takeMark(int markAmount, PlayerColor dealer) {
        gameView.sendEvent(new PlayerDidReceiveMarkEvent(player.getColor(), dealer, markAmount));

    }

    @Override
    public void addAmmoSet(int yellowAmount, int redAmount, int blueAmount) {
        gameView.sendEvent(new PlayerDidReceiveAmmoSetEvent(player.getColor(), yellowAmount, redAmount, blueAmount));

    }

    @Override
    public void removeAmmoSet(int yellowAmount, int redAmount, int blueAmount) {
        gameView.sendEvent(new PlayerDidRemoveAmmoSetEvent(player.getColor(), yellowAmount, redAmount, blueAmount));

    }

    @Override
    public void playerTookDamage(int dmgAmount, Player attacker) {
        takeDamage(dmgAmount, attacker.getColor());
    }

    @Override
    public void playerTookMark(int markAmount, Player attacker) {
        takeMark(markAmount, attacker.getColor());

    }

    @Override
    public void playerDidDrawPowerUpCard(PowerUpCard powerUpCard) {
        addPowerUpCard(powerUpCard.getCardID());

    }

    @Override
    public void playerDidDiscardPowerUpCard(PowerUpCard powerUpCard) {
        removePowerUpCard(powerUpCard.getCardID());
    }

    @Override
    public void playerDidGrabDrop(DropInstance dropInstance) {
        //TODO
    }

    @Override
    public void playerDidGrabWeapon(WeaponCard weaponCard) {
        addWeaponCard(weaponCard.getCardID());
    }

    @Override
    public void playerDidDiscardWeapon(WeaponCard weaponCard) {
        removeWeaponCard(weaponCard.getCardID());

    }
}
