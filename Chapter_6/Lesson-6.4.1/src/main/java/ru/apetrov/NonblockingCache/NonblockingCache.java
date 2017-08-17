package ru.apetrov.NonblockingCache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;

/**
 * Created by Andrey on 17.08.2017.
 */
public class NonblockingCache {

    /**
     * cache.
     */
    private final ConcurrentMap<Integer, Model> cache;

    /**
     * Constructor.
     */
    public NonblockingCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    /**
     * add Model.
     * @param model model.
     */
    public void add(Model model) {
        this.cache.put(model.getId(), model);
    }

    /**
     * delete model.
     * @param model model.
     */
    public void delete(Model model) {
        this.cache.remove(model.getId());
    }

    /**
     * update model.
     * @param newModel new Model.
     */
    public void update(Model newModel) {
        this.cache.computeIfPresent(newModel.getId(), new BiFunction<Integer, Model, Model>() {
            @Override
            public Model apply(Integer id, Model oldModel) {
                if (isValidate(newModel, oldModel)) {
                    newModel.incrementVersion();
                    return newModel;
                } else {
                    throw new OptimisticExeption("Data is being used by another thread");
                }
            }
        });
    }

    /**
     * Get Model by id.
     * @param id id.
     * @return model.
     */
    public Model get(int id) {
        return this.cache.get(id);
    }

    /**
     * size of cache.
     * @return size.
     */
    public int size() {
        return this.cache.size();
    }

    /**
     * check validate version.
     * @param newModel new model.
     * @param oldModel old model.
     * @return result.
     */
    private boolean isValidate(Model newModel, Model oldModel) {
        boolean result = false;
        if (newModel.getVersion() == oldModel.getVersion()) {
            result = true;
        }
        return result;
    }
}
