/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.InventoryTransaction;
import java.nio.file.*;

/**
 *
 * @author jacks
 */
public class InventoryTransactionRepo extends MasterRepo<InventoryTransaction>{
    public InventoryTransactionRepo(){
       super(Path.of("database/inventoryTransaction.txt"));
    }
    
    // custom method
    // others
    
    // Converts InventoryTransaction object to pipe-delimited string for text file storage
    @Override
    protected String objectToString(InventoryTransaction it) {
        return String.join("|",
            it.getId().toString(),
            it.getItemId().toString(),
            it.getTransactionType(),
            Integer.toString(it.getQuantity()),
            it.getReferenceId().toString(),
            it.getReferenceType(),
            it.getRecordedById().toString()
        );
    }

    // Converts pipe-delimited string back to InventoryTransaction object
    @Override
    protected InventoryTransaction stringToObject(String line) {
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
