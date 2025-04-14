/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;



import domain.FinancialReportItem;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author See Kai Yang
 */
public class FinancialReportItemRepo {
    
    private final Path filePath = Path.of("database/financialReportItem.txt");

    public FinancialReportItemRepo() {
    }

    // Create
    public void createFinancialReportItem(FinancialReportItem item) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        lines.add(objectToString(item));
        Files.write(filePath, lines);
    }

    // Read all
    public List<FinancialReportItem> getAllFinancialReportItems() throws IOException {
        List<FinancialReportItem> itemList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            itemList.add(stringToObject(line));
        }
        return itemList;
    }

    // Read by ID
    public FinancialReportItem getFinancialReportItemById(long itemId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            FinancialReportItem item = stringToObject(line);
            if (item.getFinancialReportItemId() == itemId) {
                return item;
            }
        }
        return null;
    }
    
    // Read by Financial Report ID
     public List<FinancialReportItem> getByFinancialReportId(Long financialReportId) throws IOException {
        List<FinancialReportItem> itemList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            FinancialReportItem item = stringToObject(line);
            if (item.getFinancialReportId() == financialReportId) {
                itemList.add(item);
            }
        }
        return itemList;
    }

    // Update
    public void updateFinancialReportItem(FinancialReportItem item) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            FinancialReportItem existingItem = stringToObject(line);
            if (existingItem.getFinancialReportItemId() == item.getFinancialReportItemId()) {
                updatedLines.add(objectToString(item));
            } else {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // Delete
    public void deleteFinancialReportItem(long itemId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            FinancialReportItem item = stringToObject(line);
            if (item.getFinancialReportItemId() != itemId) {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // Convert object to string for file storage
    private String objectToString(FinancialReportItem item) {
        return String.join(",",
                String.valueOf(item.getFinancialReportItemId()),
                String.valueOf(item.getFinancialReportId()),
                String.valueOf(item.getPaymentId())
        );
    }

    // Convert string to object
    private FinancialReportItem stringToObject(String line) {
        String[] parts = line.split("\\|", 3);
        
        return new FinancialReportItem(
            Long.parseLong(parts[0]),
            Long.parseLong(parts[1]),
            Long.parseLong(parts[2])
        );
    }
    
    
    
    
}
