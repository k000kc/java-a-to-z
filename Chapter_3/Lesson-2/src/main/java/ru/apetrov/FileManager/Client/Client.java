package main.java.ru.apetrov.FileManager.Client;

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

    private void initProperties() {
        Settings settings = new Settings();
        settings.load();
        this.port = Integer.valueOf(settings.getValue("port"));
        this.host = settings.getValue("host");
        this.clientDir = settings.getValue("clientDir");
        File file = new File(clientDir);
        file.mkdir();
    }

    public void startClient() {
        initProperties();
        System.out.println("Welcome!");
        try(Socket socket = new Socket(this.host, this.port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String s = reader.readLine();
            while (!s.equalsIgnoreCase("exit")) {
                String[] msg = s.split(" ");
                if (msg[0].equals("uload")) {
                    for (File file : new File(clientDir).listFiles()) {
                        if (file.isFile()) {
                            if (msg[1].equals(file.getName())) {
                                out.writeUTF(String.format("%s %s %s", msg[0], msg[1], file.length()));
                                out.flush();
                                upload(socket, file);
                            }
                        }
                    }
                } else {
                    out.writeUTF(s);
                    out.flush();
                }
                s = in.readUTF();
                System.out.println("Server answer:\r\n" + s);
                String[] commands = s.split(" ");
                if (commands[0].equals("dload")) {
                    String filePath = String.format("%s%s", clientDir, commands[1]);
                    download(socket, filePath, Long.valueOf(commands[2]));
                }
                System.out.println("Input messages");
                s = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void download(Socket socket, String filePath, long fileSize) {
        try(FileOutputStream outFile = new FileOutputStream(filePath)) {
            int count;
            while (fileSize > 0) {
                count = socket.getInputStream().read();
                outFile.write(count);
                outFile.flush();
                fileSize--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void upload(Socket socket, File file) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            int count;
            long fileSize = file.length();
            while (fileSize > 0) {
                count = inputStream.read();
                socket.getOutputStream().write(count);
                socket.getOutputStream().flush();
                fileSize--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
