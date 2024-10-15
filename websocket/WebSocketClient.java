package com.coindcx.websocket;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletionStage;

public class WebSocketClient {
    private WebSocket webSocket;
    private double marketPrice;

    public void connect(String url, String[] currencyPairs) {
        HttpClient client = HttpClient.newHttpClient();
        webSocket = client.newWebSocketBuilder()
                .buildAsync(URI.create(url), new WebSocketListener(currencyPairs))
                .join();
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    private class WebSocketListener implements WebSocket.Listener {
        private final String[] currencyPairs;

        public WebSocketListener(String[] currencyPairs) {
            this.currencyPairs = currencyPairs;
        }

        @Override
        public void onOpen(WebSocket webSocket) {
            System.out.println("WebSocket connection established");
            subscribeToMarketData(webSocket, currencyPairs);
        }

        @Override
        public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            System.out.println("Received market data: " + data);
            marketPrice = 48000; // Placeholder for actual market price parsing
            return null; // Returning null here is acceptable for the sake of this implementation
        }

        @Override
        public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
            System.out.println("WebSocket connection closed");
            return null; // Returning null here is acceptable for the sake of this implementation
        }

        // Corrected onError method signature
        @Override
        public void onError(WebSocket webSocket, Throwable throwable) {
            System.out.println("WebSocket error: " + throwable.getMessage());
        }
    }

    private void subscribeToMarketData(WebSocket webSocket, String[] currencyPairs) {
        String subscribeMessage = "{\"type\":\"subscribe\",\"channels\":[{\"name\":\"ticker\",\"symbols\":[";
        for (int i = 0; i < currencyPairs.length; i++) {
            subscribeMessage += "\"" + currencyPairs[i] + "\"";
            if (i < currencyPairs.length - 1) {
                subscribeMessage += ",";
            }
        }
        subscribeMessage += "]}]}";
        webSocket.sendText(subscribeMessage, true);
    }
}
