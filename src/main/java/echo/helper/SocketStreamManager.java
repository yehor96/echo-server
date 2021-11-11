package echo.helper;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketStreamManager {

    private final Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public SocketStreamManager(Socket socket) {
        this.socket = socket;
    }

    public String readMessage() throws Exception {
        if (input == null) {
            input = new ObjectInputStream(socket.getInputStream());
        }
        return (String) input.readObject();
    }

    public void sendMessage(String message) throws Exception {
        if (output == null) {
            output = new ObjectOutputStream(socket.getOutputStream());
        }
        output.writeObject(message);
    }

}
