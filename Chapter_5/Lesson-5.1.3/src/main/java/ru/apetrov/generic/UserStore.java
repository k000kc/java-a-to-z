package ru.apetrov.generic;

/**
 * Created by Andrey on 08.03.2017.
 */
public class UserStore implements Store<User> {

    private final SimpleArray array;

    public UserStore(SimpleArray array) {
        this.array = array;
    }

    @Override
    public void add(User value) {
        this.array.add(value);
    }

    @Override
    public void update(String id, User newValue) {
        int index = Integer.parseInt(id);
        this.array.update(index, newValue);
    }

    @Override
    public void delete(String id) {
        int index = Integer.parseInt(id);
        this.array.delete(index);
    }
}
