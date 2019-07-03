package it.polimi.deib.newdem.adrenaline.view.client.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.utils.FileUtils;
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

public class GUIGameWindowHelper {

    private GUIGameWindowHelper() { }

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

    public static String toStyleClass(AmmoColor color) {
        switch (color) {
            case RED:
                return "red-ammo";
            case BLUE:
                return "blue-ammo";
            case YELLOW:
                return "yellow-ammo";
        }
        return null;
    }

    public static Pane createTilePane(TilePosition tilePosition) {
        try {
            Pane tilePane = FXMLLoader.load(GUIGameWindowHelper.class.getResource("/gui/tile.fxml"));
            tilePane.setId("tileSlot"+ tilePosition.getX() +"_"+ tilePosition.getY());

            Pane playersPane = (Pane) tilePane.lookup(".tile-players-pane");
            playersPane.getChildren().add(new Label("("+ tilePosition.getX() +", "+ tilePosition.getY() +")"));

            return tilePane;
        } catch (IOException e) {
            return null;
        }
    }

    public static Pane createPlayerPin(PlayerColor color) {
        Pane pin = new Pane();

        pin.getStyleClass().add("player-pin");
        pin.getStyleClass().add(toStyleClass(color));
        return pin;
    }

    public static Pane createDropIcon(GameData.DropType dropType) {
        Pane icon = new Pane();

        icon.getStyleClass().add("drop-icon");
        icon.getStyleClass().add(toStyleClass(dropType));
        return icon;
    }



    public static Group createSpawnPointCardPane(int cardID) {
        try {
            Image image = new Image(GUIGameWindowHelper.class.getResource("/gui/assets/cards/weapon"+ cardID +".png").openStream());

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

    public static Group createPowerUpCardPane(int cardID) {
        try {
            String imgLocation = cardID < 0 ?
                    "/gui/assets/cards/powerup_flipped.png" :
                    "/gui/assets/cards/powerup"+ cardID +".png";

            Image image = new Image(GUIGameWindowHelper.class.getResource(imgLocation).openStream());

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

    public static Group createWeaponCardPane(int cardID) {
        try {
            String imgLocation = "/gui/assets/cards/weapon"+ cardID +".png";

            Image image = new Image(GUIGameWindowHelper.class.getResource(imgLocation).openStream());

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


    public static Pane createDamageIcon(PlayerColor color) {
        Pane pane = new Pane();

        pane.getStyleClass().add("dmg");
        pane.getStyleClass().add(toStyleClass(color));
        return pane;
    }

    public static Pane lookupTilePane(Scene scene, TilePosition position) {
        return (Pane) scene.lookup("#tileSlot"+ position.getX() +"_"+ position.getY());
    }


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

    public static void addPlayerToTilePane(Scene scene, PlayerColor color, TilePosition destTile) {
        Pane playersPane = (Pane) GUIGameWindowHelper.lookupTilePane(scene, destTile).lookup(".tile-players-pane");
        playersPane.getChildren().add(createPlayerPin(color));
    }

    public static Pane getPlayerPin(Scene scene, PlayerColor color) {
        return (Pane) scene.lookup(".player-pin."+ toStyleClass(color));
    }





    public static void setActiveWeaponCard(Scene scene, int cardID) {
        StackPane activeCardSlot = (StackPane) scene.lookup("#activeCardSlot");
        activeCardSlot.getChildren().clear();

        activeCardSlot.getChildren().add(createWeaponCardPane(cardID));
        activeCardSlot.getChildren().add(createActiveCardGrid(cardID));
    }

    private static Node createActiveCardGrid(int cardID) {
        try {
            GridPane tilePane = FXMLLoader.load(GUIGameWindowHelper.class.getResource("/gui/active-card-grid.fxml"));

            InputStream res = GUIGameWindowHelper.class.getResource("/cards/weaponuserdata.json").openStream();

            try (InputStreamReader fread = new InputStreamReader(res)) {
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

            } catch (Exception x) {
                Alert a = new Alert(Alert.AlertType.ERROR);

                StringWriter s = new StringWriter();
                x.printStackTrace(new PrintWriter(s));

                a.setContentText(s.toString());
                a.showAndWait();
            }

            return tilePane;
        } catch (IOException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);

            StringWriter s = new StringWriter();
            e.printStackTrace(new PrintWriter(s));

            a.setContentText(s.toString());
            a.showAndWait();
            return null;
        }
    }


}
