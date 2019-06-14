package it.polimi.deib.newdem.adrenaline.model.map;

import org.omg.PortableServer.THREAD_POLICY_ID;
import org.omg.SendingContext.RunTime;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class TestingMapBuilder {
    public static Map getNewMap(Class c) {
        Map map;
        try {
            String encodedPath = c.getClassLoader().getResource("JsonData.json").getFile();
            String decodedPath = URLDecoder.decode(encodedPath, StandardCharsets.UTF_8.name());
            map = Map.createMap(decodedPath);
        }
        catch(UnsupportedEncodingException e) {
            // won't happen
            throw new RuntimeException(e);
        }
        return map;
    }
}
