package echo.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 3000);
        var scanner = new Scanner(System.in);

        while (true) {
            var output = new ObjectOutputStream(socket.getOutputStream());
            String entry = scanner.nextLine();
            output.writeObject(entry);
            if (entry.equals("stop")) {
                break;
            }
            var input = new ObjectInputStream(socket.getInputStream());
            var response = (String) input.readObject();
            System.out.println(response);
        }
    }

}
