
import com.opencsv.CSVReader;
import dao.StorageDao;
import dao.StorageDaoImpl;
import model.Fruit;
import model.Orange;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * ізоляція від звязків між классами під час тестування
 * тестовий варіант Storage ??
 */

public class CsvUtilsTest {
    private static CsvUtils csvUtils;
    private static StorageDao<Fruit> storageDao;
    private static final String CSV_INPUT_TEST_FILE = "src\\main\\resources\\testInputFile.csv";
    private static final String CSV_OUTPUT_TEST_FILE = "src\\main\\resources\\testOutputFile.csv";

    @BeforeClass
    public static void initialCsvUtils() {
        csvUtils = new CsvUtils();
        storageDao = new StorageDaoImpl();
    }

    @Before
    public void cleanDao() {
        storageDao.getAll().clear();
    }

    @Test
    public void positiveProcessFile() {
        csvUtils.processFile(CSV_INPUT_TEST_FILE);
        List<Fruit> expectedList = new ArrayList<>();
        expectedList.add(new Orange(LocalDate.parse("2020-10-17")));
        expectedList.add(new Orange(LocalDate.parse("2020-10-17")));
        List<Fruit> actualList = storageDao.getAll();
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void positiveCreateReport() {
        List<String[]> actualList = new ArrayList<>();
        storageDao.put(new Orange(LocalDate.parse("2020-10-17")));
        storageDao.put(new Orange(LocalDate.parse("2020-10-17")));
        csvUtils.createReport(CSV_OUTPUT_TEST_FILE);
        try (CSVReader csvReader = new CSVReader(new FileReader(CSV_OUTPUT_TEST_FILE))) {
            actualList = csvReader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String[]> expectedList = new ArrayList<>();
        expectedList.add(new String[]{"fruit","quantity"});
        expectedList.add(new String[]{"banana","0"});
        expectedList.add(new String[]{"apple","0"});
        expectedList.add(new String[]{"orange","2"});
        for (int i = 0; i < actualList.size(); i++) {
            for (int j = 0; j < actualList.get(i).length; j++) {
                Assert.assertEquals(expectedList.get(i)[j], actualList.get(i)[j]);
            }
        }
    }
}