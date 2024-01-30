package org.example.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public boolean flag = true;
    public static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket) throws IOException {
        this.socket = socket;
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        name = bufferedReader.readLine();
        clients.add(this);
        System.out.println(name + " подключился к чату.");
        broadcastMessage("Server: " + name + " подключился к чату.");
    }

    private void removeClient() {
        clients.remove(this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }

    @Override
    public void run() {
        String messageFromClient;
        try {
            while (socket.isConnected()) {
                messageFromClient = bufferedReader.readLine();
                if (messageFromClient == null) {
                    closeEverything();
                    break;
                }
                if (isPrivateMessage(messageFromClient)) {
                   privateMessage(messageFromClient);
                } else {
                    broadcastMessage(messageFromClient);
                }
            }
        } catch (IOException e) {
            closeEverything();
        }
    }

    private void broadcastMessage(String message) {
        for (ClientManager client : clients) {
            if (!client.name.equals(name)) {
                try {
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                } catch (IOException e) {
                    closeEverything();
                }
            }
        }
    }


    private boolean isPrivateMessage(String message) {
        return message.contains("@");
    }


    private void privateMessage(String message) {
        String[] parts1 = message.split("@", 2);
        String[] parts2 = parts1[1].split(" ");
        if (parts2.length > 1) {
            String recipient = parts2[0];
            StringBuilder messageBuffer = new StringBuilder();
            for (int i = 1; i < parts2.length; i++) {
                messageBuffer.append(parts2[i]).append(" ");
            }
            String content = String.valueOf(messageBuffer);
            for (ClientManager client : clients) {
                if (client.name.equals(recipient)) {
                    try {
                        client.bufferedWriter.write(name + " (личное): " + content);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    } catch (IOException e) {
                        closeEverything();
                    }
                    return;
                }

            }
            broadcastMessage(message);
        }
    }

    private void closeEverything() {
        for (ClientManager client : clients) {
            if (client.name.equals(name)) {
                client.removeClient();
            }
        }
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
