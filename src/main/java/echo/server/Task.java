package echo.server;

import echo.helper.SocketStreamManager;

import java.io.IOException;
import java.net.Socket;

import static echo.server.Server.SHUTDOWN_KEYWORD;

public record Task(Socket socket, int clientNumber) implements Runnable {

    @Override
    public void run() {
        try (SocketStreamManager socketStreamManager = new SocketStreamManager(socket)) {
            while (true) {
                String message = socketStreamManager.readMessage();

                if (message.equals(SHUTDOWN_KEYWORD)) {
                    break;
                }

                String modifiedMessage = "echo:" + message;
                socketStreamManager.sendMessage(modifiedMessage);
            }
            System.out.println("Finished processing client #" + clientNumber);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
