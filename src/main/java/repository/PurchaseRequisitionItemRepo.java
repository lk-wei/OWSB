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

    // Convert object to string for file storage
    @Override
    protected String objectToString(PurchaseRequisitionItem item) {
        return String.join(",",
                String.valueOf(item.getId()),
                String.valueOf(item.getPurchaseRequisitionId()),
                String.valueOf(item.getPurchaseRequisitionCode()),
                String.valueOf(item.getItemId()),
                String.valueOf(item.getQuantity()),
                String.valueOf(item.getSupplierId())
        );
    }

    // Convert string to object
    @Override
    protected PurchaseRequisitionItem stringToObject(String line) {
        String[] parts = line.split("|");
        PurchaseRequisitionItem item = new PurchaseRequisitionItem();
        item.setId(Long.valueOf(parts[0]));
        item.setPurchaseRequisitionId(Long.valueOf(parts[1]));
        item.setItemId(Long.valueOf(parts[2]));
        item.setQuantity(Integer.parseInt(parts[3]));
        item.setSupplierId(Long.valueOf(parts[4]));
        return item;
    }
    
    
    
}
