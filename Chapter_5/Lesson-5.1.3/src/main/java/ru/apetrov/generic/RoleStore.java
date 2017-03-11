package ru.apetrov.generic;

/**
 * Created by Andrey on 08.03.2017.
 */
public class RoleStore implements Store<Role> {

    private final SimpleArray<Role> array;
    private int size;

    public RoleStore(int size) {
        this.size = size;
        this.array = new SimpleArray(size);
    }

    @Override
    public void add(Role value) {
        this.array.add(value);
    }

    @Override
    public void update(String id, Role newValue) {
        this.array.update(this.indexOfId(id), newValue);
    }

    @Override
    public void delete(String id) {
        this.array.delete(this.indexOfId(id));
    }

    @Override
    public Role get(String id) {
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

    public SimpleArray<Role> getArray() {
        return array;
    }
}
