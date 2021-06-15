public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebSocketOperations webSocket = new WebSocketOperations();
        webSocket.connectWebSocket();

        Thread.sleep(3000);

        webSocket.sendMessage("HelloWorld!");
    }
}
