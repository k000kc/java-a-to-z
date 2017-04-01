package ru.apetrov.generic;

/**
 * AbstractStore class.
 * @param <T> extends Base model.
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    /**
     * array.
     */
    private final SimpleArray<T> array;

    /**
     * size array.
     */
    private int size;

    /**
     * Constructor of class.
     * @param size size of array.
     */
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

    /**
     * Get index of the model by id.
     * @param id of the model.
     * @return index.
     */
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

    /**
     * getter of array.
     * @return array.
     */
    public SimpleArray<T> getArray() {
        return array;
    }
}
