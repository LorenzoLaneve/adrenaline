package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicAction;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.*;

public class ActionType implements Serializable {
    /**
     * Represent the composite type of an action.
     *
     * Can be used for comparison operation
     */

    private final List<AtomicActionType> atoms;

    /**
     * Builds an ActionType from an arbitrary number of atomic types passed as argiments.
     *
     * @param atoms one or more elements of {@code AtomicActionTypes}
     */
    public ActionType(AtomicActionType ... atoms) {
        if(0 >= atoms.length) throw new IllegalArgumentException();
        this.atoms = Arrays.asList(atoms);
    }

    /**
     * MAkes use of the primes assigned to {@code AtomicActionType} to easily compute an hash.
     *
     * This does not take order into account, so it is not consistent with {@code .equals()}. However, this should never come up in real cases.
     * @return
     */
    @Override
    public int hashCode() {
        int prod = 1;
        for(AtomicActionType a : atoms){
            prod *= a.getId();
        }
        return prod;
    }

    /**
     * This takes the order into account
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        int i;
        if (!(obj instanceof ActionType)) return false;
        if (this.atoms.size() != ((ActionType) obj).atoms.size()) return false;
        // assuming they are in the same order.
        for (i = 0; i < this.atoms.size(); i++) {
            if (this.atoms.get(i)
                !=
            (((ActionType) obj).atoms.get(i)
                    )) {
                return false;
            }
        }
        return true;
    }


    /**
     * Returns whether the given action type is covered by this action type, meaning that
     * using this action type allows to do all the moves allowed by the given action type.
     */
    public boolean covers(ActionType actionType) {
        for (AtomicActionType aat : actionType.getAtomicTypes()) {
            /*if(!this.atoms.contains(aat)) {
                return false;
            }*/
            if(!this.atoms.stream().anyMatch(ownAtom -> ownAtom.covers(aat))) {
                return false;
            }
        }
        return true;
        /*
        int k = 0;
        List<AtomicActionType> typesToCover = actionType.atoms;

        for (AtomicActionType aType : atoms) {
            if (k >= typesToCover.size()) {
                return true;
            }

            while (k < typesToCover.size() && !aType.covers(typesToCover.get(k))) { k++; }
            k++;
        }

        return false;
        */
    }

    public List<AtomicActionType> getAtomicTypes() {
        return new ArrayList<>(atoms);
    }

}
