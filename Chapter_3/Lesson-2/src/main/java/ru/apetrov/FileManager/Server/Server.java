package main.java.ru.apetrov.FileManager.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * Created by Andrey on 03.12.2016.
 * Класс сервер.
 */
public class Server {

    private Integer port = null;
    private String rootDir;

    public String getRootDir() {
        return rootDir;
    }

    public void setServer() {
        try(FileInputStream fis = new FileInputStream("Chapter_3\\Lesson-2\\src\\main\\resources\\config.properties")) {
            Properties priority = new Properties();
            priority.load(fis);

            this.port = Integer.valueOf(priority.getProperty("port"));
            this.rootDir = priority.getProperty("rootDir");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        MenuServer menu = new MenuServer(new File(rootDir));

        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Waiting for connection...");
            Socket socket = serverSocket.accept();
            System.out.println("Connection accepted.\r\n");

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            DataInputStream in = new DataInputStream(inputStream);
            DataOutputStream out = new DataOutputStream(outputStream);

            String s = null;
            while (true) {
                s = in.readUTF();
                System.out.println("Message delivered " + s);
                System.out.println("answer");
                menu.selectActions(s);
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
