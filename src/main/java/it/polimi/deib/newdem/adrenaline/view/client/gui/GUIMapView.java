package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.MapData;
import it.polimi.deib.newdem.adrenaline.model.map.PlayerTilePair;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.MapView;
import it.polimi.deib.newdem.adrenaline.view.MapViewEventListener;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GUIMapView implements MapView {

    private GUIGameWindow window;

    private HashMap<TilePosition, AmmoColor> spawnPointLocs;

    public GUIMapView(GUIGameWindow window) {
        this.window = window;

        this.spawnPointLocs = new HashMap<>();
    }


    @Override
    public void updateView(MapData data) {
        Platform.runLater(() -> {
            String relPath = "gui/assets/tables/"+ data.getMapID() +".png";

            String mapImagePath = getClass().getClassLoader().getResource(relPath).toExternalForm();

            window.getScene().lookup(".map-pane")
                    .setStyle("-fx-background-image: url('"+ mapImagePath +"');");

            Pane mapGrid = (Pane) window.getScene().lookup("#mapGrid");
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    mapGrid.getChildren().add(GUIGameWindowHelper.createTilePane(new TilePosition(j, i)));
                }
            }

            for (TilePosition tile : data.getTiles()) {
                DropInstance drop = data.getDropInTile(tile);
                if (drop != null) {
                    GUIGameWindowHelper.addDropsToTilePane(window.getScene(), tile, drop);
                }
            }

            spawnPointLocs.put(data.getRedSpawnPoint(), AmmoColor.RED);
            spawnPointLocs.put(data.getBlueSpawnPoint(), AmmoColor.BLUE);
            spawnPointLocs.put(data.getYellowSpawnPoint(), AmmoColor.YELLOW);

            for (Integer wCard : data.getRedWeaponSet()) {
                Pane spawnPointCards = (Pane) window.getScene().lookup("#redSpawnPointCards");
                spawnPointCards.getChildren().add(GUIGameWindowHelper.createSpawnPointCardPane(wCard));
            }

            for (Integer wCard : data.getBlueWeaponSet()) {
                Pane spawnPointCards = (Pane) window.getScene().lookup("#blueSpawnPointCards");
                spawnPointCards.getChildren().add(GUIGameWindowHelper.createSpawnPointCardPane(wCard));
            }

            for (Integer wCard : data.getYellowWeaponSet()) {
                Pane spawnPointCards = (Pane) window.getScene().lookup("#yellowSpawnPointCards");
                spawnPointCards.getChildren().add(GUIGameWindowHelper.createSpawnPointCardPane(wCard));
            }

            for (PlayerTilePair pair : data.getPlayerLocations()) {
                Pane playerPin = GUIGameWindowHelper.getPlayerPin(window.getScene(), pair.getPlayer());
                if (playerPin != null) ((Pane) playerPin.getParent()).getChildren().remove(playerPin);

                GUIGameWindowHelper.addPlayerToTilePane(window.getScene(), pair.getPlayer(), pair.getTile());
            }

            window.closeDialog(); // close loading data
        });
    }

    @Override
    public void addDrops(TilePosition tile, GameData.DropType drop1, GameData.DropType drop2, GameData.DropType drop3) {
        Platform.runLater(() -> {
            Pane tilePane = GUIGameWindowHelper.lookupTilePane(window.getScene(), tile);

            Pane dropsPane = (Pane) tilePane.lookup(".tile-drops-pane");
            dropsPane.getChildren().add(GUIGameWindowHelper.createDropIcon(drop1));
            dropsPane.getChildren().add(GUIGameWindowHelper.createDropIcon(drop2));
            dropsPane.getChildren().add(GUIGameWindowHelper.createDropIcon(drop3));
        });
    }

    @Override
    public void removeDrops(TilePosition tile) {
        Platform.runLater(() -> {
            Pane tilePane = GUIGameWindowHelper.lookupTilePane(window.getScene(), tile);

            Pane dropsPane = (Pane) tilePane.lookup(".tile-drops-pane");
            dropsPane.getChildren().clear();
        });
    }

    @Override
    public void movePlayer(PlayerColor player, TilePosition destTile) {
        Platform.runLater(() -> {
            Pane playerPin = GUIGameWindowHelper.getPlayerPin(window.getScene(), player);
            ((Pane) playerPin.getParent()).getChildren().remove(playerPin);

            GUIGameWindowHelper.addPlayerToTilePane(window.getScene(), player, destTile);
        });
    }

    @Override
    public void spawnPlayer(PlayerColor player, TilePosition tile) {
        Platform.runLater(() -> {
            Pane playerPin = GUIGameWindowHelper.getPlayerPin(window.getScene(), player);
            if (playerPin != null) ((Pane) playerPin.getParent()).getChildren().remove(playerPin);

            GUIGameWindowHelper.addPlayerToTilePane(window.getScene(), player, tile);
        });
    }

    @Override
    public void killPlayer(PlayerColor player) {
        Platform.runLater(() -> GUIGameWindowHelper.getPlayerPin(window.getScene(), player).getStyleClass().add("dead"));
    }

    @Override
    public void removePlayer(PlayerColor player) {
        Platform.runLater(() -> {
            Pane playerPin = GUIGameWindowHelper.getPlayerPin(window.getScene(), player);
            if (playerPin != null) {
                ((Pane) playerPin.getParent()).getChildren().remove(playerPin);
            }
        });
    }


    private static String ammoToID(AmmoColor ammoColor) {
        switch (ammoColor) {
            case RED:
                return "red";
            case BLUE:
                return "blue";
            case YELLOW:
                return "yellow";
        }
        return null;
    }

    @Override
    public void addWeapon(TilePosition tilePosition, int cardId) {
        Platform.runLater(() -> {
            AmmoColor spawnPointColor = spawnPointLocs.get(tilePosition);
            if (spawnPointColor != null) {
                Pane spawnPointCards = (Pane) window.getScene().lookup("#"+ ammoToID(spawnPointColor) +"SpawnPointCards");
                spawnPointCards.getChildren().add(GUIGameWindowHelper.createSpawnPointCardPane(cardId));
            }
        });
    }

    @Override
    public void removeWeapon(TilePosition tilePosition, int cardId) {
        Platform.runLater(() -> {
            AmmoColor spawnPointColor = spawnPointLocs.get(tilePosition);
            if (spawnPointColor != null) {
                window.getScene().getRoot().applyCss();
                Pane spawnPointCards = (Pane) window.getScene().lookup("#"+ ammoToID(spawnPointColor) +"SpawnPointCards");
                spawnPointCards.getChildren().remove(spawnPointCards.lookup(".weapon-"+ cardId));
            }
        });
    }

}
