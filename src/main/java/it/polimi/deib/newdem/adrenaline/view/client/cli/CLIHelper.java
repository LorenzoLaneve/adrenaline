package it.polimi.deib.newdem.adrenaline.view.client.cli;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.utils.FileUtils;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;

import java.io.FileReader;
import java.io.Reader;

class CLIHelper {

    private CLIHelper() {  }


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

    public static String getWeaponName(int cardID) {
        try (Reader fread = FileUtils.getResourceReader("cards/weaponuserdata.json")) {
            JsonObject userData = new JsonParser().parse(fread).getAsJsonObject();

            JsonObject cardDict = userData.get("cards").getAsJsonObject().get("card-"+ cardID).getAsJsonObject();

            return cardDict.get("screenName").getAsString();
        } catch (Exception x) {
            return "No name found";
        }
    }

    public static String getPowerUpName(int cardID) {
        try (Reader fread = FileUtils.getResourceReader("cards/powerupuserdata.json")) {
            JsonObject userData = new JsonParser().parse(fread).getAsJsonObject();

            JsonObject cardDict = userData.get("cards").getAsJsonObject().get("card-"+ cardID).getAsJsonObject();

            return cardDict.get("screenName").getAsString();
        } catch (Exception x) {
            return "No name found";
        }
    }



    public static String tilePositionToString(TilePosition pos) {
        return "("+ pos.getX() +", "+ pos.getY() +")";
    }

    public static String dropInstanceToString(DropInstance drop) {
        if (drop == null) {
            return "no drop";
        } else {
            StringBuilder ret = new StringBuilder("drop ");
            for (int i = 0; i < drop.getAmmos().getRedAmmos(); i++) ret.append("R");
            for (int i = 0; i < drop.getAmmos().getBlueAmmos(); i++) ret.append("B");
            for (int i = 0; i < drop.getAmmos().getYellowAmmos(); i++) ret.append("Y");
            if (drop.hasPowerUp()) ret.append("P");
            return ret.toString();
        }
    }

    public static String getStringFromDropType(GameData.DropType dType) {
        switch (dType) {
            case RED_AMMO:
                return "R";
            case YELLOW_AMMO:
                return "Y";
            case BLUE_AMMO:
                return "B";
            case POWER_UP:
                return "P";
            default:
                return "-";
        }
    }

}
