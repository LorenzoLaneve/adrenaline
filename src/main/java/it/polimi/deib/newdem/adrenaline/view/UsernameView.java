package it.polimi.deib.newdem.adrenaline.view;

public interface UsernameView {

    String prompt();

    void reportError(String message);

    void reportSuccess();

}
