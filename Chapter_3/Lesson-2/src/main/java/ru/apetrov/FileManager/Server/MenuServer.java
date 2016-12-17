package main.java.ru.apetrov.FileManager.Server;

import java.io.*;

/**
 * Created by Andrey on 04.12.2016.
 */
public class MenuServer {

    private BaseAction actions[] = new BaseAction[4];
    private File dir;
    private OutputStream out;

    public OutputStream getOut() {
        return out;
    }

    public MenuServer(File dir, OutputStream out) {
        this.dir = dir;
        this.out = out;
    }

    public File getDir() {
        return dir;
    }

    public void setDir(File dir) {
        this.dir = dir;
    }

    public void fillActions() {
        this.actions[0] = new ShowDirectory("ls", "Введите \"ls\", чтобы просмотреть директорию");
        this.actions[1] = new MoveDirectory("cd", "Введите \"cd <имя каталога>\", чтобы перейти в каталог\r\nВведите \"cd ..\", чтобы вернуться в корневой каталог");
        this.actions[2] = new DownloadFile("dload", "Введите \"dload\", чтобы скачать файл");
        this.actions[3] = new UplaodFile("uload", "Введите \"uload\", чтобы загрузить файл");
    }

    public void showActions() throws IOException {
        StringBuilder builder = new StringBuilder();
        DataOutputStream writer = new DataOutputStream(this.out);
        for (BaseAction action : this.actions) {
            if (action != null) {
                builder.append(String.format("%s\r\n", action.getName()));
            }
        }
        builder.append("Введите \"exit\", для выхода");
        writer.writeUTF(builder.toString());
        writer.flush();
    }

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
            DataOutputStream out = new DataOutputStream(getOut());
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
            DataOutputStream out = new DataOutputStream(getOut());



            if (commands[1].equals("..")) {
                setDir(new File(String.format("%s", getDir().getParent())));
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
            DataOutputStream out = new DataOutputStream(getOut());
            out.writeUTF("Download");
            out.flush();
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
            DataOutputStream out = new DataOutputStream(getOut());
            out.writeUTF("Upload");
            out.flush();
        }
    }
}
