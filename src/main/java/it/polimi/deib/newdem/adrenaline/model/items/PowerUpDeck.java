package it.polimi.deib.newdem.adrenaline.model.items;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectLoader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PowerUpDeck {
    private List<PowerUpCard> cards;

    private PowerUpDeck(List<PowerUpCard> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Deck<PowerUpCard> createNewDeck() {
        return new Deck<>(cards);
    }

    private static AmmoColor parseColor(JsonObject cardObject) {

        String colorString = cardObject.get("color").getAsString();

        switch (colorString){
            case ("blue"):
                return AmmoColor.BLUE;
            case ("red"):
                return AmmoColor.RED;
            case ("yellow"):
                return AmmoColor.YELLOW;
            default:
                return null;
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

                AmmoColor ammoColor = parseColor(cardObject);

                String effectClassName = cardObject.get("effectClass").getAsString();
                Effect cardEffect = EffectLoader.fromClass(effectClassName);

                PowerUpCard card = new PowerUpCardImpl(cardID,ammoColor,cardEffect);
                cards.add(card);
            }

        } catch (Exception e) {
            throw new InvalidJSONException(e.getMessage());
        }

        return new PowerUpDeck(cards);
    }
}
