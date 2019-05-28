package it.polimi.deib.newdem.adrenaline.view.inet.socket;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.MapView.DropType;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StreamHelper {

    public static final int MAGENTA_PLAYER_COLOR_CODE = 0x1;
    public static final int CYAN_PLAYER_COLOR_CODE = 0x2;
    public static final int GRAY_PLAYER_COLOR_CODE = 0x3;
    public static final int YELLOW_PLAYER_COLOR_CODE = 0x4;
    public static final int GREEN_PLAYER_COLOR_CODE = 0x5;
    public static final int STRING_SEPARATOR_INT = 0;
    public static final int LIST_HAS_NEXT = 1;
    public static final int LIST_HAS_NOT_NEXT = 0;
    public static final int POWER_UP_DROP_CODE = 0x1;
    public static final int RED_AMMO_DROP_CODE = 0x2;
    public static final int BLUE_AMMO_DROP_CODE = 0x3;
    public static final int YELLOW_AMMO_DROP_CODE = 0x4;

    private StreamHelper() {

    }

    /**
     * Reads a zero-delimited string from the given stream.
     */
    public static String readString(DataInputStream input) throws IOException {
        StringBuilder sb = new StringBuilder();

        char c;
        while ((c = input.readChar()) != STRING_SEPARATOR_INT) {
            sb.append(c);
        }

        return sb.toString();
    }

    /**
     * Writes the given string in the given stream, appending a zero after the data.
     */
    public static void writeString(DataOutputStream output, String str) throws IOException {
        for (char c : str.toCharArray()) {
            output.writeChar(c);
        }
        output.writeChar(STRING_SEPARATOR_INT);
    }

    public static PlayerColor readPlayerColor(DataInputStream dataInputStream) throws IOException, InvalidMessageException {
        int code = dataInputStream.readInt();
        switch (code) {
            case MAGENTA_PLAYER_COLOR_CODE:
                return PlayerColor.MAGENTA;
            case GRAY_PLAYER_COLOR_CODE:
                return PlayerColor.GRAY;
            case YELLOW_PLAYER_COLOR_CODE:
                return PlayerColor.YELLOW;
            case GREEN_PLAYER_COLOR_CODE:
                return PlayerColor.GREEN;
            case CYAN_PLAYER_COLOR_CODE:
                return PlayerColor.CYAN;
            default:
                throw new InvalidMessageException("Invalid player color");
        }
    }

    public static void writePlayerColor(DataOutputStream output, PlayerColor playerColor) throws  IOException{
        switch (playerColor){
            case MAGENTA:
                output.writeInt(MAGENTA_PLAYER_COLOR_CODE);
                break;
            case CYAN:
                output.writeInt(CYAN_PLAYER_COLOR_CODE);
                break;
            case GRAY:
                output.writeInt(GRAY_PLAYER_COLOR_CODE);
                break;
            case YELLOW:
                output.writeInt(YELLOW_PLAYER_COLOR_CODE);
                break;
            case GREEN:
                output.writeInt(GREEN_PLAYER_COLOR_CODE);
                break;
        }
    }

    public static void writeTilePosition(DataOutputStream output, TilePosition tilePosition) throws IOException{
        output.writeInt(tilePosition.getX());
        output.writeInt(tilePosition.getY());
    }

    public static TilePosition readTilePosition(DataInputStream dataInputStream) throws IOException {
        return new TilePosition(
                dataInputStream.readInt(),
                dataInputStream.readInt()
        );
    }

    public static DropType readDropType(DataInputStream dataInputStream) throws IOException, InvalidMessageException {
        int drop = dataInputStream.readInt();
        switch (drop) {
            case POWER_UP_DROP_CODE:
                return DropType.POWER_UP;
            case RED_AMMO_DROP_CODE:
                return DropType.RED_AMMO;
            case BLUE_AMMO_DROP_CODE:
                return DropType.BLUE_AMMO;
            case YELLOW_AMMO_DROP_CODE:
                return DropType.YELLOW_AMMO;
                default:
                    throw new InvalidMessageException();
        }
    }

    public static void writeDropType(DataOutputStream output, DropType drop) throws IOException{
        switch (drop){
            case POWER_UP:
                output.writeInt(POWER_UP_DROP_CODE);
                break;
            case RED_AMMO:
                output.writeInt(RED_AMMO_DROP_CODE);
                break;
            case BLUE_AMMO:
                output.writeInt(BLUE_AMMO_DROP_CODE);
                break;
            case YELLOW_AMMO:
                output.writeInt(YELLOW_AMMO_DROP_CODE);
                break;
        }
    }

    public static List<TilePosition> readTileList(DataInputStream dataInputStream) throws IOException, InvalidMessageException {
        /*
        I assume there is at least one element in the list. If there isn't,
        it's not possible to differentiate between a part of a tile
        or the end of list value due to the current implementation
        of writeTileList. This may have to change in the future.
         */

        List<TilePosition> list = new ArrayList<>();
        int hasNext;

        do {
            list.add(readTilePosition(dataInputStream));
            hasNext = dataInputStream.readInt();
        } while (LIST_HAS_NEXT == hasNext);

        if(hasNext == LIST_HAS_NOT_NEXT) {
            return list;
        }
        else {
            throw new InvalidMessageException();
        }
    }

    public static void writeTileList(DataOutputStream output, List<TilePosition> tileData) throws IOException{
        Iterator<TilePosition> iterator = tileData.iterator();

        if(iterator.hasNext()){
            writeTilePosition(output, iterator.next());
        }

        while(iterator.hasNext()){
            output.writeInt(1);

            writeTilePosition(output, iterator.next());
        }

        output.writeInt(0);
    }
}
