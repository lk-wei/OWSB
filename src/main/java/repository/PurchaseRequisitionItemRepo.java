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
import java.util.Objects;

/**
 *
 * @author See Kai Yang
 */
public class PurchaseRequisitionItemRepo extends MasterRepo<PurchaseRequisitionItem>{
    public PurchaseRequisitionItemRepo() {
        super(Path.of("database/purchaseRequisitionItem.txt"));
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
    
    // for PR deletion
    public void deleteByPRID(Long prid) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            PurchaseRequisitionItem is = stringToObject(line);

            if (!Objects.equals(is.getPurchaseRequisitionId(), prid)) {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // Convert object to string for file storage
    @Override
    protected String objectToString(PurchaseRequisitionItem item) {
        return String.join("|",
                String.valueOf(item.getId()),
                String.valueOf(item.getPurchaseRequisitionId()),
                String.valueOf(item.getItemId()),
                String.valueOf(item.getQuantity())
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

        return item;
    }
    
    
    
}
