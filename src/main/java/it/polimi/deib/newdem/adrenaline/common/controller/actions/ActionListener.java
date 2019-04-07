package it.polimi.deib.newdem.adrenaline.common.controller.actions;

import it.polimi.deib.newdem.adrenaline.common.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.common.model.map.Tile;

import java.util.List;

public interface ActionListener {

    Player actionDidRequestPlayerBinding(MetaPlayer metaPlayer, PlayerSelector selector);

    Tile actionDidRequestTile(TileSelector selector);

    int actionDidRequestChoiche(List<Effect> choices);

    int actionDidRequestAdditionalDamage();

    int actionDidRequestRevengeMark(Player attackedPlayer);

    void actionDidEmitGameChange(GameChange gameChange);
}
