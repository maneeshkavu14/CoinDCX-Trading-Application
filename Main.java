package com.coindcx;

import com.coindcx.payload.PayloadPreparer;
import com.coindcx.ui.CLI;
import com.coindcx.websocket.WebSocketClient;

public class Main {
    public static void main(String[] args) {
        WebSocketClient webSocketClient = new WebSocketClient();
        PayloadPreparer payloadPreparer = new PayloadPreparer();
        CLI cli = new CLI(webSocketClient, payloadPreparer);
        cli.run();
    }
}
