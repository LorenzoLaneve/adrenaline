package it.polimi.deib.newdem.adrenaline.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

/**
 * A configuration object mantaining all the information used by the server.
 */
public final class Config {

    private boolean useSockets = true;

    private boolean useRMI = true;

    private int socketPort = 9700;

    private int rmiPort = 9701;

    private String rmiIdentifier = "adrenaline";


    private int timerLength = 3;

    private int minPlayers = 3;

    private int maxPlayers = 5;

    private int turnTime = 1;

    private GameControllerFactory gameMaker = new AdrenalineGameControllerFactory();

    private Config() {  }


    private static JsonElement getMember(JsonObject object, String id) throws InvalidConfigException {
        JsonElement elem = object.get(id);
        if (elem == null) throw new InvalidConfigException("Missing value for "+ id);
        return elem;
    }

    /**
     * Parses the given configuration file into a new configuration info object.
     * @param fileName the filename of the configuration file that has to be read.
     * @return a new {@code Config} object containing all the information retrieved by the given file.
     */
    public static Config fromFile(String fileName) throws InvalidConfigException {
        Config config = new Config();

        try (FileReader fread = new FileReader(fileName)) {
            JsonObject configJson = new JsonParser().parse(fread).getAsJsonObject();

            config.useSockets = getMember(configJson, "useSockets").getAsBoolean();
            config.useRMI = getMember(configJson, "useRMI").getAsBoolean();
            config.socketPort = getMember(configJson, "socketPort").getAsInt();
            config.rmiPort = getMember(configJson, "rmiPort").getAsInt();
            config.rmiIdentifier = getMember(configJson, "rmiIdentifier").getAsString();

            config.timerLength = getMember(configJson, "timerLength").getAsInt();
            config.minPlayers = getMember(configJson, "minPlayers").getAsInt();
            config.maxPlayers = getMember(configJson, "maxPlayers").getAsInt();
            config.turnTime = getMember(configJson, "turnTime").getAsInt();

        } catch (Exception x) {
            throw new InvalidConfigException(x.getMessage());
        }

        return config;
    }


    /**
     * Returns the hardcoded default configuration.
     * @implNote This should only be used in absence of a configuration file.
     */
    public static Config getDefaultConfig() {
        return new Config();
    }

    /**
     * Returns the length in seconds that timers used by the controller should have when the lobby enters in the countdown state.
     */
    public int getTimerLength() {
        return timerLength;
    }

    /**
     * Returns the minimum numbers of players that lobbies in this server need to start a new game.
     */
    public int getMinPlayers() {
        return minPlayers;
    }

    /**
     * Returns the maximum numbers of players that lobbies in this server need to start the game immediately.
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Returns the TCP port the socket of the server should listen to new socket connections from.
     */
    public int getSocketPort() {
        return socketPort;
    }

    /**
     * Returns the TCP port the socket of the server should listen to new RMI connections from.
     */
    public int getRMIPort() { return rmiPort; }

    /**
     * Return the URI for the server object that will wait for new RMI user connections.
     */
    public String getRMIIdentifier() { return rmiIdentifier; }

    /**
     * Returns whether the socket module should be added to the modules used by the server instance.
     */
    public boolean isSocketActive(){
        return useSockets;
    }

    /**
     * Returns whether the RMI module should be added to the modules used by the server instance.
     */
    public boolean isRMIActive(){
        return useRMI;
    }

    /**
     * Returns the factory object used to create new game controllers for the .
     */
    public GameControllerFactory getGameControllerFactory() {
        return gameMaker;
    }

    /**
     * Returns the time for an ordinary turn in seconds
     */
    public int getTurnTime(){
        return turnTime;
    }

}
