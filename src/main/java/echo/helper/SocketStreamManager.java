package echo.helper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

public class SocketStreamManager implements AutoCloseable {

    private final Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public SocketStreamManager(Socket socket) {
        this.socket = socket;
    }

    public String readMessage() throws IOException, ClassNotFoundException {
        if (Objects.isNull(input)) {
            input = new ObjectInputStream(socket.getInputStream());
        }
        return (String) input.readObject();
    }

    public void sendMessage(String message) throws IOException {
        if (Objects.isNull(output)) {
            output = new ObjectOutputStream(socket.getOutputStream());
        }
        output.writeObject(message);
    }

    @Override
    public void close() throws IOException {
        if (Objects.nonNull(input)) {
            input.close();
        }
        if (Objects.nonNull(output)) {
            output.close();
        }
    }
}
