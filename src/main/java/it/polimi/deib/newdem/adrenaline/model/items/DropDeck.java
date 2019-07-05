package it.polimi.deib.newdem.adrenaline.model.items;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.deib.newdem.adrenaline.utils.FileUtils;

import java.io.Reader;
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

    /**
     * Method to load correctly the ammoset from Json field.
     * @param cardObject Json field of the dropinstance.
     * @return the AmmoSet as loaded from the json
     */
    private static AmmoSet parseAmmoSet(JsonObject cardObject) {

        int red = 0;
        int blue = 0;
        int yellow = 0;

        JsonArray ammoArray = cardObject.get("ammo").getAsJsonArray();

        for (JsonElement ammoElem : ammoArray) {

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
                default: break;
            }
        }

        return new AmmoSet(red, yellow, blue);

    }

    /**
     * Method to load DropDeck from json file in resources.
     * @param jsonFile the source file for the DropDeck.
     * @return the DropDeck loaded from the file.
     * @throws InvalidJSONException in case the file is wrong.
     */
    public static DropDeck fromJson(String jsonFile) throws InvalidJSONException {
        List<DropInstance> cards = new ArrayList<>();

        try (Reader reader = FileUtils.getResourceReader(jsonFile)) {
            JsonObject deckJsonObject = new JsonParser().parse(reader).getAsJsonObject();

            JsonArray cardsJsonArray = deckJsonObject.get("cards").getAsJsonArray();

            for (JsonElement object : cardsJsonArray) {
                JsonObject cardObject = object.getAsJsonObject();

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
