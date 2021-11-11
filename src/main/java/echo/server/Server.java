package echo.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(3000);
        Socket socket = serverSocket.accept();
        while (true) {
            var input = new ObjectInputStream(socket.getInputStream());
            var message = (String) input.readObject();
            if (message.equals("stop")) {
                break;
            }
            String modified = "echo:" + message;
            var output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(modified);
        }
    }
}
