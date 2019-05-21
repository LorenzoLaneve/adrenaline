package it.polimi.deib.newdem.adrenaline.view.inet.socket;

import it.polimi.deib.newdem.adrenaline.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.MapView.DropType;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class StreamHelper {

    private StreamHelper() {  }

    /**
     * Reads a zero-delimited string from the given stream.
     */
    public static String readString(DataInputStream input) throws IOException {
        StringBuilder sb = new StringBuilder();

        char c;
        while ((c = input.readChar()) != 0) {
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
        output.writeChar(0);
    }

    public static void writePlayerColor(DataOutputStream output, PlayerColor playerColor) throws  IOException{
        switch (playerColor){
            case MAGENTA:
                output.writeInt(0x1);
                break;
            case CYAN:
                output.writeInt(0x2);
                break;
            case GRAY:
                output.writeInt(0x3);
                break;
            case YELLOW:
                output.writeInt(0x4);
                break;
            case GREEN:
                output.writeInt(0x5);
                break;
        }
    }

    public static void writeTilePosition(DataOutputStream output, TilePosition tilePosition) throws IOException{
        output.writeInt(tilePosition.getX());
        output.writeInt(tilePosition.getY());
    }

    public static void writeDropType(DataOutputStream output, DropType drop) throws IOException{
        switch (drop){
            case POWER_UP:
                output.writeInt(0x1);
                break;
            case RED_AMMO:
                output.writeInt(0x2);
                break;
            case BLUE_AMMO:
                output.writeInt(0x3);
                break;
            case YELLOW_AMMO:
                output.writeInt(0x4);
                break;
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
