package echo.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketStreamManager implements AutoCloseable {

    private final BufferedReader reader;
    private final BufferedWriter writer;

    public SocketStreamManager(Socket socket) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new PrintWriter(socket.getOutputStream()));
    }

    public String readMessage() throws IOException {
        return reader.readLine();
    }

    public void sendMessage(String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        try (reader; writer) {

        }
    }
}
