/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.PurchaseOrderItem;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Kang Wei
 */
public class PurchaseOrderItemRepo extends MasterRepo<PurchaseOrderItem>{
    public PurchaseOrderItemRepo(){
       super(Path.of("database/purchaseOrderItem.txt"));
    }
    
    public List<PurchaseOrderItem> getByPurchaseOrderId(Long poid) throws IOException{
        List<PurchaseOrderItem> PurchaseOrderItemList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
   
        for(String line : lines){
            PurchaseOrderItem poi = stringToObject(line);
            
            if(Objects.equals(poi.getPurchaseOrderId(), poid)){
                PurchaseOrderItemList.add(poi);
            }
        }
        return PurchaseOrderItemList;
    }
    
    // Converts object to string for text file storage
    @Override
    protected String objectToString(PurchaseOrderItem item) {
        return String.join("|",
            item.getId().toString(),
            item.getPurchaseOrderId().toString(),
            item.getItemId().toString(),
            Integer.toString(item.getQuantity()),
            Double.toString(item.getUnitCost())
        );
    }

    // Converts pipe-delimited string back to PurchaseOrderItem object
    @Override
    protected PurchaseOrderItem stringToObject(String line) {
        String[] parts = line.split("\\|", -1); // -1 keeps empty values

        return new PurchaseOrderItem(
            Long.valueOf(parts[0]), // purchaseOrderItemId
            Long.valueOf(parts[1]), // purchaseOrderId
            Long.valueOf(parts[2]), // itemId
            Integer.parseInt(parts[3]), // quantity
            Double.parseDouble(parts[4]) // unitCost
        );
    }
}
