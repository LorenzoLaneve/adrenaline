package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;

import java.util.List;

public interface TurnView {

    PlayerColor askForPlayer(MetaPlayer metaPlayer, List<PlayerColor> allowedPlayers);

    TilePosition askForTile(List<TilePosition> legalTiles);

    int askForChoice(List<Integer> allowedChoices);


    /*
    Player actionDidRequestPlayer(MetaPlayer metaPlayer, PlayerSelector selector);

    Tile actionDidRequestTile(TileSelector selector);

    int actionDidRequestChoice(List<Effect> choices);

    int actionDidRequestAdditionalDamage();

    int actionDidRequestRevengeMark(Player attackedPlayer);

    void actionDidEmitGameChange(GameChange gameChange);
    */
}
