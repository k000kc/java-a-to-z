package main.java.ru.apetrov.FileManager.Server.Actions;

import main.java.ru.apetrov.FileManager.Server.BaseAction;

/**
 * Класс для входа в подкаталог.
 * Created by Andrey on 03.12.2016.
 */
public class MoveToCatalog extends BaseAction {

    public MoveToCatalog(String name) {
        super(name);
    }

    @Override
    public int key() {
        return 0;
    }

    @Override
    public void execute() {

    }
}
