package main.java.ru.apetrov.FileManager;

import main.java.ru.apetrov.FileManager.Server.Server;

/**
 * Created by Andrey on 03.12.2016.
 */
public class TestServer {

    public static void main(String[] args) {

        Server server = new Server();
        server.setServer();
        server.startServer();
    }
}
