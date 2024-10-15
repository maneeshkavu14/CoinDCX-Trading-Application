package com.coindcx.ui;

import java.util.Scanner;
import com.coindcx.websocket.WebSocketClient;
import com.coindcx.payload.PayloadPreparer;

public class CLI {
    private WebSocketClient webSocketClient;
    private PayloadPreparer payloadPreparer;

    public CLI(WebSocketClient webSocketClient, PayloadPreparer payloadPreparer) {
        this.webSocketClient = webSocketClient;
        this.payloadPreparer = payloadPreparer;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter trigger price:");
        double triggerPrice = scanner.nextDouble();
        System.out.println("Enter currency pair (e.g., BTC/USDT):");
        String currencyPair = scanner.next();
    
        // Test with a known working WebSocket URL
        try {
            webSocketClient.connect("wss://echo.websocket.org", new String[]{currencyPair});
        } catch (Exception e) {
            e.printStackTrace(); // Print full stack trace for debugging
            return; // Exit if connection fails
        }

        // This loop simulates a continuous check for market data
        while (true) {
            // Here you should retrieve and display the market data
            System.out.println("Market price: " + webSocketClient.getMarketPrice());
            System.out.println("Prepared buy payload: " + payloadPreparer.prepareBuyPayload(webSocketClient.getMarketPrice(), triggerPrice, currencyPair));
            System.out.println("Prepared sell payload: " + payloadPreparer.prepareSellPayload(webSocketClient.getMarketPrice(), triggerPrice, currencyPair));
            System.out.println("Prepared cancel payload: " + payloadPreparer.prepareCancelPayload("12345"));
            // Add a delay or a way to break the loop as needed
            try {
                Thread.sleep(5000); // Pause for 5 seconds before the next iteration
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
