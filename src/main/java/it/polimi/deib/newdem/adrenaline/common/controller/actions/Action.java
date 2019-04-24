package it.polimi.deib.newdem.adrenaline.common.controller.actions;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.common.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

public interface Action {

    Player getActor();

    Effect getEffect();
    /*
    At the time of writing, Effect hasn't been introduced
    not even as a stub

    TODO validate
     */

    void bindListener(ActionListener listener);

    void start();

    void emitGameChange(GameChange gameChange);
}