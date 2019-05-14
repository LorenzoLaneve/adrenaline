package it.polimi.deib.newdem.adrenaline.controller;

public class Config {

    private boolean useSockets = true;

    private boolean useRMI = true;

    private int socketPort = 9700;

    private int timerLength = 60;

    private int minPlayers = 3;

    private int maxPlayers = 5;


    private Config() {  }

    /**
     * Parses the given configuration file into a new configuration info object.
     * @param fileName the filename of the configuration file that has to be read.
     * @return a new {@code Config} object containing all the information retrieved by the given file.
     */
    public Config fromFile(String fileName){
        //TODO file reading
        return new Config();
    }

    /**
     * Returns the hardcoded default configuration.
     * @implNote This should only be used in absence of a configuration file.
     */
    public Config getDefaultConfig() {
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
     * Returns the TCP port the socket of the server should listen to new connections from.
     */
    public int getSocketPort() {
        return socketPort;
    }

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

}
