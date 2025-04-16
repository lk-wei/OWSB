/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.PurchaseOrder;
import function.IdGenerator;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kang Wei
 */
public class PurchaseOrderRepo {
    // define the txt file that stores data
    final private Path filePath = Path.of("database/purchaseOrder.txt");
    
    public PurchaseOrderRepo(){
       
    }
    
    // create
    public void createPurchaseOrder(PurchaseOrder PurchaseOrder) throws IOException{
        PurchaseOrder.setPurchaseOrderId(IdGenerator.getId(filePath));
        
        List<String> lines = Files.readAllLines(filePath);
        lines.add(objectToString(PurchaseOrder));

        Files.write(filePath, lines);
    }
    
    // read
    
    public List<PurchaseOrder> getAllPurchaseOrder() throws IOException{
        List<PurchaseOrder> PurchaseOrderList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
        
        for(String line : lines){
            PurchaseOrder po = stringToObject(line);
            PurchaseOrderList.add(po);
        }
        return PurchaseOrderList;
    }
    
    // update
    public void updatePurchaseOrder(PurchaseOrder PurchaseOrder) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            PurchaseOrder u = stringToObject(line);
            
            if(u.getPurchaseOrderId().equals(PurchaseOrder.getPurchaseOrderId())){
                updatedLines.add(objectToString(PurchaseOrder));
            }else{
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // delete
    public void deletePurchaseOrder(PurchaseOrder PurchaseOrder) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            PurchaseOrder u = stringToObject(line);
            
            if(!u.getPurchaseOrderId().equals(PurchaseOrder.getPurchaseOrderId())){
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    public PurchaseOrder getOrderById(Long purchaseOrderId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            PurchaseOrder po = stringToObject(line);

            if (po.getPurchaseOrderId().equals(purchaseOrderId)) {
                return po;
            }
        }
        return null;
    }
    
    // others
    
    // Converts PurchaseOrder object to pipe-delimited string for text file storage
    private String objectToString(PurchaseOrder po) {
        return String.join("|",
            po.getPurchaseOrderId().toString(),
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
    private PurchaseOrder stringToObject(String line) throws IOException {
        
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
            itemRepo.getByPurchaseOrderId(Long.valueOf(parts[0]))     // item list                            
        );
    }
}
