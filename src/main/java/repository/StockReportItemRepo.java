/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;



import domain.StockReportItem;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author See Kai Yang
 */
public class StockReportItemRepo extends MasterRepo<StockReportItem>{
    public StockReportItemRepo() {
        super(Path.of("database/financialReportItem.txt"));
    }

    // Read by ID
    public StockReportItem getStockReportItemById(long itemId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            StockReportItem item = stringToObject(line);
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }
    
    // Read by Financial Report ID
     public List<StockReportItem> getByStockReportId(Long financialReportId) throws IOException {
        List<StockReportItem> itemList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            StockReportItem item = stringToObject(line);
            if (item.getStockReportId() == financialReportId) {
                itemList.add(item);
            }
        }
        return itemList;
    }

    // Convert object to string for file storage
    @Override
    protected String objectToString(StockReportItem item) {
        return String.join(",",
                String.valueOf(item.getId()),
                String.valueOf(item.getStockReportId()),
                String.valueOf(item.getStockUpdateId())
        );
    }

    // Convert string to object
    @Override
    protected StockReportItem stringToObject(String line) {
        String[] parts = line.split("\\|", 3);
        
        return new StockReportItem(
            Long.valueOf(parts[0]),
            Long.valueOf(parts[1]),
            Long.valueOf(parts[2])
        );
    }
    
    
    
    
}
