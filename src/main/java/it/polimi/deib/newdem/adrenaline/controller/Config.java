package it.polimi.deib.newdem.adrenaline.controller;

public final class Config {

    protected boolean useSockets = true;

    protected boolean useRMI = true;

    protected int socketPort = 9700;

    protected int RMIPort = 9701;

    protected String RMIIdentifier = "adrenaline";


    protected int timerLength = 60;

    protected int minPlayers = 3;

    protected int maxPlayers = 5;

    protected int turnTime = 1;

    protected GameControllerFactory gameMaker = new AdrenalineGameControllerFactory();

    private Config() {  }

    /**
     * Parses the given configuration file into a new configuration info object.
     * @param fileName the filename of the configuration file that has to be read.
     * @return a new {@code Config} object containing all the information retrieved by the given file.
     */
    public static Config fromFile(String fileName){
        //TODO file reading
        return new Config();
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
    public int getRMIPort() { return RMIPort; }

    /**
     * Return the URI for the server object that will wait for new RMI user connections.
     */
    public String getRMIIdentifier() { return RMIIdentifier; }

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
     * Returns the time for an ordinary turn
     */
    public int getTurnTime(){
        return turnTime;
    }

}
