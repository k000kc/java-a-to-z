package main.java.ru.apetrov.FileManager.Server.Actions;

import main.java.ru.apetrov.FileManager.Server.BaseAction;

/**
 * Класс для получения списка корневого каталога.
 * Created by Andrey on 03.12.2016.
 */
public class ShowDirectory extends BaseAction {

    public ShowDirectory(String name) {
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
