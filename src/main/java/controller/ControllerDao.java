package controller;

import java.util.List;
import model.Fruit;

public interface ControllerDao<T extends Fruit> {
    boolean put(T value);

    T get(int index);

    List<T> getAll();
}
