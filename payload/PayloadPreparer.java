package com.coindcx.payload;

public class PayloadPreparer {
    public String prepareBuyPayload(double marketPrice, double triggerPrice, String currencyPair) {
        String payload = "{\"type\":\"order\",\"symbol\":\"" + currencyPair + "\",\"side\":\"buy\",\"type\":\"limit\",\"quantity\":\"1\",\"price\":\"" + marketPrice + "\"}";
        return payload;
    }

    public String prepareSellPayload(double marketPrice, double triggerPrice, String currencyPair) {
        String payload = "{\"type\":\"order\",\"symbol\":\"" + currencyPair + "\",\"side\":\"sell\",\"type\":\"limit\",\"quantity\":\"1\",\"price\":\"" + marketPrice + "\"}";
        return payload;
    }

    public String prepareCancelPayload(String orderId) {
        String payload = "{\"type\":\"cancel_order\",\"order_id\":\"" + orderId + "\"}";
        return payload;
    }
}
