package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.*;

public abstract class EffectVisitorBase implements EffectVisitor {

    private Deque<Effect> undoStack;

    private Deque<Effect> scheduledEvents;

    private Map<Effect, List<Effect>> children;
    // parent-children relationships

    private Map<Effect, List<GameChange>> gameChanges;

    private Map<MetaPlayer, Player> boundPlayers;

    private Map<Effect, List<MetaPlayer>> playerPromptRegistry;
    // effects that asked for a player.

    private List<Effect> promptRegistry;
    // effects that asked for a tile or choice.


    /**
     * Creates a new and empty EmptyVisitorBase.
     */
    public EffectVisitorBase() {
        this.undoStack = new ArrayDeque<>();
        this.scheduledEvents = new ArrayDeque<>();

        this.children = new HashMap<>();
        this.gameChanges = new HashMap<>();

        this.boundPlayers = new EnumMap<>(MetaPlayer.class);
        this.playerPromptRegistry = new HashMap<>();

        this.promptRegistry = new ArrayList<>();
    }

    private Effect getCurrentEffect() {
        return undoStack.peekLast();
    }

    private List<Effect> getChildrenEffects(Effect e) {
        return children.getOrDefault(e, new ArrayList<>());
    }

    private boolean effectPromptedSomething(Effect e) {
        return playerPromptRegistry.get(e) != null || promptRegistry.contains(e);
    }


    /**
     * Executes the given effect.
     * @throws UndoException if the user, during any prompt, requested to undo the action.
     */
    public void visit(Effect startEffect) throws UndoException {
        if (startEffect == null) {
            throw new IllegalArgumentException("The effect to execute must not be null.");
        }

        scheduledEvents.addFirst(startEffect);

        while (!scheduledEvents.isEmpty()) {
            undoStack.addLast(scheduledEvents.pollFirst());

            try {
                getCurrentEffect().apply(this);

                List<Effect> children = getChildrenEffects(getCurrentEffect());
                for (int i = children.size() - 1; i >= 0; i--) {
                    scheduledEvents.addFirst(children.get(i));
                }
            } catch (UndoException x) {

                boolean shouldStop = false;
                while (!shouldStop) {
                    if (undoStack.isEmpty()) {
                        throw x;
                    }

                    shouldStop = effectPromptedSomething(getCurrentEffect());
                    undoEffect();
                }
            }
        }

    }

    /**
     * Reverts all the game changes reported by the last effect, in the reverse of the application order,
     * removes all the events scheduled by this event from the queue and
     * reverts the queues to the state before the last effect was applied.
     */
    private void undoEffect() {
        List<GameChange> lgc = gameChanges.get(getCurrentEffect());
        if (lgc != null) {
            for (int i = lgc.size()-1; i >= 0; i--) {
                revertGameChange(lgc.get(i));
            }
            lgc.clear();
        }

        scheduledEvents.removeAll(getChildrenEffects(getCurrentEffect()));
        getChildrenEffects(getCurrentEffect()).clear();


        List<MetaPlayer> boundMetaPlayers = playerPromptRegistry.getOrDefault(getCurrentEffect(), new ArrayList<>());
        for (MetaPlayer metaPlayer : boundMetaPlayers) {
            boundPlayers.put(metaPlayer, null);
        }
        boundMetaPlayers.clear();

        while (promptRegistry.contains(getCurrentEffect())) {
            promptRegistry.remove(getCurrentEffect());
        }

        scheduledEvents.addFirst(undoStack.pollLast());
    }


    @Override
    public Player getBoundPlayer(MetaPlayer player, PlayerSelector selector) throws UndoException {
        Player p = boundPlayers.get(player);

        if (p == null) {
            p = askForPlayer(player, selector);

            List<MetaPlayer> metaPlayers = playerPromptRegistry.get(getCurrentEffect());

            if (metaPlayers == null) {
                metaPlayers = new ArrayList<>();
                playerPromptRegistry.put(getCurrentEffect(), metaPlayers);
            }
            metaPlayers.add(player);
        }

        return p;
    }

    @Override
    public Tile getTile(TileSelector selector) throws UndoException {
        Tile t = askForTile(selector);
        promptRegistry.add(getCurrentEffect());

        return t;
    }

    @Override
    public Effect chooseEffect(List<Effect> choices) throws UndoException {
        Effect e = askForEffectChoice(choices);
        promptRegistry.add(getCurrentEffect());

        return e;
    }

    @Override
    public void scheduleEffect(Effect effect) {
        List<Effect> l = children.get(getCurrentEffect());
        if (l == null) {
            l = new ArrayList<>();
            children.put(effect, l);
        }
        l.add(effect);
    }

    @Override
    public void reportGameChange(GameChange gameChange) {
        applyGameChange(gameChange);

        List<GameChange> lgc = gameChanges.get(getCurrentEffect());
        if (lgc == null) {
            lgc = new ArrayList<>();
            gameChanges.put(getCurrentEffect(), lgc);
        }
        lgc.add(gameChange);
    }



    public abstract Player askForPlayer(MetaPlayer player, PlayerSelector selector) throws UndoException;

    public abstract Tile askForTile(TileSelector selector) throws UndoException;

    public abstract Effect askForEffectChoice(List<Effect> choices) throws UndoException;

    public abstract void applyGameChange(GameChange gameChange);

    public abstract void revertGameChange(GameChange gameChange);

}
