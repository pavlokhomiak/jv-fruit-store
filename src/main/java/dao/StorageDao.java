package dao;

import java.util.List;
import java.util.Optional;

public interface StorageDao<T> {
    boolean put(T value);

    Optional<T> get(int index);

    List<T> getAll();
}
