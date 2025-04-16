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
public class StockReportItemRepo {
    
    private final Path filePath = Path.of("database/financialReportItem.txt");

    public StockReportItemRepo() {
    }

    // Create
    public void createStockReportItem(StockReportItem item) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        lines.add(objectToString(item));
        Files.write(filePath, lines);
    }

    // Read all
    public List<StockReportItem> getAllStockReportItems() throws IOException {
        List<StockReportItem> itemList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            itemList.add(stringToObject(line));
        }
        return itemList;
    }

    // Read by ID
    public StockReportItem getStockReportItemById(long itemId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            StockReportItem item = stringToObject(line);
            if (item.getStockReportItemId() == itemId) {
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

    // Update
    public void updateStockReportItem(StockReportItem item) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            StockReportItem existingItem = stringToObject(line);
            if (existingItem.getStockReportItemId() == item.getStockReportItemId()) {
                updatedLines.add(objectToString(item));
            } else {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // Delete
    public void deleteStockReportItem(long itemId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            StockReportItem item = stringToObject(line);
            if (item.getStockReportItemId() != itemId) {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // Convert object to string for file storage
    private String objectToString(StockReportItem item) {
        return String.join(",",
                String.valueOf(item.getStockReportItemId()),
                String.valueOf(item.getStockReportId()),
                String.valueOf(item.getStockUpdateId())
        );
    }

    // Convert string to object
    private StockReportItem stringToObject(String line) {
        String[] parts = line.split("\\|", 3);
        
        return new StockReportItem(
            Long.parseLong(parts[0]),
            Long.parseLong(parts[1]),
            Long.parseLong(parts[2])
        );
    }
    
    
    
    
}
