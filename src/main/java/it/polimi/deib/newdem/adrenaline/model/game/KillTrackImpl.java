package it.polimi.deib.newdem.adrenaline.model.game;

import java.util.ArrayList;
import java.util.List;

public class KillTrackImpl implements KillTrack {

    private List<Player> killers;
    private int initialTrackLength;

    /** Creates a new {@code KillTrack} with {@code trackLength} initial skulls on it.
     *
     * @param trackLength Amount of initial skulls on the track. Between 5 and 8, inclusive.
     */
    public KillTrackImpl(int trackLength){
        if(trackLength < 5 || trackLength > 8) {
            throw new IllegalArgumentException("TrackLength must be between 5 and 8 inclusive");
        }

        killers = new ArrayList<>(0);
        initialTrackLength = trackLength;
    }

    /**
     *  Register a kill for {@code Player}
     *
     * @param player the killer. Not null.
     */
    @Override
    public void registerKill(Player player) {
        //TODO interaction with a listener
        if(null == player) {
            throw new IllegalArgumentException("Player must not be null");
        }

        killers.add(player);
    }

    /**
     * Identifies the {@code Player} that executed the {@code killIndex}-th kill.
     *
     * @param killIndex The kill index. Must be between 0 (inclusive) and {@code getTotalKills()} (exclusive)
     * @return The {@code Player} responsible for the kill
     */
    @Override
    public Player getKiller(int killIndex) {
        if(killIndex < 0 || killIndex > getTotalKills()) {
            throw new IndexOutOfBoundsException("KillIndex must be between 0 inclusive and getTotalKills() exclusive");
        }

        return killers.get(killIndex);
    }

    /**
     * Returns the initial length of this {@code KillTrack}
     *
     * @return the initial length of the {@code KillTrack}
     */
    @Override
    public int getTrackLength() {
        return initialTrackLength;
    }

    /**
     * Returns the total amount of kills up to now.
     *
     * @return the amount of kills
     */
    @Override
    public int getTotalKills() {
        return killers.size();
    }
}
