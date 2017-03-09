package ru.apetrov.generic;

/**
 * Created by Andrey on 08.03.2017.
 */
public class UserStore implements Store<Base> {

    private final SimpleArray<Base> array;
    private int size;

    public UserStore(int size) {
        this.size = size;
        this.array = new SimpleArray<>(size);
    }

    @Override
    public void add(Base value) {
        this.array.add(value);
    }

    @Override
    public void update(String id, Base newValue) {
        this.array.update(this.indexOfId(id), newValue);
    }

    @Override
    public void delete(String id) {
        this.array.delete(this.indexOfId(id));
    }

    @Override
    public Base get(String id) {
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

    public SimpleArray<Base> getArray() {
        return array;
    }
}
