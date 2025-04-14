/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;



import domain.PurchaseRequisitionItem;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author See Kai Yang
 */
public class PurchaseRequisitionItemRepo {
    
    private final Path filePath = Path.of("database/purchaseRequisitionItem.txt");

    public PurchaseRequisitionItemRepo() {
    }

    // Create
    public void createPurchaseRequisitionItem(PurchaseRequisitionItem item) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        lines.add(objectToString(item));
        Files.write(filePath, lines);
    }

    // Read all
    public List<PurchaseRequisitionItem> getAllPurchaseRequisitionItems() throws IOException {
        List<PurchaseRequisitionItem> itemList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            itemList.add(stringToObject(line));
        }
        return itemList;
    }

    // Read by ID
    public PurchaseRequisitionItem getPurchaseRequisitionItemById(long itemId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            PurchaseRequisitionItem item = stringToObject(line);
            if (item.getPurchaseRequisitionItemId() == itemId) {
                return item;
            }
        }
        return null;
    }

    // Update
    public void updatePurchaseRequisitionItem(PurchaseRequisitionItem item) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            PurchaseRequisitionItem existingItem = stringToObject(line);
            if (existingItem.getPurchaseRequisitionItemId() == item.getPurchaseRequisitionItemId()) {
                updatedLines.add(objectToString(item));
            } else {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // Delete
    public void deletePurchaseRequisitionItem(long itemId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            PurchaseRequisitionItem item = stringToObject(line);
            if (item.getPurchaseRequisitionItemId() != itemId) {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // Convert object to string for file storage
    private String objectToString(PurchaseRequisitionItem item) {
        return String.join(",",
                String.valueOf(item.getPurchaseRequisitionItemId()),
                String.valueOf(item.getPurchaseRequisitionId()),
                String.valueOf(item.getItemId()),
                String.valueOf(item.getQuantity()),
                String.valueOf(item.getSupplierId())
        );
    }

    // Convert string to object
    private PurchaseRequisitionItem stringToObject(String line) {
        String[] parts = line.split(",");
        PurchaseRequisitionItem item = new PurchaseRequisitionItem();
        item.setPurchaseRequisitionItemId(Long.parseLong(parts[0]));
        item.setPurchaseRequisitionId(Long.parseLong(parts[1]));
        item.setItemId(Long.parseLong(parts[2]));
        item.setQuantity(Integer.parseInt(parts[3]));
        item.setSupplierId(Long.parseLong(parts[4]));
        return item;
    }
    
    
    
    
}
