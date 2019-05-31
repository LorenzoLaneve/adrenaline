package it.polimi.deib.newdem.adrenaline.model.items.Parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.*;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class SelectorParser {

    public SelectorParser(){

    }

    public PlayerSelectorFactory parsePlayerSelector(String jsonSelector){
        JsonElement jsonEffectElement = new JsonParser().parse(jsonSelector);
        JsonObject jsonEffectObject = jsonEffectElement.getAsJsonObject();

        String type = jsonEffectObject.get("type").getAsString();

        PlayerSelectorFactory selectorFactory = null;

        switch(type){
            case "AnyPlayerSelector":
                selectorFactory = parseAnyPlayerSelector(jsonEffectObject);
                break;
            case "DirectionalPlayerSelector":
                selectorFactory = parseDirectionalPlayerSelector(jsonEffectObject);
                break;
            case "NearPlayerSelector":
                selectorFactory = parseNearPlayerSelector(jsonEffectObject);
                break;
            case "NegatedPlayerSelector":
                selectorFactory = parseNegatedPlayerSelector(jsonEffectObject);
                break;
            case "VisiblePlayerSelector":
                selectorFactory = parseVisiblePlayerSelector(jsonEffectObject);
                break;

        }

       return selectorFactory;
    }


    public TileSelectorFactory parseTileSelector(String jsonSelector){
        JsonElement jsonEffectElement = new JsonParser().parse(jsonSelector);
        JsonObject jsonEffectObject = jsonEffectElement.getAsJsonObject();

        String type = jsonEffectObject.get("type").getAsString();

        TileSelectorFactory selectorFactory = null;

        switch(type){
            case "AnyTileSelector":
                selectorFactory = parseAnyTileSelector(jsonEffectObject);
                break;
            case "DirectionalTileSelector":
                selectorFactory = parseDirectionalTileSelector(jsonEffectObject);
                break;
            case "NearTileSelector":
                selectorFactory = parseNearTileSelector(jsonEffectObject);
                break;
            case "VisibleTileSelector":
                selectorFactory = parseVisibleTileSelector(jsonEffectObject);
                break;

        }

        return selectorFactory;
    }

    private MetaPlayer parseMetaPlayerString(String metaPlayerString){

        MetaPlayer metaPlayer = null;

        switch (metaPlayerString){
            case "red":
                metaPlayer = MetaPlayer.RED;
                break;
            case "blue":
                metaPlayer = MetaPlayer.BLUE;
                break;
            case "green":
                metaPlayer = MetaPlayer.GREEN;
                break;
            case "yellow":
                metaPlayer = MetaPlayer.YELLOW;
                break;
            case "attacker":
                metaPlayer = MetaPlayer.ATTACKER;
                break;
        }
        return metaPlayer;

    }

    private TileSelectorFactory parseVisibleTileSelector(JsonObject jsonEffectObject) {
        String sourcePlayerString = jsonEffectObject.get("sourcePlayer").getAsString();

        MetaPlayer sourcePlayer = parseMetaPlayerString(sourcePlayerString);

        return new VisibleTileSelectorFactory(sourcePlayer);

    }

    private TileSelectorFactory parseNearTileSelector(JsonObject jsonEffectObject) {
        String sourcePlayerString = jsonEffectObject.get("sourcePlayer").getAsString();

        MetaPlayer sourcePlayer = parseMetaPlayerString(sourcePlayerString);

        int minDist = jsonEffectObject.get("minDist").getAsInt();

        int maxDist = jsonEffectObject.get("maxDist").getAsInt();

        return new NearTileSelectorFactory(sourcePlayer, minDist, maxDist);
    }

    private TileSelectorFactory parseDirectionalTileSelector(JsonObject jsonEffectObject) {
        //TODO
        return null;
    }

    private TileSelectorFactory parseAnyTileSelector(JsonObject jsonEffectObject) {
        return new AnyTileSelectorFactory();
    }


    private PlayerSelectorFactory parseVisiblePlayerSelector(JsonObject jsonEffectObject) {
        String sourcePlayerString = jsonEffectObject.get("sourcePlayer").getAsString();

        MetaPlayer sourcePlayer = parseMetaPlayerString(sourcePlayerString);

        return new VisiblePlayerSelectorFactory(sourcePlayer);
    }

    private PlayerSelectorFactory parseNegatedPlayerSelector(JsonObject jsonEffectObject) {
        String innerSelector = jsonEffectObject.get("innerSelector").getAsString();

        return new NegatedPlayerSelectorFactory(parsePlayerSelector(innerSelector));
    }

    private PlayerSelectorFactory parseNearPlayerSelector(JsonObject jsonEffectObject) {
        String sourcePlayerString = jsonEffectObject.get("sourcePlayer").getAsString();

        MetaPlayer sourcePlayer = parseMetaPlayerString(sourcePlayerString);

        int minDist = jsonEffectObject.get("minDist").getAsInt();

        int maxDist = jsonEffectObject.get("maxDist").getAsInt();

        return new NearPlayerSelectorFactory(sourcePlayer, minDist, maxDist);

    }

    private PlayerSelectorFactory parseDirectionalPlayerSelector(JsonObject jsonEffectObject) {
        //TODO
        return null;

    }

    private PlayerSelectorFactory parseAnyPlayerSelector(JsonObject jsonEffectObject) {

        return new AnyPlayerSelectorFactory();
    }


}
