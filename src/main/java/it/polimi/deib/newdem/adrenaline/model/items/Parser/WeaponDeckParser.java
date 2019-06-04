package it.polimi.deib.newdem.adrenaline.model.items.Parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

import java.util.ArrayList;
import java.util.List;

public class WeaponDeckParser {

    private String stringWeapon;

    public Effect parseEffect(String jsonEffect){

        Effect effect = null;

        JsonElement jsonEffectElement = new JsonParser().parse(jsonEffect);
        JsonObject jsonEffectObject = jsonEffectElement.getAsJsonObject();

        String effectType = jsonEffectObject.get("type").getAsString();

        switch (effectType){
            case "EffectSequence":
               effect = parseEffectSequence(jsonEffectObject);
                break;
            case "EffectChoice":
                effect = parseEffectChoice(jsonEffectObject);
                break;
            case "ChainDamageEffect":
                effect = parseChainDamageEffect(jsonEffectObject);
                break;
            case "PowerGloveEffect":
                effect = parsePowerGloveEffect(jsonEffectObject);
                break;
            case "DirectionalDamageEffect":
                effect = parseDirectionalDamageEffect(jsonEffectObject);
                break;
            case "DirectionalTileDamageEffect":
                effect = parseDirectionalTileDamageEffect(jsonEffectObject);
                break;
            case "MoveEffect":
                effect = parseMoveEffect(jsonEffectObject);
                break;
            case "PayableEffect":
                effect = parsePayableEffect(jsonEffectObject);
                break;
            case "PickupEffect":
                effect = parsePickupEffect(jsonEffectObject);
                break;
            case "ReloadWeaponEffect":
                effect = parseReloadWeaponEffect(jsonEffectObject);
                break;
            case "RoomDamageEffect":
                effect = parseRoomDamageEffect(jsonEffectObject);
                break;
            case "SingleDamageEffect":
                effect = parseSingleDamageEffect(jsonEffectObject);
                break;
            case "TeleportEffect":
                effect = parseTeleportEffect(jsonEffectObject);
                break;
            case "TileDamageEffect":
                effect = parseTileDamageEffect(jsonEffectObject);
                break;
            case "VortexDamageEffect":
                effect = parseVortexDamageEffect(jsonEffectObject);
                break;
            default:
                return null;

        }

        return effect;
    }

    private Effect parseVortexDamageEffect(JsonObject jsonEffectObject) {
        //TODO
        return null;
    }

    private Effect parseTileDamageEffect(JsonObject jsonEffectObject) {
        //TODO
        return null;
    }

    private Effect parseTeleportEffect(JsonObject jsonEffectObject) {
        //TODO
        return null;
    }

    private Effect parseSingleDamageEffect(JsonObject jsonEffectObject) {
        int effectID = jsonEffectObject.get("id").getAsInt();

        int dmgAmt = jsonEffectObject.get("dmgAmt").getAsInt();

        int mrkAmt = jsonEffectObject.get("mrkAmt").getAsInt();

        String target = jsonEffectObject.get("target").getAsString();


        return null;
    }

    private Effect parseRoomDamageEffect(JsonObject jsonEffectObject) {
        int effectID = jsonEffectObject.get("id").getAsInt();

        int dmgAmt = jsonEffectObject.get("dmgAmt").getAsInt();

        int mrkAmt = jsonEffectObject.get("mrkAmt").getAsInt();



        return null;
    }


    private Effect parseReloadWeaponEffect(JsonObject jsonEffectObject) {
        //TODO
        return null;
    }

    private Effect parsePickupEffect(JsonObject jsonEffectObject) {

        int effectID = jsonEffectObject.get("id").getAsInt();

        int minDist = jsonEffectObject.get("minDist").getAsInt();

        int maxDist = jsonEffectObject.get("maxDist").getAsInt();

        return new PickupEffect(effectID, minDist, maxDist);
    }

