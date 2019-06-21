package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

public class CLIHelper {

    public static String colorToString(PlayerColor color) {
        switch (color) {
            case YELLOW:
                return "Yellow";
            case GREEN:
                return "Green";
            case GRAY:
                return "Gray";
            case MAGENTA:
                return "Magenta";
            case CYAN:
                return "Cyan";
        }
        return null;
    }

}
