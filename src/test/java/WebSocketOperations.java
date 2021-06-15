import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class WebSocketOperations {

    private WebSocketClient webSocket;

    public void connectWebSocket() {

        System.out.println("Enter ws uri please: ");
        String uri = new Scanner(System.in).next();

        try {
            webSocket = new WebSocketClient(new URI(uri)) {

                @Override
                public void onMessage(String message) {
                    System.out.println("got: " + message + "\n");
                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("You are connected to ChatServer: " + getURI() + "\n");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("You have been disconnected from: " + getURI() + "; Code: " + code + " " + reason
                            + "\n");
                }

                @Override
                public void onError(Exception ex) {
                    System.out.println("Exception occurred ...\n" + ex + "\n");
                }
            };
            webSocket.connect();
        } catch ( URISyntaxException ex) {
            System.out.println(uri + " is not a valid WebSocket URI\n");
        }
    }

    public void sendMessage(String message) {
        webSocket.send(message);
    }

}
