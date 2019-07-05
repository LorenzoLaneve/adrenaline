package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

/**
 * Atomic action types.
 *
 * They are associated with a small prime to allow for a simple has function
 * for composite types.
 */
public enum AtomicActionType {

    /**
     * Move of radius 4
     */
    MOVE4(2),

    /**
     * Move of radius 3
     */
    MOVE3(3),

    /**
     * Move of radius 2
     */
    MOVE2(5),

    /**
     * Move of radius 1
     */
    MOVE1(7),

    /**
     * Grab stuff off the ground
     */
    GRAB(11),

    /**
     * Use a weapon
     */
    SHOOT(13),

    /**
     * Reload a weapon
     */
    RELOAD(19),

    /**
     * Use a powerup
     */
    USE_POWERUP(23),

    /**
     * Take revenge from damage dealt
     */
    REVENGE(29);

    // note to self: following primes are 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,...

    private int id;

    AtomicActionType(int prime) {
        this.id = prime;
    }

    /**
     * Returns a unique identifier for the given {@code AtomicActionType}.
     *
     * @return unique id
     */
    public int getId() {
        return id;
    }

    /**
     * Checks if the given {@code ActionType} is one of MOVE*
     *
     * @return whether this MOVE*
     */
    public boolean isMoveAction() {
        return this == MOVE1 || this == MOVE2 || this == MOVE3 || this == MOVE4;
    }

    /**
     * Retrieves the move distance associated with this {@code AtomicActionType}.
     *
     * If this {@code AtomicActionType} is not MOVE*, will return 0.
     *
     * @return move distance
     */
    public int getMoveDistance() {
        switch (this) {
            case MOVE4:
                return 4;
            case MOVE3:
                return 3;
            case MOVE2:
                return 2;
            case MOVE1:
                return 1;
            default:
                return 0;
        }
    }

    /**
     * Checks if this {@code AtomicActionType} covers the one passed as a parameter,
     * meaning this can do at least everything that can be done by that.
     *
     * @param that to check for cover
     * @return this covers that
     */
    public boolean covers(AtomicActionType that) {
        if (this.isMoveAction() && that.isMoveAction()) {
            return this.getMoveDistance() >= that.getMoveDistance();
        }
        return this == that;
    }

}
