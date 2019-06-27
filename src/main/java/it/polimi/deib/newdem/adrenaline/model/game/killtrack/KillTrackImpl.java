package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.utils.Scoreboard;
import it.polimi.deib.newdem.adrenaline.model.game.utils.ScoreboardEntry;

import java.util.ArrayList;
import java.util.List;

public class KillTrackImpl implements KillTrack {

    private List<Cell> kills;
    private int initialTrackLength;
    private KillTrackListener listener;
    private int[] score;

    public static final int MIN_KILLTRACK_SIZE = 5 - 1; // arrays start at zero
    public static final int MAX_KILLTRACK_SIZE = 8 - 1;

    /** Creates a new {@code KillTrack} with {@code trackLength} initial skulls on it.
     *
     * @param trackLength Amount of initial skulls on the track. Between 5 and 8, inclusive.
     */
    public KillTrackImpl(int trackLength){
        if(trackLength < MIN_KILLTRACK_SIZE || trackLength > MAX_KILLTRACK_SIZE) {
            throw new IllegalArgumentException("TrackLength must be between 5 and 8 inclusive");
        }

        kills = new ArrayList<>(0);
        initialTrackLength = trackLength;
        listener = new NullKillTrackLister();
        score = new int[6];
        score[0] = 8;
        score[1] = 6;
        score[2] = 4;
        score[3] = 2;
        score[4] = 1;
        score[5] = 1;

    }

    /**
     *  Register a kill for {@code Player}
     *
     * @param player the killer. Not null.
     */
    @Override
    public void addKill(Player player, int amount) {
        if(null == player) {
            throw new IllegalArgumentException("Player must not be null");
        }
        if(!(0 <= amount && amount <= 2)) throw new IndexOutOfBoundsException();
        kills.add(new Cell(player, amount));
        listener.playerDidKill(player, amount);
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

        return kills.get(killIndex).getKiller();
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
        return kills.size();
    }

    @Override
    public void setListener(KillTrackListener listener) {
        this.listener = listener;
    }

    @Override
    public int getScoreForPlayer(Player player) {
        Scoreboard sb = makeScoreboard();
        int placement = sb.getPlacement(player);

        try{
            return score[placement];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    private ScoreboardEntry makeScoreboardEntry(Player player) {

        int points = 0;
        int earliest = getTotalKills() + 1;

        for(int i = 0; i < kills.size(); i++) {
            if(kills.get(i).getKiller().equals(player)) {
                points += kills.get(i).getAmount();
                earliest = Math.min(earliest, i);
                }
            }
        return new ScoreboardEntry(player, points, earliest);
    }

    private Scoreboard makeScoreboard() {
        ArrayList<Player> players = new ArrayList<>();

        for(Cell c : kills) {
            if(!players.contains(c.getKiller())) players.add(c.getKiller());
        }

        Scoreboard s = new Scoreboard();

        for(Player p : players) {
            s.registerEntry(makeScoreboardEntry(p));
        }

        return s;
    }

    @Override
    public KillTrackData generateKillTrackData() {
        KillTrackData data = new KillTrackData(this);
        for(Cell c : kills) {
            data.addCell(c);
        }
        return data;
    }
}
