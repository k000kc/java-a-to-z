package main.java.ru.apetrov.FileManager.Server;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Andrey on 04.12.2016.
 */
public class MenuServer {

    /**
     * Колличество АПИ.
     */
    private final int api = 4;

    /**
     * Массив с возможными апи.
     */
    private BaseAction[] actions = new BaseAction[api];

    /**
     * Текущая директория.
     */
    private File dir;

    /**
     * Исходящий поток.
     */
    private OutputStream outputStream;

    /**
     * Входящий поток.
     */
    private InputStream inputStream;

    /**
     * Доступ к Входящему потоку.
     * @return inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Доступ к исходещему потоку.
     * @return outputStream
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Конструктор.
     * @param dir Текущая директория.
     * @param outputStream outputStream
     * @param inputStream inputStream
     */
    public MenuServer(File dir, OutputStream outputStream, InputStream inputStream) {
        this.dir = dir;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    /**
     * Доступ к текущей директории.
     * @return dir
     */
    public File getDir() {
        return dir;
    }

    /**
     * Сменить директорию.
     * @param dir dir
     */
    public void setDir(File dir) {
        this.dir = dir;
    }

    /**
     * Инициализация массива апи.
     */
    public void fillActions() {
        this.actions[0] = new ShowDirectory("ls", "Введите \"ls\", чтобы просмотреть директорию");
        this.actions[1] = new MoveDirectory("cd", "Введите \"cd <имя каталога>\", чтобы перейти в каталог\r\nВведите \"cd ..\", чтобы вернуться в корневой каталог");
        this.actions[2] = new DownloadFile("dload", "Введите \"dload\", чтобы скачать файл");
        this.actions[3] = new UplaodFile("uload", "Введите \"uload\", чтобы загрузить файл");
    }

    /**
     * Покажем список апи (Справка).
     * @throws IOException IOException
     */
    public void showActions() throws IOException {
        StringBuilder builder = new StringBuilder();
        DataOutputStream writer = new DataOutputStream(this.outputStream);
        for (BaseAction action : this.actions) {
            if (action != null) {
                builder.append(String.format("%s\r\n", action.getName()));
            }
        }
        builder.append("Введите \"exit\", для выхода");
        writer.writeUTF(builder.toString());
        writer.flush();
    }

    /**
     * Выбор апи.
     * @param key ключ апи
     * @throws IOException IOException
     */
    public void selectActions(String key) throws IOException {
        fillActions();
        String[] keys = key.split(" ");
        if (keys[0].equalsIgnoreCase("help")) {
            showActions();
        }
        for (BaseAction action : actions) {
            if (keys[0].equalsIgnoreCase(action.getKey())) {
                action.execute(key);
            }
        }
    }

    /**
     * Класс для просмотре файлов и каталогов в директории.
     */
    public class ShowDirectory extends BaseAction {

        public ShowDirectory(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(String command) throws IOException {
            StringBuilder builder = new StringBuilder();
            DataOutputStream out = new DataOutputStream(getOutputStream());
            for (File file : getDir().listFiles()) {
                if (file.isDirectory()) {
                    builder.append(String.format("%s\t\t%s\r\n", "dir", file.getName()));
                } else {
                    builder.append(String.format("\t\t%s\r\n", file.getName()));
                }
            }
            out.writeUTF(builder.toString());
            out.flush();
        }
    }

    /**
     * Класс для хождения по каталогам.
     * Created by Andrey on 03.12.2016.
     */
    public class MoveDirectory extends BaseAction {

        public MoveDirectory(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(String command) throws IOException {
            String result = "Нет такого каталога";
            String[] commands = command.split(" ");
            DataOutputStream out = new DataOutputStream(getOutputStream());

            if (commands[1].equals("..")) {
                if (!getDir().isAbsolute()) {
                    setDir(new File(String.format("%s", getDir().getParent())));
                }
                result = String.format("%s%s", getDir().getName(), "\\");
            }
            for (File file : getDir().listFiles()) {
                if (file.isDirectory()) {
                    if (commands[1].equals(file.getName())) {
                        setDir(new File(String.format("%s\\%s", getDir(), commands[1])));
                        result = String.format("%s%s", getDir().getName(), "\\");
                    }
                }
            }
            out.writeUTF(result);
            out.flush();
        }
    }

    /**
     * Класс для скачивания файла.
     * Created by Andrey on 03.12.2016.
     */
    public class DownloadFile extends BaseAction {

        public DownloadFile(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(String command) throws IOException {
            String[] commands = command.split(" ");
            for (File file : getDir().listFiles()) {
                if (file.isFile()) {
                    if (commands[1].equals(file.getName())) {
                        DataOutputStream out = new DataOutputStream(getOutputStream());
                        out.writeUTF(String.format("%s %s %s kB", commands[0], commands[1], file.length()));
                        out.flush();
                        FileInputStream inStream = new FileInputStream(file);
                        int count;
                        while ((count = inStream.read()) != -1) {
                            out.write(count);
                            out.flush();
                        }
                    }
                }
            }
        }
    }

    /**
     * Класс для загрузки файла.
     * Created by Andrey on 03.12.2016.
     */
    public class UplaodFile extends BaseAction {

        public UplaodFile(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(String command) throws IOException {
            String[] commands = command.split(" ");
            String inFile = String.format("%s%s", getDir(), commands[1]);
            File file = new File(inFile);
            DataInputStream in = new DataInputStream(getInputStream());
            DataOutputStream out = new DataOutputStream(getOutputStream());
            FileOutputStream outStream = new FileOutputStream(file);
            int count;
            long fileSize = Long.valueOf(commands[2]);
            while (fileSize > 0) {
                count = in.read();
                outStream.write(count);
                outStream.flush();
                fileSize--;
            }
            out.writeUTF(String.format("upload %s %s kB", commands[1], file.length()));
            out.flush();
        }
    }
}
