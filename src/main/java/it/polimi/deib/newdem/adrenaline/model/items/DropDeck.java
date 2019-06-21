package it.polimi.deib.newdem.adrenaline.model.items;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DropDeck {
    private List<DropInstance> cards;

    private DropDeck(List<DropInstance> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Deck<DropInstance> createNewDeck() {
        return new Deck<>(cards);
    }

    private static AmmoSet parseAmmoSet(JsonObject cardObject) {

        int red = 0;
        int blue = 0;
        int yellow = 0;

        JsonArray ammoArray = cardObject.get("ammo").getAsJsonArray();

        for (JsonElement ammoElem : ammoArray ){

            String ammoString = ammoElem.getAsString();

            switch (ammoString){
                case ("blue"):
                    blue++;
                    break;
                case ("red"):
                    red++;
                    break;
                case ("yellow"):
                    yellow++;
                    break;
            }
        }

        return new AmmoSet(red, yellow, blue);

    }


    public static DropDeck fromJson(String jsonFile) throws InvalidJSONException {
        List<DropInstance> cards = new ArrayList<>();

        try (FileReader reader = new FileReader(jsonFile)) {
            JsonObject deckJsonObject = new JsonParser().parse(reader).getAsJsonObject();

            JsonArray cardsJsonArray = deckJsonObject.get("cards").getAsJsonArray();

            for (JsonElement object : cardsJsonArray) {
                JsonObject cardObject = object.getAsJsonObject();

                int cardID = cardObject.get("id").getAsInt();
                boolean hasPowerUp = cardObject.get("powerUp").getAsBoolean();

                AmmoSet ammoSet = parseAmmoSet(cardObject);

                DropInstance card = new DropInstance(ammoSet, hasPowerUp);
                cards.add(card);
            }

        } catch (Exception e) {
            throw new InvalidJSONException(e.getMessage());
        }

        return new DropDeck(cards);
    }
}
