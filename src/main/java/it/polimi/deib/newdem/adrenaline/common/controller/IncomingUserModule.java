package it.polimi.deib.newdem.adrenaline.common.controller;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;

public interface IncomingUserModule {

    void init();

    User newUser();
}
