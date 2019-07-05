package it.polimi.deib.newdem.adrenaline.view.client.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.*;
import java.net.URL;
import java.util.Map;

/**
 * Class that provide utility methods to interact with the game window.
 */
public class GUIHelper {

    private GUIHelper() { }

    /**
     * Returns a CSS class for the given player color.
     */
    public static String toStyleClass(PlayerColor color) {
        switch (color) {
            case YELLOW:
                return "yellow";
            case CYAN:
                return "cyan";
            case MAGENTA:
                return "magenta";
            case GRAY:
                return "gray";
            case GREEN:
                return "green";
        }
        return null;
    }

    /**
     * Returns a CSS class for the given drop type.
     */
    public static String toStyleClass(GameData.DropType dropType) {
        switch (dropType) {
            case RED_AMMO:
                return "red-ammo";
            case BLUE_AMMO:
                return "blue-ammo";
            case YELLOW_AMMO:
                return "yellow-ammo";
            case POWER_UP:
                return "powerup";
        }
        return null;
    }

    /**
     * Creates and returns a tile pane for the given tile position.
     */
    public static Pane createTilePane(TilePosition tilePosition) {
        try {
            Pane tilePane = FXMLLoader.load(GUIHelper.class.getResource("/gui/tile.fxml"));
            tilePane.setId("tileSlot"+ tilePosition.getX() +"_"+ tilePosition.getY());

            Pane playersPane = (Pane) tilePane.lookup(".tile-players-pane");
            playersPane.getChildren().add(new Label("("+ tilePosition.getX() +", "+ tilePosition.getY() +")"));

            return tilePane;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Creates and returns a player pin pane for the given player color to show in the tile pane.
     */
    public static Pane createPlayerPin(PlayerColor color) {
        Pane pin = new Pane();

        pin.getStyleClass().add("player-pin");
        pin.getStyleClass().add(toStyleClass(color));
        return pin;
    }

    /**
     * Creates and returns an icon representing the given drop type to show in a tile pane.
     */
    public static Pane createDropIcon(GameData.DropType dropType) {
        Pane icon = new Pane();

        icon.getStyleClass().add("drop-icon");
        icon.getStyleClass().add(toStyleClass(dropType));
        return icon;
    }

    /**
     * Creates a Pane showing the weapon card with the given ID to be added to spawn points.
     */
    public static Group createSpawnPointCardPane(int cardID) {
        try {
            Image image = new Image(GUIHelper.class.getResource("/gui/assets/cards/weapon"+ cardID +".png").openStream());

            ImageView imgView = new ImageView(image);
            imgView.setFitWidth(104);
            imgView.setFitHeight(158);

            Group g = new Group(imgView);
            g.getStyleClass().add("weapon-"+ cardID);
            return g;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Creates and returns a new pane showing a power up with the given card ID.
     */
    public static Group createPowerUpCardPane(int cardID) {
        try {
            String imgLocation = cardID < 0 ?
                    "/gui/assets/cards/powerup_flipped.png" :
                    "/gui/assets/cards/powerup"+ cardID +".png";

            Image image = new Image(GUIHelper.class.getResource(imgLocation).openStream());

            ImageView imgView = new ImageView(image);
            imgView.setFitWidth(83);
            imgView.setFitHeight(130);

            Group result = new Group(imgView);
            result.getStyleClass().add(cardID < 0 ? "equipPowerUpHidden" : "equipPowerUp"+ cardID);
            return result;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Creates and returns a new pane showing the weapon with the given card ID.
     */
    public static Group createWeaponCardPane(int cardID) {
        try {
            String imgLocation = "/gui/assets/cards/weapon"+ cardID +".png";

            Image image = new Image(GUIHelper.class.getResource(imgLocation).openStream());

            ImageView imgView = new ImageView(image);
            imgView.setFitWidth(102);
            imgView.setFitHeight(160);

            Group result = new Group(imgView);
            result.getStyleClass().add("equipWeapon"+ cardID);
            return result;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Creates a new pane showing a damage icon with the given color.
     */
    public static Pane createDamageIcon(PlayerColor color) {
        Pane pane = new Pane();

        pane.getStyleClass().add("dmg");
        pane.getStyleClass().add(toStyleClass(color));
        return pane;
    }

    /**
     * Returns a tile pane present in the given scene associated with the given tile position.
     */
    public static Pane lookupTilePane(Scene scene, TilePosition position) {
        return (Pane) scene.lookup("#tileSlot"+ position.getX() +"_"+ position.getY());
    }

    /**
     * Shows the given drops in the tile associated to the given tile position present in the given scene.
     */
    public static void addDropsToTilePane(Scene scene, TilePosition tile, DropInstance drop) {
        Pane dropsPane = (Pane) lookupTilePane(scene, tile).lookup(".tile-drops-pane");

        int reds = drop.getAmmos().getRedAmmos();
        while (reds > 0) {
            dropsPane.getChildren().add(createDropIcon(GameData.DropType.RED_AMMO));
            reds--;
        }

        int blues = drop.getAmmos().getBlueAmmos();
        while (blues > 0) {
            dropsPane.getChildren().add(createDropIcon(GameData.DropType.BLUE_AMMO));
            blues--;
        }

        int yellows = drop.getAmmos().getYellowAmmos();
        while (yellows > 0) {
            dropsPane.getChildren().add(createDropIcon(GameData.DropType.YELLOW_AMMO));
            yellows--;
        }

        if (drop.hasPowerUp()) {
            dropsPane.getChildren().add(createDropIcon(GameData.DropType.POWER_UP));
        }
    }

    /**
     * Shows the player with the given color in the tile pane associated to the given tile position
     * present in the given scene.
     */
    public static void addPlayerToTilePane(Scene scene, PlayerColor color, TilePosition destTile) {
        Pane playersPane = (Pane) GUIHelper.lookupTilePane(scene, destTile).lookup(".tile-players-pane");
        playersPane.getChildren().add(createPlayerPin(color));
    }

    /**
     * Returns the player pin associated to the given player color present in the scene.
     */
    public static Pane getPlayerPin(Scene scene, PlayerColor color) {
        return (Pane) scene.lookup(".player-pin."+ toStyleClass(color));
    }


    /**
     * Shows the weapon card with the given card ID in the scene's active card slot (upper right corner of the map).
     */
    public static void setActiveWeaponCard(Scene scene, int cardID) {
        StackPane activeCardSlot = (StackPane) scene.lookup("#activeCardSlot");
        activeCardSlot.getChildren().clear();

        activeCardSlot.getChildren().add(createWeaponCardPane(cardID));
        activeCardSlot.getChildren().add(createActiveCardGrid(cardID));
    }

    /**
     * Creates and returns a GridPane object containing clickable buttons representing the fragments of the given card ID.
     */
    private static Node createActiveCardGrid(int cardID) {
        try {
            GridPane tilePane = FXMLLoader.load(GUIHelper.class.getResource("/gui/active-card-grid.fxml"));

            URL res = GUIHelper.class.getResource("/cards/weaponuserdata.json");

            try (Reader fread = new InputStreamReader(res.openStream())) {
                JsonObject userData = new JsonParser().parse(fread).getAsJsonObject();
                JsonObject cardDict = userData.get("cards").getAsJsonObject().get("card-"+ cardID).getAsJsonObject();

                for (Map.Entry<String, JsonElement> entry : cardDict.get("fragments").getAsJsonObject().entrySet()) {
                    String id = entry.getKey();
                    String location = entry.getValue().getAsJsonObject().get("location").getAsString();

                    Pane fragmentPane = new Pane();
                    fragmentPane.getStyleClass().add(id);
                    fragmentPane.getStyleClass().add(location);

                    Pane destPane;
                    switch (location) {
                        case "top":
                        case "top-left":
                        case "top-right":
                            destPane = (Pane) tilePane.lookup(".top-buttons");
                            destPane.getChildren().add(fragmentPane);

                            break;
                        default:
                            destPane = (Pane) tilePane.lookup(".bottom-buttons");
                            destPane.getChildren().add(fragmentPane);
                            break;
                    }
                }

            } catch (Exception | Error x) {
                // nothing to do here.
                Alert a = new Alert(Alert.AlertType.INFORMATION);

                StringWriter d = new StringWriter();
                x.printStackTrace(new PrintWriter(d));
                a.setContentText(d.toString());
                a.showAndWait();
            }

            return tilePane;
        } catch (IOException | Error e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);

            StringWriter d = new StringWriter();
            e.printStackTrace(new PrintWriter(d));
            a.setContentText(d.toString());
            a.showAndWait();

            return null;
        }
    }


}
