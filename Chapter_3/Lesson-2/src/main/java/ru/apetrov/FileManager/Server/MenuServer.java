package main.java.ru.apetrov.FileManager.Server;

import java.io.File;

/**
 * Created by Andrey on 04.12.2016.
 */
public class MenuServer {

    private BaseAction actions[] = new BaseAction[5];
    private File dir;

    public MenuServer(File dir) {
        this.dir = dir;
    }

    public void fillActions() {
        this.actions[0] = new ShowDirectory("ls", "Введите \"ls\", чтобы просмотреть директорию");
        this.actions[1] = new MoveToDirectory("cd", "Введите \"cd\", чтобы перейти в каталог");
        this.actions[2] = new RootDirectory("cd ..", "Введите \"cd ..\", чтобы вернуться в корневой каталог");
        this.actions[3] = new DownloadFile("download", "Введите \"download\", чтобы скачать файл");
        this.actions[4] = new UplaodFile("upload", "Введите \"upload\", чтобы загрузить файл");
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
        String result = "Неизвестная команда!";
        if (key.equalsIgnoreCase("help")) {
            result = showActions();
        }
        for (BaseAction action : actions) {
            if (key.equalsIgnoreCase(action.getKey())) {
                result = action.execute();
            }
        }
        return result;
    }

    public class ShowDirectory extends BaseAction {

        public ShowDirectory(String key, String name) {
            super(key, name);
        }

        @Override
        public String execute() {
            String result = "";
            StringBuilder builder = new StringBuilder();
            for (File file : dir.listFiles()) {
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
    public class MoveToDirectory extends BaseAction {

        public MoveToDirectory(String key, String name) {
            super(key, name);
        }

        @Override
        public String execute() {
            String result = "MoveToDirectory";
            return result;
        }
    }

    /**
     * Класс для перехода в корневую директорию.
     * Created by Andrey on 03.12.2016.
     */
    public class RootDirectory extends BaseAction {

        public RootDirectory(String key, String name) {
            super(key, name);
        }

        @Override
        public String execute() {
            String result = "RootDirectory";
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
        public String execute() {
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
        public String execute() {
            String result = "UplaodFile";
            return result;
        }
    }
}
