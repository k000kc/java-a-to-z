package main.java.ru.apetrov.FileManager.Server.Actions;

import main.java.ru.apetrov.FileManager.Server.BaseAction;

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
