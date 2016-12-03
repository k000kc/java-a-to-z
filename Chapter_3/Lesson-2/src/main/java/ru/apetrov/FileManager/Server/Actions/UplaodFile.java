package main.java.ru.apetrov.FileManager.Server.Actions;

import main.java.ru.apetrov.FileManager.Server.BaseAction;

/**
 * Класс для загрузки файла.
 * Created by Andrey on 03.12.2016.
 */
public class UplaodFile extends BaseAction {

    public UplaodFile(String name) {
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
