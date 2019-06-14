package it.polimi.deib.newdem.adrenaline.model.items;

import com.google.gson.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectLoader;
import it.polimi.deib.newdem.adrenaline.controller.effects.InvalidEffectException;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WeaponDeck {

    private List<WeaponCard> cards;

    private WeaponDeck(List<WeaponCard> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Deck<WeaponCard> createNewDeck() {
        return new Deck<>(cards);
    }




    private static PaymentInvoice parseInvoice(JsonElement element) {
        JsonObject invoiceObject = element.getAsJsonObject();

        int redAmmos = invoiceObject.get("red").getAsInt();
        int blueAmmos = invoiceObject.get("blue").getAsInt();
        int yellowAmmos = invoiceObject.get("yellow").getAsInt();
        int anyColorAmmos = invoiceObject.get("any").getAsInt();

        return new PaymentInvoice(redAmmos, blueAmmos, yellowAmmos, anyColorAmmos);
    }

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
