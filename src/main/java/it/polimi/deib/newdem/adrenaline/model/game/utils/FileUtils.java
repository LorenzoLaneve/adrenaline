package it.polimi.deib.newdem.adrenaline.model.game.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    public static String getAbsoluteDecodedFilePath(String relativePath, Class c) {
        String encodedPath, decodedPath;

        URL resource = c.getClassLoader().getResource(relativePath);

        try {
            encodedPath = resource.getFile();
        }
        catch (NullPointerException e) {
            throw new IllegalArgumentException(String.format("File %s does not exist", relativePath));
        }

        try {
             decodedPath = URLDecoder.decode(encodedPath, StandardCharsets.UTF_8.name());
        }
        catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(String.format("Path %s is not UTF-8 encoded", encodedPath));
        }

        return decodedPath;
    }

}
