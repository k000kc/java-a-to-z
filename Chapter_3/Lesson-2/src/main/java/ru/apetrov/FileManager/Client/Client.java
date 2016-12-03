package main.java.ru.apetrov.FileManager.Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Andrey on 03.12.2016.
 * Временный код для проверки работы.
 */
public class Client {

    private int port;
    private String host;

    public Client(int port, String host) {
        this.port = port;
        this.host = host;
    }


    public void connectByServer() {

        try {
        InetAddress inetAddress = InetAddress.getByName(host);
            System.out.println("Connect by " + host);
            Socket socket = new Socket(host, port);

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            DataInputStream in = new DataInputStream(inputStream);
            DataOutputStream out = new DataOutputStream(outputStream);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = null;
            System.out.println("Input messages");
            while (true) {
                s = reader.readLine();
                out.writeUTF(s);
                out.flush();
                s = in.readUTF();
                System.out.println("Server answer: " + s);
                System.out.println("Input messages");
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
