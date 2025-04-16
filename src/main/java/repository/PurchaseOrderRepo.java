/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.PurchaseOrder;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kang Wei
 */
public class PurchaseOrderRepo extends MasterRepo<PurchaseOrder>{
    // define the txt file that stores data
    final private Path filePath = Path.of("database/purchaseOrder.txt");
    
    public PurchaseOrderRepo(){
       super(Path.of("database/purchaseOrder.txt"));
    }
    
    // custom method
    public PurchaseOrder getOrderById(Long purchaseOrderId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            PurchaseOrder po = stringToObject(line);

            if (po.getId().equals(purchaseOrderId)) {
                return po;
            }
        }
        return null;
    }
    
    // others
    
    // Converts PurchaseOrder object to pipe-delimited string for text file storage
    @Override
    protected String objectToString(PurchaseOrder po) {
        return String.join("|",
            po.getId().toString(),
            po.getPurchaseRequisitionId().toString(),
            po.getCreatedById().toString(),
            po.getSupplierId().toString(),
            po.getOrderDate().toString(),
            po.getExpectedDeliveryDate().toString(),
            po.getStatus(),
            po.getApprovedById().toString(),
            po.getTotalAmount().toString()
        );
    }

    // Converts pipe-delimited string back to PurchaseOrder object
    @Override
    protected PurchaseOrder stringToObject(String line) {
        
        PurchaseOrderItemRepo itemRepo = new PurchaseOrderItemRepo();
        String[] parts = line.split("\\|", -1); // -1 keeps empty values

        return new PurchaseOrder(
            Long.valueOf(parts[0]), // purchaseOrderId
            Long.valueOf(parts[1]), // purchaseRequisitionId
            Long.valueOf(parts[2]),                                 // createdById
            Long.valueOf(parts[3]),                                  // supplierId
            LocalDate.parse(parts[4]),                               // orderDate
            LocalDate.parse(parts[5]),                               // expectedDeliveryDate
            parts[6],                                                // status
            LocalDate.parse(parts[7]),                              // approval 
            Long.valueOf(parts[8]),                                 // approvedById
            Double.valueOf(parts[9]),                                 // totalAmount
            new ArrayList<>()                                        // empty item list                            
        );
    }
}
