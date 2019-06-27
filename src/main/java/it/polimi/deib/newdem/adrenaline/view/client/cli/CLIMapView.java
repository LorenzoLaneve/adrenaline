package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.MapData;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.MapView;
import it.polimi.deib.newdem.adrenaline.view.MapViewEventListener;

import java.io.PrintStream;

public class CLIMapView implements MapView {

    private PrintStream out;



    public CLIMapView(PrintStream out) {
        this.out = out;
    }

    @Override
    public void addEventListener(MapViewEventListener listener) {
        // TODO
    }

    @Override
    public void removeEventListener(MapViewEventListener listener) {
        // TODO
    }

    @Override
    public void updateView(MapData data) {
        out.println("Using map with ID "+ data.getMapID() +" for this game.");

        out.println("Tiles: ");
        for (TilePosition tile : data.getTiles()) {
            out.print(CLIHelper.tilePositionToString(tile) + " with " + CLIHelper.dropInstanceToString(data.getDropInTile(tile)));
        }
        out.println();

        out.print("\nRed spawn point is at "+ CLIHelper.tilePositionToString(data.getRedSpawnPoint()));
        out.println(" with weapons:");
        for (int cardID : data.getRedWeaponSet()) {
            out.println(cardID +" - "+ CLIHelper.getWeaponName(cardID));
        }


        out.print("\nBlue spawn point is at "+ CLIHelper.tilePositionToString(data.getBlueSpawnPoint()));
        out.println(" with weapons:");
        for (int cardID : data.getBlueWeaponSet()) {
            out.println(cardID +" - "+ CLIHelper.getWeaponName(cardID));
        }

        out.print("\nYellow spawn point is at "+ CLIHelper.tilePositionToString(data.getYellowSpawnPoint()));
        out.println(" with weapons:");
        for (int cardID : data.getYellowWeaponSet()) {
            out.println(cardID +" - "+ CLIHelper.getWeaponName(cardID));
        }

    }

    @Override
    public void addDrops(TilePosition tile, GameData.DropType drop1, GameData.DropType drop2, GameData.DropType drop3) {
        out.print("A new drop ");
        out.print(CLIHelper.getStringFromDropType(drop1) + CLIHelper.getStringFromDropType(drop2) + CLIHelper.getStringFromDropType(drop3));

        out.print("has spawned in tile "+ CLIHelper.tilePositionToString(tile));
        out.println();
    }

    @Override
    public void movePlayer(PlayerColor player, TilePosition destTile) {
        out.print("Player "+ CLIHelper.colorToString(player) +" moved to tile "+ CLIHelper.tilePositionToString(destTile));
        out.println();
    }

    @Override
    public void spawnPlayer(PlayerColor player, TilePosition tile) {
        out.print("Player "+ CLIHelper.colorToString(player) +" has spawned in tile "+ CLIHelper.tilePositionToString(tile));
        out.println();
    }

    @Override
    public void killPlayer(PlayerColor player) {
        out.println("Player "+ CLIHelper.colorToString(player) +" has been killed!");
    }

    @Override
    public void removePlayer(PlayerColor player) {
        out.println("Player "+ CLIHelper.colorToString(player) +" pin has been removed from the map");
    }

    @Override
    public void addWeapon(TilePosition tilePosition, int cardId) {
        out.print("Weapon "+ cardId +"("+ CLIHelper.getWeaponName(cardId) +") has spawned in tile "+ CLIHelper.tilePositionToString(tilePosition));
        out.println();
    }

    @Override
    public void acquireDrop(TilePosition tile, PlayerColor player, GameData.DropType drop1, GameData.DropType drop2, GameData.DropType drop3) {
        out.print("Player "+ CLIHelper.colorToString(player) +" has picked up the drop ");
        out.print(CLIHelper.getStringFromDropType(drop1) + CLIHelper.getStringFromDropType(drop2) + CLIHelper.getStringFromDropType(drop3));
        out.print(" in tile "+ CLIHelper.tilePositionToString(tile));
        out.println();
    }
}
