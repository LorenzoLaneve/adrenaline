package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.GameView;

public class GUIGameView implements GameView {

    private GUIGameWindow window;


    public GUIGameView(GUIGameWindow window) {
        this.window = window;
    }


    @Override
    public void setGameData(GameData data) {

    }

    @Override
    public void disablePlayer(PlayerColor color) {

    }

    @Override
    public void enablePlayer(PlayerColor color) {

    }

}
