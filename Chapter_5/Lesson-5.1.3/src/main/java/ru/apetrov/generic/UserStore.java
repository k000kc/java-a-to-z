package ru.apetrov.generic;

import ru.apetrov.generic.Store;
import ru.apetrov.generic.User;

/**
 * Created by Andrey on 08.03.2017.
 */
public class UserStore implements Store<User> {

    private final SimpleArray<User> array;
    private int size;

    public UserStore(int size) {
        this.size = size;
        this.array = new SimpleArray<>(size);
    }

    @Override
    public void add(User value) {
        this.array.add(value);
    }

    @Override
    public void update(String id, User newValue) {
        this.array.update(this.indexOfId(id), newValue);
    }

    @Override
    public void delete(String id) {
        this.array.delete(this.indexOfId(id));
    }

    @Override
    public User get(String id) {
        return this.array.get(this.indexOfId(id));
    }

    public int indexOfId(String id) {
        Integer result = null;
        for (int i = 0; i < this.size; i++) {
            if (this.array.get(i) != null) {
                if (this.array.get(i).getId().equals(id)) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    public SimpleArray<User> getArray() {
        return array;
    }
}
