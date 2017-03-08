package ru.apetrov.generic;

/**
 * Created by Andrey on 08.03.2017.
 */
public class RoleStore implements Store<Role> {

    private final SimpleArray array;

    public RoleStore(SimpleArray array) {
        this.array = array;
    }

    @Override
    public void add(Role value) {
        this.array.add(value);
    }

    @Override
    public void update(String id, Role newValue) {
        int index = Integer.parseInt(id);
        this.array.update(index, newValue);
    }

    @Override
    public void delete(String id) {
        int index = Integer.parseInt(id);
        this.array.delete(index);
    }
}
