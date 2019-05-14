package it.polimi.deib.newdem.adrenaline.controller;

public interface GameManagerFactory {

    GameManager makeGameManager();

    /**
     * Returns a game manager factory of the appropriate class according to the given configuration.
     */
    static GameManagerFactory create() {
        return new AdrenalineGameManagerFactory();
    }

}
