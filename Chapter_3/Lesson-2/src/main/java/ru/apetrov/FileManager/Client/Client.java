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
                    upload(out, msg[1]);
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

    public void upload(DataOutputStream outStream, String nameFile) throws IOException {
        for (File file : new File(clientDir).listFiles()) {
            if (file.isFile()) {
                if (nameFile.equals(file.getName())) {
                    outStream.writeUTF(String.format("uload %s %s", nameFile, file.length()));
                    outStream.flush();
                    FileInputStream inputStream = new FileInputStream(file);
                    int count;
                    while ((count = inputStream.read()) != -1) {
                        outStream.write(count);
                        outStream.flush();
                    }
                    break;
                }
            }
        }
    }
}
