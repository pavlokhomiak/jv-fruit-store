package service;

import controller.ControllerDao;
import controller.ControllerDaoImpl;
import java.time.LocalDate;
import java.util.List;

import model.Apple;
import model.Banana;
import model.Fruit;
import model.Orange;

/**
 * Open-Closed
 * returnFruit()
 */

public class StorageServiceImpl implements StorageService {
    private ControllerDao<Fruit> controllerDao = new ControllerDaoImpl();

    public boolean supplyFruit(String fruitType,
                               int count, LocalDate expirationDate) {
        for (int i = 0; i < count; i++) {
            if (fruitType.equalsIgnoreCase(Banana.class.getSimpleName())) {
                if (!controllerDao.put(new Banana(expirationDate))) {
                    return false;
                }
            } else if (fruitType.equalsIgnoreCase(Orange.class.getSimpleName())) {
                if (!controllerDao.put(new Orange(expirationDate))) {
                    return false;
                }
            } else if (fruitType.equalsIgnoreCase(Apple.class.getSimpleName())) {
                if (!controllerDao.put(new Apple(expirationDate))) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean buyFruit(String fruitType,
                            int count, LocalDate purchaseDate) {
        List<Fruit> fruitList = controllerDao.getAll();
        for (int i = 0; i < fruitList.size(); i++) {
            if (fruitList.get(i).getClass().getSimpleName().equalsIgnoreCase(fruitType)) {
                if (fruitList.get(i).getExDate().isBefore(purchaseDate)) {
                    return false;
                }
                controllerDao.get(i);
                count--;
            }
            if (count == 0) {
                break;
            }
        }
        return true;
    }

    public boolean returnFruit(String fruitType,
                               int count, LocalDate expirationDate) {
        return supplyFruit(fruitType, count, expirationDate);
    }
}
