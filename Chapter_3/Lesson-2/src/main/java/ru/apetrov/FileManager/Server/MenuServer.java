package main.java.ru.apetrov.FileManager.Server;

import java.io.File;

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
        this.actions[2] = new DownloadFile("download", "Введите \"download\", чтобы скачать файл");
        this.actions[3] = new UplaodFile("upload", "Введите \"upload\", чтобы загрузить файл");
    }

    public String showActions() {
        StringBuilder builder = new StringBuilder();
        String result;
        for (BaseAction action : this.actions) {
            if (action != null) {
                builder.append(String.format("%s\r\n", action.getName()));
            }
        }
        builder.append("Введите \"exit\", для выхода");
        result = builder.toString();
        return result;
    }

    public String selectActions(String key) {
        fillActions();
        String[] keys = key.split(" ");
        String result = "Неизвестная команда!";
        if (keys[0].equalsIgnoreCase("help")) {
            result = showActions();
        }
        for (BaseAction action : actions) {
            if (keys[0].equalsIgnoreCase(action.getKey())) {
                result = action.execute(key);
            }
        }
        return result;
    }

    /**
     * Класс для просмотре файлов и каталогов в директории.
     */
    public class ShowDirectory extends BaseAction {

        public ShowDirectory(String key, String name) {
            super(key, name);
        }

        @Override
        public String execute(String command) {
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
            return result;
        }
    }

    /**
     * Класс для входа в подкаталог.
     * Created by Andrey on 03.12.2016.
     */
    public class MoveDirectory extends BaseAction {

        public MoveDirectory(String key, String name) {
            super(key, name);
        }

        @Override
        public String execute(String command) {
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
            return result;
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
        public String execute(String command) {
            String result = "Download";
            return result;
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
        public String execute(String command) {
            String result = "UplaodFile";
            return result;
        }
    }
}
