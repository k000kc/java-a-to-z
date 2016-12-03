package main.java.ru.apetrov.FileManager.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Andrey on 03.12.2016.
 * Класс сервер, этот код временный просто для проверки.
 */
public class Server {

    private int port;
    private String rootDir;

    public Server(int port, String rootDir) {
        this.port = port;
        this.rootDir = rootDir;
    }

    public void connectByClient() {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Waiting for connection...");
            Socket socket = serverSocket.accept();
            System.out.println("Connection accepted.");

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            DataInputStream in = new DataInputStream(inputStream);
            DataOutputStream out = new DataOutputStream(outputStream);

            String s = null;
            while (true) {
                s = in.readUTF();
                System.out.println("Message delivered " + s);
                System.out.println("answer");
                out.writeUTF(s);
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
