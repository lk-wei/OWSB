/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.InventoryTransaction;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jacks
 */
public class InventoryTransactionRepo {
    
    // define the txt file that stores data
    final private Path filePath = Path.of("database/inventoryTransaction.txt");
    
    public InventoryTransactionRepo(){
       
    }
    
    // create
    public void createInventoryTransaction(InventoryTransaction InventoryTransaction) throws IOException{
         List<String> lines = Files.readAllLines(filePath);
         lines.add(objectToString(InventoryTransaction));
         
         Files.write(filePath, lines);
    }
    
    // read
    public List<InventoryTransaction> getAllInventoryTransaction() throws IOException{
        List<InventoryTransaction> InventoryTransactionList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
        
        for(String line : lines){
            InventoryTransaction it = stringToObject(line);
            InventoryTransactionList.add(it);
        }
        return InventoryTransactionList;
    }
    
    // update
    public void updateInventoryTransaction(InventoryTransaction InventoryTransaction) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            InventoryTransaction it = stringToObject(line);
            
            if(it.getInventoryTransactionId().equals(InventoryTransaction.getInventoryTransactionId())){
                updatedLines.add(objectToString(InventoryTransaction));
            }else{
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // delete
    public void deleteInventoryTransaction(InventoryTransaction InventoryTransaction) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            InventoryTransaction it = stringToObject(line);
            
            if(!it.getInventoryTransactionId().equals(InventoryTransaction.getInventoryTransactionId())){
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // others
    
    // Converts InventoryTransaction object to pipe-delimited string for text file storage
    private String objectToString(InventoryTransaction it) {
        return String.join("|",
            it.getInventoryTransactionId().toString(),
            it.getItemId().toString(),
            it.getTransactionType(),
            Integer.toString(it.getQuantity()),
            it.getReferenceId().toString(),
            it.getReferenceType(),
            it.getRecordedById().toString()
        );
    }

    // Converts pipe-delimited string back to InventoryTransaction object
    private InventoryTransaction stringToObject(String line) {
        String[] parts = line.split("\\|", -1); // -1 keeps empty values

        return new InventoryTransaction(
            Long.valueOf(parts[0]), // inventoryTransactionId
            Long.valueOf(parts[1]), // itemId
            parts[2], // transactionType
            Integer.parseInt(parts[3]), // quantity
            Long.valueOf(parts[4]), // referenceId
            parts[5], // referenceType
            Long.valueOf(parts[6]) // recordedById
        );
    }
    
}
