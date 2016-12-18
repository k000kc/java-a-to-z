package main.java.ru.apetrov.FileManager.Server;

import main.java.ru.apetrov.FileManager.Settings;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Andrey on 03.12.2016.
 * Класс сервер.
 */
public class Server {

    private int port;
    private String rootDir;

    private void initProperties() {
        Settings settings = new Settings();
        settings.load();
        this.port = Integer.valueOf(settings.getValue("port"));
        this.rootDir = settings.getValue("rootDir");
    }

    public void startServer() {
        initProperties();
        try (ServerSocket server = new ServerSocket(this.port);) {
            System.out.println("Waiting for connection...");
            Socket socket = server.accept();
            System.out.println("Connection accepted.\r\n");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            MenuServer menu = new MenuServer(new File(this.rootDir), out, in);
            String s = in.readUTF();
            while (!s.equalsIgnoreCase("exit")) {
                System.out.println("Message delivered " + s);
                menu.selectActions(s);
                s = in.readUTF();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
