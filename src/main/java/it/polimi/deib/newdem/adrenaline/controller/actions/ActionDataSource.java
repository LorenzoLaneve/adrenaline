package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public interface ActionDataSource {

    Player actionDidRequestPlayerBinding(MetaPlayer metaPlayer, PlayerSelector selector);

    Tile actionDidRequestTile(TileSelector selector);

    int actionDidRequestChoiche(List<Effect> choices);

    int actionDidRequestAdditionalDamage();

    int actionDidRequestRevengeMark(Player attackedPlayer);

    void actionDidEmitGameChange(GameChange gameChange);
}
