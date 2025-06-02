/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;



import domain.PurchaseRequisition;
import domain.PurchaseRequisitionItem;
import domain.Supplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author See Kai Yang
 */
public class PurchaseRequisitionItemRepo extends MasterRepo<PurchaseRequisitionItem>{
    public PurchaseRequisitionItemRepo() {
        super(Path.of("database/purchaseRequisitionItem.txt"));
    }

    // Read by ID
    public PurchaseRequisitionItem getPurchaseRequisitionItemById(long itemId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            PurchaseRequisitionItem item = stringToObject(line);
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }
    
    public List<PurchaseRequisitionItem> getItemsByRequisitionId(long requisitionId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<PurchaseRequisitionItem> items = new ArrayList<>();

        for (String line : lines) {
            PurchaseRequisitionItem item = stringToObject(line);
            if (item.getPurchaseRequisitionId() == requisitionId) {
                items.add(item);  // Add the item if it belongs to the correct PurchaseRequisition
            }
        }
        return items;  // Return the list of items for this PurchaseRequisition
    }
    
    public void delete(PurchaseRequisitionItem prItem) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        // Filter out the line corresponding to the PurchaseRequisitionItem you want to delete
        for (String line : lines) {
            PurchaseRequisitionItem existingPrItem = stringToObject(line);
            if (!existingPrItem.getId().equals(prItem.getId())) {
                updatedLines.add(line);
            }
        }

        // Write the updated lines back to the file
        Files.write(filePath, updatedLines);
    }
    
    public void save(PurchaseRequisitionItem prItem) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        lines.add(objectToString(prItem)); // Convert the object to string and add to the list
        Files.write(filePath, lines);  // Save the lines back to the file
    }
    
    
    public void update(PurchaseRequisitionItem prItem) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split("\\|");
            if (Long.parseLong(parts[0]) == prItem.getId()) {
                // Update the specific line for this PurchaseRequisitionItem
                lines.set(i, objectToString(prItem));  // Replace the old entry with the updated one
                break;
            }
        }
        // Write the updated lines back to the file
        Files.write(filePath, lines);
    }
    

    // Convert object to string for file storage
    @Override
    protected String objectToString(PurchaseRequisitionItem item) {
        return String.join("|",
                String.valueOf(item.getId()),
                String.valueOf(item.getPurchaseRequisitionId()),
                String.valueOf(item.getItemId()),
                String.valueOf(item.getQuantity()),
                String.valueOf(item.getSupplierId())
        );
    }

    // Convert string to object
    @Override
    protected PurchaseRequisitionItem stringToObject(String line) {
        String[] parts = line.split("\\|"); // Split by pipe delimiter
        PurchaseRequisitionItem item = new PurchaseRequisitionItem();

        item.setId(Long.valueOf(parts[0]));
        item.setPurchaseRequisitionId(Long.valueOf(parts[1]));
        item.setItemId(Long.valueOf(parts[2]));
        item.setQuantity(Integer.parseInt(parts[3]));
        item.setSupplierId(Long.valueOf(parts[4]));

        return item;
    }
    
    
    
}
