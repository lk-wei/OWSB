/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.PurchaseOrderItem;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Kang Wei
 */
public class PurchaseOrderItemRepo {
    
    // define the txt file that stores data
    final private Path filePath = Path.of("purchaseOrderItem.txt");
    
    public PurchaseOrderItemRepo(){
       
    }
    
    // create
    public void createPurchaseOrderItem(PurchaseOrderItem PurchaseOrderItem) throws IOException{
         List<String> lines = Files.readAllLines(filePath);
         lines.add(objectToString(PurchaseOrderItem));
         
         Files.write(filePath, lines);
    }
    
    // read
    
    public List<PurchaseOrderItem> getAllPurchaseOrderItem() throws IOException{
        List<PurchaseOrderItem> PurchaseOrderItemList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
        
        for(String line : lines){
            PurchaseOrderItem poi = stringToObject(line);
            PurchaseOrderItemList.add(poi);
        }
        return PurchaseOrderItemList;
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
    
    // update
    public void updatePurchaseOrderItem(PurchaseOrderItem PurchaseOrderItem) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            PurchaseOrderItem u = stringToObject(line);
            
            if(u.getPurchaseOrderItemId().equals(PurchaseOrderItem.getPurchaseOrderItemId())){
                updatedLines.add(objectToString(PurchaseOrderItem));
            }else{
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // delete
    public void deletePurchaseOrderItem(PurchaseOrderItem PurchaseOrderItem) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            PurchaseOrderItem u = stringToObject(line);
            
            if(!u.getPurchaseOrderItemId().equals(PurchaseOrderItem.getPurchaseOrderItemId())){
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // Converts PurchaseOrderItem object to pipe-delimited string for text file storage
    private String objectToString(PurchaseOrderItem item) {
        return String.join("|",
            item.getPurchaseOrderItemId().toString(),
            item.getPurchaseOrderId().toString(),
            item.getItemId().toString(),
            item.getQuantity().toString(),
            item.getUnitCost().toString(),
            item.getReceivedQuantity().toString()
        );
    }

    // Converts pipe-delimited string back to PurchaseOrderItem object
    private PurchaseOrderItem stringToObject(String line) {
        String[] parts = line.split("\\|", -1); // -1 keeps empty values

        return new PurchaseOrderItem(
            parts[0].equals("null") ? null : Long.valueOf(parts[0]), // purchaseOrderItemId
            parts[1].equals("null") ? null : Long.valueOf(parts[1]), // purchaseOrderId
            parts[2].equals("null") ? null : Long.valueOf(parts[2]), // itemId
            parts[3].equals("null") ? null : Integer.valueOf(parts[3]), // quantity
            parts[4].equals("null") ? null : new BigDecimal(parts[4]), // unitCost
            parts[5].equals("null") ? null : Integer.valueOf(parts[5])  // receivedQuantity
        );
    }
}
