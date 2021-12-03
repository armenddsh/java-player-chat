package com.dulaj.chat.client;

import com.dulaj.chat.server.model.Command;
import com.dulaj.chat.server.model.CommandType;
import com.dulaj.chat.server.model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Client {
    private static final String ipAddress = "127.0.0.1";
    private static final int port = 7777;

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendAndReceive() throws IOException {
        final Scanner sc = new Scanner(System.in);

        String str = null;
        String respMessage = null;

        while ((respMessage = in.readLine()) != null) {
            System.out.println("Server: " + respMessage);
            if (respMessage.equals("...")) {
                str = sc.nextLine();
                System.out.println("Me: " + str);
            }
        }
    }

    private void printMessages(final List<String> messages) {
        if (messages != null) {
            for (String message: messages) {
                System.out.println(message);
            }
        }
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        final String uuid = UUID.randomUUID().toString();
        final Player player = new Player(uuid,  "Mergim Dulaj");

        final Client client = new Client();
        client.startConnection(ipAddress, port);

        client.sendAndReceive();
    }
}
