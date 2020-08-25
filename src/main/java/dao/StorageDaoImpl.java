package dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import model.Fruit;

/**
 * Architecture DAO layer
 */

public class StorageDaoImpl implements StorageDao<Fruit> {
    @Override
    public boolean put(Fruit value) {
        if (value != null) {
            Storage.FRUIT_LIST.add(value);
            Storage.sortList();
        }
        return true;
    }

    @Override
    public Optional<Fruit> get(int index) {
        if (Storage.FRUIT_LIST.size() > index
                && index >= 0
                && !Storage.FRUIT_LIST.isEmpty()) {
            Fruit returnedValue = Storage.FRUIT_LIST.remove(index);
            Storage.sortList();
            return Optional.ofNullable(returnedValue);
        }
        return Optional.empty();
    }

    @Override
    public List<Fruit> getAll() {
        return Storage.FRUIT_LIST.isEmpty()
                ? Collections.emptyList()
                : Storage.FRUIT_LIST;
    }
}
