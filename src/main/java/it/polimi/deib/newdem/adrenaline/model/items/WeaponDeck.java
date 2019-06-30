package it.polimi.deib.newdem.adrenaline.model.items;

import com.google.gson.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectLoader;
import it.polimi.deib.newdem.adrenaline.controller.effects.InvalidEffectException;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.utils.FileUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WeaponDeck {

    // static for singleton implementation
    private static List<WeaponCard> cards;

    // deprecated
    private WeaponDeck(List<WeaponCard> cards) {
        this.cards = new ArrayList<>(cards);
    }

    /**
     * Creates a new Deck object playable and modifiable by other objects
     * using the cards previously loaded.
     * @return new playable {@code Deck<WeaponCard>}
     */
    public static Deck<WeaponCard> createNewDeck() {
        if(null == cards) throw new IllegalStateException();
        return new Deck<>(cards);
    }


    /**
     * Utility that parses a PaymentInvoice from a card during the
     * JSON decoding process
     *
     * @param element payment invoice as json element
     * @return POJO PaymentInvoice
     */
    private static PaymentInvoice parseInvoice(JsonElement element) {
        JsonObject invoiceObject = element.getAsJsonObject();

        int redAmmos = invoiceObject.get("red").getAsInt();
        int blueAmmos = invoiceObject.get("blue").getAsInt();
        int yellowAmmos = invoiceObject.get("yellow").getAsInt();
        int anyColorAmmos = invoiceObject.get("any").getAsInt();

        return new PaymentInvoice(redAmmos, blueAmmos, yellowAmmos, anyColorAmmos);
    }

    public static void loadCardsFromJson(String jsonFile) throws InvalidJSONException, DeckAlreadyLoadedException {
        if(null != cards) throw new DeckAlreadyLoadedException();
        cards = new ArrayList<>();

        String path = FileUtils.getAbsoluteDecodedFilePath(jsonFile, WeaponDeck.class);

        try (FileReader reader = new FileReader(path)) {
            JsonObject deckJsonObject = new JsonParser().parse(reader).getAsJsonObject();

            JsonArray cardsJsonArray = deckJsonObject.get("cards").getAsJsonArray();

            for (JsonElement object : cardsJsonArray) {
                JsonObject cardObject = object.getAsJsonObject();

                int cardID = cardObject.get("id").getAsInt();

                PaymentInvoice pickupPrice = parseInvoice(cardObject.get("pickupPrice"));
                PaymentInvoice reloadPrice = parseInvoice(cardObject.get("reloadPrice"));

                String effectClassName = cardObject.get("effectClass").getAsString();
                Effect cardEffect = EffectLoader.fromClass(effectClassName);

                WeaponCard card = new WeaponCardImpl(cardID, pickupPrice, reloadPrice, cardEffect);
                cards.add(card);
            }

        } catch (Exception e) {
            throw new InvalidJSONException(e.getMessage());
        }
    }

    // deprecated
    public static WeaponDeck fromJson(String jsonFile) throws InvalidJSONException {
        List<WeaponCard> cards = new ArrayList<>();

        try (FileReader reader = new FileReader(jsonFile)) {
            JsonObject deckJsonObject = new JsonParser().parse(reader).getAsJsonObject();

            JsonArray cardsJsonArray = deckJsonObject.get("cards").getAsJsonArray();

            for (JsonElement object : cardsJsonArray) {
                JsonObject cardObject = object.getAsJsonObject();

                int cardID = cardObject.get("id").getAsInt();

                PaymentInvoice pickupPrice = parseInvoice(cardObject.get("pickupPrice"));
                PaymentInvoice reloadPrice = parseInvoice(cardObject.get("reloadPrice"));

                String effectClassName = cardObject.get("effectClass").getAsString();
                Effect cardEffect = EffectLoader.fromClass(effectClassName);

                WeaponCard card = new WeaponCardImpl(cardID, pickupPrice, reloadPrice, cardEffect);
                cards.add(card);
            }

        } catch (Exception e) {
            throw new InvalidJSONException(e.getMessage());
        }

        return new WeaponDeck(cards);
    }

}