    private Effect parsePayableEffect(JsonObject jsonEffectObject) {

        int effectID = jsonEffectObject.get("id").getAsInt();

        int redAmmos = jsonEffectObject.get("price").getAsJsonObject().get("redAmmos").getAsInt();

        int yellowAmmos = jsonEffectObject.get("price").getAsJsonObject().get("yellowAmmos").getAsInt();

        int blueAmmos = jsonEffectObject.get("price").getAsJsonObject().get("blueAmmos").getAsInt();

        int anyAmmos = jsonEffectObject.get("price").getAsJsonObject().get("anyAmmos").getAsInt();

        PaymentInvoice paymentInvoice = new PaymentInvoice(redAmmos, blueAmmos, yellowAmmos, anyAmmos);

        Effect nestedEffect = parseEffect(jsonEffectObject.get("effect").getAsString());

        return new PayableEffect(effectID, paymentInvoice, nestedEffect);
    }

    private Effect parseMoveEffect(JsonObject jsonEffectObject) {

        int effectID = jsonEffectObject.get("id").getAsInt();

        int minDist = jsonEffectObject.get("minDist").getAsInt();

        int maxDist = jsonEffectObject.get("maxDist").getAsInt();

        return new MoveEffect(effectID, minDist, maxDist);
    }


    private Effect parseDirectionalTileDamageEffect(JsonObject jsonEffectObject) {

        int effectID = jsonEffectObject.get("id").getAsInt();

        int dmgAmt = jsonEffectObject.get("dmgAmt").getAsInt();

        int mrkAmt = jsonEffectObject.get("mrkAmt").getAsInt();

        boolean ignoreWalls = jsonEffectObject.get("ignoreWalls").getAsBoolean();

        int minDistance = jsonEffectObject.get("minDistance").getAsInt();

        int maxDistance = jsonEffectObject.get("maxDistance").getAsInt();

        return new DirectionalTileDamageEffect(effectID,dmgAmt, mrkAmt, ignoreWalls, minDistance, maxDistance);

    }

    private Effect parseDirectionalDamageEffect(JsonObject jsonEffectObject) {

        int effectID = jsonEffectObject.get("id").getAsInt();

        int dmgAmt = jsonEffectObject.get("dmgAmt").getAsInt();

        int mrkAmt = jsonEffectObject.get("mrkAmt").getAsInt();

        boolean ignoreWalls = jsonEffectObject.get("ignoreWalls").getAsBoolean();

        int minDistance = jsonEffectObject.get("minDistance").getAsInt();

        int maxDistance = jsonEffectObject.get("maxDistance").getAsInt();

        return new DirectionalDamageEffect(effectID,dmgAmt, mrkAmt, ignoreWalls, minDistance, maxDistance);
    }

    private Effect parsePowerGloveEffect(JsonObject jsonEffectObject) {
        //TODO
        return null;
    }

    private Effect parseChainDamageEffect(JsonObject jsonEffectObject) {
        int effectID = jsonEffectObject.get("id").getAsInt();
        return new ChainDamageEffect(effectID);
    }


    private Effect parseEffectSequence(JsonObject jsonEffectObject){

        int effectID = jsonEffectObject.get("id").getAsInt();

        JsonArray jsonSequence = jsonEffectObject.get("effect").getAsJsonArray();

        List<Effect> effectList = new ArrayList<>();

        for(JsonElement jsonNestedElement: jsonSequence){
            effectList.add(parseEffect(jsonNestedElement.getAsString()));
        }

        return new EffectSequence(effectID, effectList);

    }

    private Effect parseEffectChoice(JsonObject jsonEffectObject){

        int effectID = jsonEffectObject.get("id").getAsInt();

        JsonArray jsonChoices = jsonEffectObject.get("effect").getAsJsonArray();

        List<Effect> choices = new ArrayList<>();

        for(JsonElement jsonNestedElement: jsonChoices){
            choices.add(parseEffect(jsonNestedElement.getAsString()));
        }

        return new EffectSequence(effectID, choices);
    }

    public WeaponCard parseWeapon(String jsonWeapon){
        //TODO
        return null;

    }


}
