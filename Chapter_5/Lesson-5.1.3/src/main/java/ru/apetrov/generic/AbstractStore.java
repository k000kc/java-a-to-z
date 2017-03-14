package ru.apetrov.generic;

/**
 * Created by Andrey on 14.03.2017.
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    private final SimpleArray<T> array;
    private int size;

    protected AbstractStore(int size) {
        this.size = size;
        this.array = new SimpleArray(size);
    }

    @Override
    public void add(T value) {
        this.array.add(value);
    }

    @Override
    public void update(String id, T newValue) {
        this.array.update(this.indexOfId(id), newValue);
    }

    @Override
    public void delete(String id) {
        this.array.delete(this.indexOfId(id));
    }

    @Override
    public T get(String id) {
        return (T) this.array.get(this.indexOfId(id));
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

    public SimpleArray<T> getArray() {
        return array;
    }
}
