package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;

public class WeaponCallAction extends ActionBaseImpl {

    private Weapon weapon;


    public WeaponCallAction(Player actor, ActionDataSource actionDataSource, Weapon weapon) {
        super(actor, actionDataSource, actor.getGame());
        this.weapon = weapon;
        // TODO implement
    }

    @Override
    public void damageDealtTrigger(Player attacker, Player victim) {

    }

    @Override
    public void damageTakenTrigger(Player attacker, Player victim) {

    }
}
