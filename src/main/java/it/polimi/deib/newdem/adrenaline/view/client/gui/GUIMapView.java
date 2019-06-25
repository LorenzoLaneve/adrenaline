package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.map.MapData;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.MapView;
import it.polimi.deib.newdem.adrenaline.view.MapViewEventListener;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GUIMapView implements MapView {

    private GUIGameWindow window;

    private List<MapViewEventListener> listeners;

    private HashMap<TilePosition, AmmoColor> spawnPointLocs;

    public GUIMapView(GUIGameWindow window) {
        this.window = window;

        this.listeners = new ArrayList<>();
        this.spawnPointLocs = new HashMap<>();

        Platform.runLater(() -> {
            // GUI events subscribing
        });
    }


    @Override
    public void addEventListener(MapViewEventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeEventListener(MapViewEventListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void updateView(MapData data) {
        Platform.runLater(() -> {
            window.getScene().lookup(".map-pane")
                    .setStyle("-fx-background-image: url(\"assets/tables/"+ data.getMapID() +".png\");");

            Pane mapGrid = (Pane) window.getScene().lookup("#mapGrid");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    mapGrid.getChildren().add(GUIGameWindowHelper.createTilePane(new TilePosition(i, j)));
                }
            }

            for (TilePosition tile : data.getTiles()) {
                DropInstance drop = data.getDropInTile(tile);
                if (drop != null) {
                    GUIGameWindowHelper.addDropsToTilePane(window.getScene(), tile, drop);
                }
            }

            spawnPointLocs.put(data.getRedSpawnLocation(), AmmoColor.RED);
            spawnPointLocs.put(data.getBlueSpawnPoint(), AmmoColor.BLUE);
            spawnPointLocs.put(data.getYellowSpawnPoint(), AmmoColor.YELLOW);
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
            ((Pane) playerPin.getParent()).getChildren().remove(playerPin);
        });
    }

    @Override
    public void addWeapon(TilePosition tilePosition, int cardId) {
        Platform.runLater(() -> {
            AmmoColor spawnPointColor = spawnPointLocs.get(tilePosition);
            if (spawnPointColor != null) {
                Pane spawnPointCards = (Pane) window.getScene().lookup("#"+ GUIGameWindowHelper.toStyleClass(spawnPointColor) +"SpawnPointCards");
                spawnPointCards.getChildren().add(GUIGameWindowHelper.createSpawnPointCardPane(cardId));
            }
        });
    }

    @Override
    public void acquireDrop(TilePosition tile, PlayerColor player, GameData.DropType drop1, GameData.DropType drop2, GameData.DropType drop3) {
        Platform.runLater(() -> {
            Pane tilePane = GUIGameWindowHelper.lookupTilePane(window.getScene(), tile);

            Pane dropsPane = (Pane) tilePane.lookup(".tile-drops-pane");
            dropsPane.getChildren().clear();
            // TODO anything else?
        });
    }

}
