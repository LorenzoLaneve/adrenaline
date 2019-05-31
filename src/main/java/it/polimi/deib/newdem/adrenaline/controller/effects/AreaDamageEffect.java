package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelectorFactory;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class AreaDamageEffect extends ConcreteEffect {

    private int damageAmount;
    private int markAmount;

    private TileSelectorFactory selectorMaker;

    public AreaDamageEffect(int id, int dmgAmt, int mrkAmt, TileSelectorFactory selectorMaker) {
        super(id);
        this.selectorMaker = selectorMaker;

        this.damageAmount = dmgAmt;
        this.markAmount = mrkAmt;
    }

    @Override
    public void apply(EffectVisitor visitor) {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        Map gameMap = attacker.getTile().getMap();

        for (Tile tile : gameMap.selectTiles(selectorMaker.makeSelector(visitor))) {
            for (Player player : tile.getPlayers()) {
                visitor.reportGameChange(new DamageGameChange(attacker, player, damageAmount, markAmount));
            }
        }

    }
}
