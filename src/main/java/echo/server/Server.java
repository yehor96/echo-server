package echo.server;

import echo.helper.SocketStreamManager;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int ECHO_PORT = 3000;
    public static final String SHUTDOWN_KEYWORD = "stop";

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(ECHO_PORT)) {
            Socket socket = serverSocket.accept();
            try (SocketStreamManager socketStreamManager = new SocketStreamManager(socket)) {
                while (true) {
                    String message = socketStreamManager.readMessage();

                    if (message.equals(SHUTDOWN_KEYWORD)) {
                        break;
                    }

                    String modifiedMessage = "echo:" + message;
                    socketStreamManager.sendMessage(modifiedMessage);
                }
            }
        }
    }
}
