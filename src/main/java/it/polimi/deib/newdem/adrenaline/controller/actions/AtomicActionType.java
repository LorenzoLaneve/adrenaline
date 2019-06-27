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

    public boolean isMoveAction() {
        return this == MOVE1 || this == MOVE2 || this == MOVE3 || this == MOVE4;
    }

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

    public boolean covers(AtomicActionType that) {
        if (this.isMoveAction() && that.isMoveAction()) {
            return this.getMoveDistance() < that.getMoveDistance();
        }
        return this == that;
    }

}
