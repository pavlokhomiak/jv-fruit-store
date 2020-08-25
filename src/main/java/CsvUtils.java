import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import controller.ControllerDao;
import controller.ControllerDaoImpl;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Apple;
import model.Banana;
import model.Fruit;
import model.Orange;
import service.StorageService;
import service.StorageServiceImpl;

/**
 * Open-Closed
 * Strategy pattern
 */

public class CsvUtils {
    private StorageService storageService = new StorageServiceImpl();

    public boolean processFile(String csvInputFile) {
        try (CSVReader reader = new CSVReader(new FileReader(csvInputFile))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                switch (line[0].trim()) {
                    case "type":
                        continue;
                    case "s":
                        storageService.supplyFruit(line[1], Integer.parseInt(line[2]),
                                LocalDate.parse(line[3]));
                        break;
                    case "b":
                        storageService.buyFruit(line[1], Integer.parseInt(line[2]),
                                LocalDate.parse(line[3]));
                        break;
                    case "r":
                        storageService.returnFruit(line[1], Integer.parseInt(line[2]),
                                LocalDate.parse(line[3]));
                        break;
                    default:
                        return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean createReport(String csvOutputFile) {
        ControllerDao<Fruit> controllerDao = new ControllerDaoImpl();
        int bananaCount = 0;
        int appleCount = 0;
        int orangeCount = 0;
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvOutputFile))) {
            List<String[]> outputLines = new ArrayList<>();
            for (Fruit fruit : controllerDao.getAll()) {
                if (fruit.getClass().equals(Banana.class)) {
                    bananaCount++;
                } else if (fruit.getClass().equals(Apple.class)) {
                    appleCount++;
                } else if (fruit.getClass().equals(Orange.class)) {
                    orangeCount++;
                }
            }
            outputLines.add(new String[]{"fruit", "quantity"});
            outputLines.add(new String[]{"banana", String.valueOf(bananaCount)});
            outputLines.add(new String[]{"apple", String.valueOf(appleCount)});
            outputLines.add(new String[]{"orange", String.valueOf(orangeCount)});
            writer.writeAll(outputLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
