/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.PurchaseOrder;
import domain.Supplier;
import domain.User;
import function.IdGenerator;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

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
        IdGenerator ig = new IdGenerator(filePath);
        PurchaseOrder.setPurchaseOrderId(ig.getId());
        
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
    
    public DefaultTableModel getTableModel() throws IOException {
    DefaultTableModel model = new DefaultTableModel(
        new Object[][]{},
        new String[]{
            "Purchase Order Code", 
            "Created By", 
            "Suppplier Code",
            "Supplier Name",
            "Approve by ", 
            "Order Date", 
            "Expected Delivery Date", 
            "Status", 
            "Total Amount", 
            ""
        }
    );

    List<PurchaseOrder> reports = getAllPurchaseOrder();
    SupplierRepo supplierRepo = new SupplierRepo();
    UserRepo userRepo = new UserRepo();
    
    for (PurchaseOrder report : reports) {
        Supplier supp = supplierRepo.getSupplierById(report.getSupplierId());
        User user = userRepo.getUserById(report.getCreatedById());
        model.addRow(new Object[]{
            report.getPurchaseOrderCode(),
            user.getFullName(),
            supp.getSupplierCode(),
            supp.getSuppliername(),
            user.getFullName(),
            report.getOrderDate(),
            report.getExpectedDeliveryDate(),
            report.getStatus(),
            report.getTotalAmount(),
            ""
        });
    }

    return model;
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
    
    // others
    
    // Converts PurchaseOrder object to pipe-delimited string for text file storage
    private String objectToString(PurchaseOrder po) {
        return String.join("|",
            po.getPurchaseOrderId().toString(),
            po.getPurchaseOrderCode(), 
            po.getPurchaseRequisitionId().toString(),
            po.getCreatedById().toString(),
            po.getSupplierId().toString(),
            po.getOrderDate().toString(),
            po.getExpectedDeliveryDate().toString(),
            po.getStatus(),
            po.getApprovalDate().toString(),
            po.getApprovedById().toString(),
            po.getTotalAmount().toString()
        );
    }

    // Converts pipe-delimited string back to PurchaseOrder object
    private PurchaseOrder stringToObject(String line) throws IOException {
        PurchaseOrderItemRepo itemRepo = new PurchaseOrderItemRepo();
        String[] parts = line.split("\\|", -1); // -1 keeps empty values

        return new PurchaseOrder(
            Long.valueOf(parts[0]),              // purchaseOrderId
            parts[1],                            // purchaseOrderCode
            Long.valueOf(parts[2]),              // purchaseRequisitionId
            Long.valueOf(parts[3]),              // createdById
            Long.valueOf(parts[4]),              // supplierId
            LocalDate.parse(parts[5]),           // orderDate
            LocalDate.parse(parts[6]),           // expectedDeliveryDate
            parts[7],                            // status
            LocalDate.parse(parts[8]),                                // approvalDate (not saved)
            Long.valueOf(parts[9]),              // approvedById
            Double.valueOf(parts[10]),            // totalAmount
            itemRepo.getByPurchaseOrderId(Long.valueOf(parts[0])) // item list
        );
    }

}
