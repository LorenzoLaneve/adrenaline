package it.polimi.deib.newdem.adrenaline.common.mechs.actions;

import it.polimi.deib.newdem.adrenaline.common.mechs.effects.Effect;

public interface Action {

    Effect getEffect();
    /*
    At the time of writing, Effect hasn't been introduced
    not even as a stub

    TODO validate
     */

    void bindListener(ActionListener lsitener);

    void unbindListener(ActionListener listener);

    void start();
}
