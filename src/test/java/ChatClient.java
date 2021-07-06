import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class ChatClient {

    private static final String locationToConnect = System.getenv("location");
    private static final String remote = System.getenv("remoteDriver");

    private static final String remote1 = System.getenv("51541541lmkfgnlsjrn");

    private static final long serialVersionUID = -6056260699202978657L;

    private WebSocketClient cc;

    private static boolean connectStatus = false;

    private static String message = "{\"uniqueID\":\"EUMM1366149\",\"correlationID\":\"0\",\"workflowRequest\":\"DELETE\"" +
            ",\"product\":\"MONEYMARKET\",\"makerIndex\":\"1\",\"requestParameters\":[{\"makerIndex\":\"2\",\"parameter\":" +
            "\"QUOTE_ID\",\"value\":\"2\"}],\"requestValues\":[{\"makerIndex\":\"1\",\"legIndex\":\"1\",\"field\":\"MARKET\"," +
            "\"subField\":null,\"value\":\"USD\"}]}";

    public void actionPerformed() {

        if (connectStatus) {
            try {
                // cc = new ChatClient(new URI(uriField.getText()), area, ( Draft ) draft.getSelectedItem() );
                cc = new WebSocketClient(new URI(locationToConnect)) {

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
                        System.out.println(
                                "You have been disconnected from: " + getURI() + "; Code: " + code + " " + reason
                                        + "\n"
                        );
                    }

                    @Override
                    public void onError(Exception ex) {
                        System.out.println("Exception occurred ...\n" + ex + "\n");
                    }
                };
                cc.connect();
            } catch (URISyntaxException ex) {
                System.out.println(locationToConnect + " is not a valid WebSocket URI\n");
            }
        } else if (connectStatus) {
            cc.close();
        }

        if (cc != null && message != null) {
            cc.send(message);
        }
    }

    public static void main(String[] args) {
        String location;
        if (args.length != 0) {
            location = args[0];
            System.out.println("Default server url specified: \'" + location + "\'");
        } else {
            location = locationToConnect;
            System.out.println("Default server url not specified: defaulting to \'" + location + "\'");
        }
        connectStatus = true;
        while (true) {
            new ChatClient().actionPerformed();
        }
    }

}