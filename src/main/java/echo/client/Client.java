package echo.client;

import echo.helper.SocketStreamManager;
import echo.server.Server;

import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", Server.ECHO_PORT)) {
            Scanner scanner = new Scanner(System.in);
            SocketStreamManager socketStreamManager = new SocketStreamManager(socket);
            System.out.println("Print 'stop' to quit the app");

            while (true) {
                System.out.print("Enter value: ");
                String entry = scanner.nextLine();
                socketStreamManager.sendMessage(entry);

                if (entry.equals(Server.SHUTDOWN_KEYWORD)) {
                    System.out.println("Shutting down ... ");
                    break;
                }

                String response = socketStreamManager.readMessage();
                System.out.println(response);
            }
        }
    }

}
