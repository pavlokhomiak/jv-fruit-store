package controller;

import dao.StorageDao;
import dao.StorageDaoImpl;
import java.util.List;
import model.Fruit;

/**
 * Architecture Controller layer
 */

public class ControllerDaoImpl implements ControllerDao<Fruit> {
    private StorageDao<Fruit> storageDao = new StorageDaoImpl();

    @Override
    public boolean put(Fruit value) {
        return storageDao.put(value);
    }

    @Override
    public Fruit get(int index) {
        return storageDao.get(index).orElseThrow();
    }

    @Override
    public List<Fruit> getAll() {
        return storageDao.getAll();
    }
}
