package main.java.ru.apetrov.FileManager;

import main.java.ru.apetrov.FileManager.Client.Client;

/**
 * Created by Andrey on 03.12.2016.
 */
public class TestClient {

    public static void main(String[] args) {

        Client client = new Client();
        client.setClient();
        client.startClient();
    }
}
