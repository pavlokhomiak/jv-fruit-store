package dao;

import java.util.ArrayList;
import java.util.List;
import model.Fruit;

public class Storage {
    static final List<Fruit> FRUIT_LIST = new ArrayList<>();

    static void sortList() {
        FRUIT_LIST.sort((e1, e2) ->
                e1.getExDate().isBefore(e2.getExDate()) ? -1
                        : e1.getExDate().isAfter(e2.getExDate())
                        ? 1 : 0);
    }
}
