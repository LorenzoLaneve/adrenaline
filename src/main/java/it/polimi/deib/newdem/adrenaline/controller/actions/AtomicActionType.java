package it.polimi.deib.newdem.adrenaline.controller.actions;

public enum AtomicActionType {
    /**
     * Atomic action types.
     *
     * They are associated with a small prime to allow for a simple has function
     * for composite types.
     */
    MOVE4(2),
    MOVE3(3),
    MOVE2(5),
    MOVE1(7),
    GRAB(11),
    SHOOT(13),
    RELOAD(19),
    USE_POWERUP(23);

    // note to self: following primes are 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,...

    private int id;

    AtomicActionType(int prime) {
        this.id = prime;
    }

    public int getId() {
        return id;
    }
}
