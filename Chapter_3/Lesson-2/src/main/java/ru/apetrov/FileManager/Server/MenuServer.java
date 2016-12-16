package main.java.ru.apetrov.FileManager.Server;

import java.io.*;

/**
 * Created by Andrey on 04.12.2016.
 */
public class MenuServer {

    private BaseAction actions[] = new BaseAction[4];
    private File dir;

    public MenuServer(File dir) {
        this.dir = dir;
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
        this.actions[2] = new DownloadFile("dload", "Введите \"download\", чтобы скачать файл");
        this.actions[3] = new UplaodFile("uload", "Введите \"upload\", чтобы загрузить файл");
    }

    public void showActions() {
        StringBuilder builder = new StringBuilder();
        PrintWriter msg;
        for (BaseAction action : this.actions) {
            if (action != null) {
                builder.append(String.format("%s\r\n", action.getName()));
            }
        }
        builder.append("Введите \"exit\", для выхода");
        builder.toString();
    }

    public void selectActions(String key) {
        fillActions();
        String[] keys = key.split(" ");
        String result = "Неизвестная команда!";
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
        public void execute(String command) {
            String result = "";
            StringBuilder builder = new StringBuilder();
            for (File file : getDir().listFiles()) {
                if (file.isDirectory()) {
                    builder.append(String.format("%s\t\t%s\r\n", "dir", file.getName()));
                } else {
                    builder.append(String.format("\t\t%s\r\n", file.getName()));
                }
            }
            result = builder.toString();
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
        public void execute(String command) {
            String[] commands = command.split(" ");
            String result = "Нет такого каталога!";

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
        public void execute(String command) {
            System.out.println("Download");

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
        public void execute(String command) {
            System.out.println("UplaodFile");
        }
    }
}
