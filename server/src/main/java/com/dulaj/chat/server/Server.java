package com.dulaj.chat.server;

import com.dulaj.chat.server.model.Message;
import com.dulaj.chat.server.model.Room;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server {
    private static final String ipAddress = "127.0.0.1";
    private static final int port = 7777;

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private OutputStream out;
    private BufferedReader in;
    private ObjectOutputStream objectOutputStream;
    private static final List<Room> rooms = new ArrayList<>();

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = clientSocket.getOutputStream();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        objectOutputStream = new ObjectOutputStream(out);

        showWelcome();

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            objectOutputStream.writeObject(inputLine);
        }
    }

    private void showWelcome() throws IOException {
        Message message = new Message();

        objectOutputStream.writeObject(message);

        for (Room room: rooms) {

        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        generateRandomRooms();

        Server server = new Server();
        server.start(port);
    }

    private static void generateRandomRooms() {
        for (int i = 0; i < 3; i++) {
            final String roomId = UUID.randomUUID().toString();
            final Room room = new Room(roomId, String.valueOf(i));
            rooms.add(room);
        }
    }
}
