package main.java.ru.apetrov.FileManager.Server.Actions;

import main.java.ru.apetrov.FileManager.Server.BaseAction;

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
