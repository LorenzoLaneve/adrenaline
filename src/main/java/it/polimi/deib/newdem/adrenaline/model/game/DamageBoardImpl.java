package it.polimi.deib.newdem.adrenaline.model.game;

import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Math.min;

public abstract class DamageBoardImpl implements DamageBoard {

    protected Player player;
    protected List<Player> damages;
    protected ArrayList<Integer> score; // this member must be populated by concrete classes
    protected HashMap<Player, Integer> marks;
    public static final int MAX_LIFE = 11;
    public static final int DEATH_SHOT_INDEX = 9;
    // Arrays start at zero

    // TODO add listener member
    private DamageBoardListener listener; //???????????????????????????????

    private class ScoreboardEntry {
        private final Player p;
        private final int totalScore;
        private final int earliestShot;

        public ScoreboardEntry(Player p, int totalScore, int earliestShot) {
            this.p = p;
            this.totalScore = totalScore;
            this.earliestShot = earliestShot;
        }

        public int getTotalScore() {
            return totalScore;
        }

        public int getEarliestShot() {
            return earliestShot;
        }

        public Player getPlayer() {
            return p;
        }
    }

    private class Scoreboard {
        private ArrayList<ScoreboardEntry> entries;

        private Comparator<ScoreboardEntry> damageboardComparator = (e1, e2) -> {
            if (null == e1 || null == e2) throw new IllegalArgumentException();
            else if (e1.getTotalScore() < e2.getTotalScore())
                return -1;
            else if (e1.getTotalScore() > e2.getTotalScore())
                return 1;
            else if (e1.getEarliestShot() > e2.getEarliestShot())
                return -1;
            else if (e1.getEarliestShot() < e2.getEarliestShot())
                return 1;
            else return 0;

        };

        public Scoreboard() {
            entries = new ArrayList<>();
        }

        public void registerEntry(ScoreboardEntry e) {
            entries.add(e);
        }

        public int getPlacement(Player p) {
            entries.sort(damageboardComparator.reversed());
            for (int i = 0; i < entries.size(); i++) {
                if (entries.get(i).getPlayer().equals(p)) {
                    return i;
                }
            }
            return -1;
        }
    }


    /** Builds a {@code DamageBoardImplementation} bound to {@code player}.
     *
     * @param player Player to bind the damage board to. Not null, not with {@code DamageBoard} already.
     */
    public DamageBoardImpl(Player player) {
        if(null == player) {
            throw new IllegalArgumentException("player must not be null");
        }
        //TODO complete
        // how do I check that a player already has a damageBoard? Do I even need to?
        damages = new ArrayList<>(MAX_LIFE);
        marks = new HashMap<>();
        this.player = player;
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
     * Register damage from another player.
     * @param dmgAmount amount of damage taken. Not negative, not zero.
     * @param attacker player that dealt the damage. Not null.
     */
    @Override
    public void takeDamage(int dmgAmount, Player attacker) {
        if(null == attacker || dmgAmount < 0) {
            throw new IllegalArgumentException();
        }
        while(damages.size() <= MAX_LIFE && dmgAmount > 0) {
            damages.add(attacker);
            dmgAmount--;
        }
    }

    /**
     * Register mark(s) from another player.
     * @param markAmount amount of marks taken
     * @param attacker player that dealt the marks
     */
    @Override
    public void takeMark(int markAmount, Player attacker) {
        if(null == attacker) {
            throw new IllegalArgumentException();
        }
        if(markAmount < 0) {
            throw new IndexOutOfBoundsException();
        }

        marks.put(attacker,
                min(marks.getOrDefault(attacker, 0) + markAmount, 3));
    }

    /**
     * Identifies the player that dealt the {@code index}-th point of damage.
     * @param index Index slot, between 0 and 12 inclusive
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
     * @return
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

        int place = s.getPlacement(player);
        if (place < 0) return 0; // player did not deal damage

        int totalScore = 0;
        if (place + this.player.getDeaths() < score.size()) {
            totalScore += score.get(place + this.player.getDeaths());
        }
        if(shouldAssignFirstBlood() && damages.get(0).equals(player)) {
            totalScore++;
        }

        return max(totalScore, 1);
    }

    protected abstract boolean shouldAssignFirstBlood();

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
}
