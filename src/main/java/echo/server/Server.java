package echo.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int ECHO_PORT = 3000;
    public static final String SHUTDOWN_KEYWORD = "stop";

    private static int clientCounter = 0;

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(ECHO_PORT)) {
            while(true) {
                Socket socket = serverSocket.accept();
                clientCounter++;
                System.out.println("Starting to process a client #" + clientCounter);
                new Thread(new Task(socket, clientCounter)).start();
            }
        }
    }
}
