package ru.apetrov.FileManager;

import ru.apetrov.FileManager.Server.Server;

/**
 * Created by Andrey on 03.12.2016.
 */
public class TestServer {

    /**
     * Запуск сервера.
     * @param args args
     */
    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }
}
