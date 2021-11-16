package echo.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class SocketStreamManager implements AutoCloseable {

    private static final int DEFAULT_BUFFER_SIZE = 1024;

    private final Socket socket;
    private BufferedReader input;
    private BufferedWriter writer;

    public SocketStreamManager(Socket socket) {
        this.socket = socket;
    }

    public String readMessage() throws IOException {
        if (Objects.isNull(input)) {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        char[] chars = new char[DEFAULT_BUFFER_SIZE];
        int readSize = input.read(chars);
        return new String(chars, 0, readSize);
    }

    public void sendMessage(String message) throws IOException {
        if (Objects.isNull(writer)) {
            writer = new BufferedWriter(new PrintWriter(socket.getOutputStream()));
        }
        writer.write(message);
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        if (Objects.nonNull(input)) {
            input.close();
        }
        if (Objects.nonNull(writer)) {
            writer.close();
        }
    }
}
