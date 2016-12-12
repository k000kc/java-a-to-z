package main.java.ru.apetrov.FileManager.Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * Created by Andrey on 03.12.2016.
 * Временный код для проверки работы.
 */
public class Client {

    private int port;
    private String host;

    public void setClient() {
        try(FileInputStream fis = new FileInputStream("Chapter_3\\Lesson-2\\src\\main\\resources\\config.properties")) {
            Properties properties = new Properties();
            properties.load(fis);
            this.port = Integer.valueOf(properties.getProperty("port"));
            this.host = properties.getProperty("host");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startClient() {

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
            System.out.println("Введите \"help\", чтобы вызвать справку");
            while (true) {
                s = reader.readLine();
                if (s.equalsIgnoreCase("exit")) {
                    break;
                }
                out.writeUTF(s);
                out.flush();
                s = in.readUTF();
                System.out.println("Server answer:\r\n" + s);
                System.out.println("Input messages");
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
