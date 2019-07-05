package it.polimi.deib.newdem.adrenaline.view;

/**
 * View object that observes ActionBoard objects in the model.
 */
public interface ActionBoardView {

    /**
     * Notifies that the observed action board flipped due to a switch in frenzy mode.
     */
    void flipActionBoard();

}
