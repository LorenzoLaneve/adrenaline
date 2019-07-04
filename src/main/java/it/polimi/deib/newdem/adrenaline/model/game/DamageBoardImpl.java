package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.utils.Scoreboard;
import it.polimi.deib.newdem.adrenaline.model.game.utils.ScoreboardEntry;

import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Math.min;

public abstract class DamageBoardImpl implements DamageBoard {

    protected Player player;
    protected List<Player> damages;
    protected ArrayList<Integer> score; // this member must be populated by concrete classes
    protected Map<Player, Integer> marks;
    // integer max size of damage vector
    public static final int MAX_LIFE = 11;
    public static final int DEATH_SHOT_INDEX = 10;
    public static final int MAX_MARKS = 3;
    public static final int OVERKILL_SHOT_INDEX = DEATH_SHOT_INDEX + 1;
    // Arrays start at zero

    private DamageBoardListener listener;

    /** Builds a {@code DamageBoardImplementation} bound to {@code player}.
     *
     * @param player Player to bind the damage board to. Not null, not with {@code DamageBoard} already.
     */
    public DamageBoardImpl(Player player) {
        if(null == player) {
            throw new IllegalArgumentException("player must not be null");
        }

        this.player = player;
        damages = new ArrayList<>(MAX_LIFE);

        // preserve old marks if applicable
        if(null != player.getDamageBoard()) {
            this.marks = player.getDamageBoard().getMarksMap();
        }
        else {
            this.marks = new HashMap<>();
        }
    }

    /**
     * Returns the player associated with this {@code DamageBoard}.
     * @return the player associated with this {@code DamageBoard}
     */
    @Override
    public Player getPlayer() {
        return player;
    }

    /**
     * Identifies the player that dealt the {@code index}-th point of damage.
     * @param index Index slot, between 0 and 11 inclusive
     * @return player that dealt the damage
     */
    @Override
    public Player getDamager(int index) {
        if(index < 0 || index > 12) {
            throw new IndexOutOfBoundsException("index must be between 0 and 12 inclusive");
        }

        if(damages.size() <= index) {
            // no damage was deal in this slot
            return null;
        }
        else {
            return damages.get(index);
        }
    }

    @Override
    public void setListener(DamageBoardListener listener) {
        this.listener = listener;
    }

    @Override
    public DamageBoardListener getListener() {
        return listener;
    }

    /**
     * Calculates total damage taken from {@code Player}.
     * @param player damage source
     * @return total damage taken
     */
    @Override
    public int getTotalDamageFromPlayer(Player player) {
        return damages.stream()
                .mapToInt(attacker -> attacker.equals(player) ? 1 : 0)
                .sum();
    }

    /**
     * Calculates total marks taken from {@code Player}.
     * @param player marks source
     * @return total marks
     */
    @Override
    public int getTotalMarksFromPlayer(Player player) {
        return marks.getOrDefault(player, 0);
    }

    /**
     * Calculates the score for {@code player} from this board
     *
     * @param player the player to calculate damage for
     * @return Score, zero or more. If {@code p} did not deal damage, zero will be returned.
     */
    @Override
    public int getScoreForPlayer(Player player) {
        int earliestDamage;
        Scoreboard s = new Scoreboard();
        for (Player p : new HashSet<>(damages)) {
            for (earliestDamage = 0; ; earliestDamage++) {
                if (damages.get(earliestDamage) == p) break;
            }
            s.registerEntry(new ScoreboardEntry(
                    p,
                    getTotalDamageFromPlayer(p),
                    earliestDamage
            ));
        }

        // player did not deal damage
        int place = s.getPlacement(player);
        if (place < 0) return 0;

        // score for amount of damage dealt
        int totalScore = 0;
        if (place + this.player.getDeaths() < score.size()) {
            totalScore += score.get(place + this.player.getDeaths());
        }

        // bonus first blood point
        if(shouldAssignFirstBlood() && damages.get(0).equals(player)) {
            totalScore++;
        }

        // at this point, player has dealt at least one damage
        return max(totalScore, 1);
    }

    /**
     * Calculates the total damage on this scoreboard and returns it as a simple scalar
     * @return total damage
     */
    @Override
    public int getTotalDamage() {
        return damages.size();
    }

    /**
     * Creates and returns a map reporting all marks currently registered
     *
     * @return marks map
     */
    public Map<Player, Integer> getMarksMap() {
        return new HashMap<>(marks);
    }

    public void appendDamage(Player player) throws DamageTrackFullException {

        if(damages.size() > MAX_LIFE) {
            throw new DamageTrackFullException();
        }

        convertMarksFromPlayerHelper(player);
        // mark conversion functionality is NOT handled by DamageGameChange

        appendDamageTrivial(player);
        // damages.add(player);
        // listener.boardDidTakeDamage(1, 0, player);
    }

    private void convertMarksFromPlayerHelper(Player p) throws DamageTrackFullException {
        int currMarks = getTotalMarksFromPlayer(p);
        boolean didConvert = currMarks > 0;

        try {
            while (currMarks > 0) {
                // damages.add(p);
                appendDamageTrivial(p);
                currMarks--;
            }
        }
        catch (DamageTrackFullException e) {
            // ok
        }
        finally {
            if (didConvert) {
                setMarksFromPlayer(0, p);
            }
        }
    }

    public void appendDamageTrivial(Player p) throws DamageTrackFullException {
        // this does not trigger any listener
        if(damages.size() > MAX_LIFE) throw new DamageTrackFullException();
        damages.add(p);
        listener.boardDidTakeDamage(1, 0, p);
        if(player.getTotalDamage() > DEATH_SHOT_INDEX + 1) {
            player.reportDeath(true);
        }
    }

    public Player popDamage() throws DamageTrackEmptyException {
        if(damages.isEmpty()) {
            throw new DamageTrackEmptyException();
        }
        if (listener != null) listener.boardDidPopDamage();
        return damages.remove(damages.size() - 1);

    }

    @Override
    public void setMarksFromPlayer(int totalMarks, Player player) {
        if(totalMarks > MAX_MARKS) {
            throw new IllegalStateException(String.format("Attempted to set %d marks, more than the maximum allowed (%d)",
                    totalMarks, MAX_MARKS));
        }

        int delta = totalMarks - getTotalMarksFromPlayer(player);
        marks.put(player, totalMarks);
        if (listener != null) listener.boardDidTakeDamage(0, delta, player);
    }

    protected abstract DamageBoard makeNewBoard();

    @Override
    public void renewDamageBoard() {
        player.registerDamageBoard(makeNewBoard());
    }
}
