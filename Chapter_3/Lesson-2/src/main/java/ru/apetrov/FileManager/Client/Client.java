package main.java.ru.apetrov.FileManager.Client;

import com.sun.org.apache.xpath.internal.SourceTree;
import main.java.ru.apetrov.FileManager.Settings;

import java.io.*;
import java.net.Socket;

/**
 * Created by Andrey on 03.12.2016.
 * Временный код для проверки работы.
 */
public class Client {

    private int port;
    private String host;
    private String clientDir;

    public String getClientDir() {
        return clientDir;
    }

    private void initProperties() {
        Settings settings = new Settings();
        settings.load();
        this.port = Integer.valueOf(settings.getValue("port"));
        this.host = settings.getValue("host");
        this.clientDir = settings.getValue("clientDir");
    }

    public void startClient() {
        initProperties();
        System.out.println("Welcome!");
        try(Socket socket = new Socket(this.host, this.port)) {

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String s = reader.readLine();
            while (!s.equalsIgnoreCase("exit")) {
                out.writeUTF(s);
                out.flush();
                s = in.readUTF();
                System.out.println("Server answer:\r\n" + s);
                System.out.println("Input messages");
                s = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
