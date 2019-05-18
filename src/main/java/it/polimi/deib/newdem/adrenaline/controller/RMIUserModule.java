package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public class RMIUserModule implements IncomingUserModule {

    public RMIUserModule(){
        //TODO
    }

    @Override
    public void init() {
        //TODO
    }

    @Override
    public synchronized User newUser() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        //TODO implement
    }
}
