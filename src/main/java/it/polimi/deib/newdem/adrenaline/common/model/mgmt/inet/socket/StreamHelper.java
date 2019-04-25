package it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.socket;

import java.io.*;

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





}
