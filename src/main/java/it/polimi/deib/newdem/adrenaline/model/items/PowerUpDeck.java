package it.polimi.deib.newdem.adrenaline.model.items;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectLoader;

import java.io.FileReader;
import java.util.*;

public class PowerUpDeck {

    private List<PowerUpCard> cards;

    private PowerUpDeck(List<PowerUpCard> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Deck<PowerUpCard> createNewDeck() {
        return new Deck<>(cards);
    }




    private static PowerUpTrigger parseTrigger(String jsonTrigger) throws InvalidJSONException {
        switch (jsonTrigger.toLowerCase()) {
            case "call":
                return PowerUpTrigger.CALL;
            case "damagetaken":
                return PowerUpTrigger.DAMAGE_TAKEN;
            case "damagedealt":
                return PowerUpTrigger.DAMAGE_DEALT;
            default:
                throw new InvalidJSONException("Invalid trigger.");
        }
    }

    private static AmmoColor parseAmmoColor(String jsonAmmoColor) throws InvalidJSONException {
        switch (jsonAmmoColor.toLowerCase()) {
            case "red":
                return AmmoColor.RED;
            case "yellow":
                return AmmoColor.YELLOW;
            case "blue":
                return AmmoColor.BLUE;
            default:
                throw new InvalidJSONException("Invalid ammo color.");
        }
    }


    public static PowerUpDeck fromJson(String jsonFile) throws InvalidJSONException {
        List<PowerUpCard> cards = new ArrayList<>();

        try (FileReader reader = new FileReader(jsonFile)) {
            JsonObject deckJsonObject = new JsonParser().parse(reader).getAsJsonObject();

            JsonArray cardsJsonArray = deckJsonObject.get("cards").getAsJsonArray();

            for (JsonElement object : cardsJsonArray) {
                JsonObject cardObject = object.getAsJsonObject();

                int cardID = cardObject.get("id").getAsInt();

                String effectClassName = cardObject.get("effectClass").getAsString();
                Effect cardEffect = EffectLoader.fromClass(effectClassName);

                PowerUpTrigger trigger = parseTrigger(cardObject.get("trigger").getAsString());

                AmmoColor equivalentAmmo = parseAmmoColor(cardObject.get("equivAmmo").getAsString());

                PowerUpCard card = new PowerUpCardImpl(cardEffect, cardID, trigger, equivalentAmmo);
                cards.add(card);
            }

        } catch (Exception e) {
            throw new InvalidJSONException(e.getMessage());
        }

        return new PowerUpDeck(cards);
    }
}
