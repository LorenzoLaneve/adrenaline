package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

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

    public static Pane createTilePane(TilePosition tilePosition) {
        try {
            Pane tilePane = (Pane) FXMLLoader.load(GUIGameWindowHelper.class.getResource("/gui/lobby.fxml"));
            tilePane.setId("tileSlot"+ tilePosition.getX() +"_"+ tilePosition.getY());

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



    public static Group createCardPane(int cardID) {
        try {
            Image image = new Image(GUIGameWindowHelper.class.getResource("/gui/assets/cards/weapon"+ cardID +".png").openStream());

            ImageView imgView = new ImageView(image);
            imgView.setFitWidth(104);
            imgView.setFitHeight(158);

            return new Group(imgView);
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

    public static Pane createPlayerPanel(PlayerColor color) {
        try {
            Pane tilePane = FXMLLoader.load(GUIGameWindowHelper.class.getResource("/gui/player-view.fxml"));
            tilePane.getStyleClass().add(toStyleClass(color));

            return tilePane;
        } catch (IOException e) {
            return null;
        }
    }





}
