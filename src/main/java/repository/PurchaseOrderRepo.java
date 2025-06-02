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
import java.util.Objects;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kang Wei
 */
public class PurchaseOrderRepo extends MasterRepo<PurchaseOrder>{
    public PurchaseOrderRepo(){
       super(Path.of("database/purchaseOrder.txt"));
    }
    
    // custom method
    public PurchaseOrder getOrderById(Long purchaseOrderId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            PurchaseOrder po = stringToObject(line);
            if(Objects.equals(po.getId(), purchaseOrderId)){
                return po;
            }
        }
        return null;
    }
    
    public DefaultTableModel getTableModel() throws IOException {
    DefaultTableModel model = new DefaultTableModel(
        new Object[][]{},
        new String[]{
            "",
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

    List<PurchaseOrder> reports = getAll();
    SupplierRepo supplierRepo = new SupplierRepo();
    UserRepo userRepo = new UserRepo();
    
    for (PurchaseOrder report : reports) {
        Supplier supp = supplierRepo.getSupplierById(report.getSupplierId());
        User createdUser = userRepo.getUserById(report.getCreatedById());
        User approvedUser = userRepo.getUserById(report.getApprovedById());
        
        model.addRow(new Object[]{
            report.getId(),
            report.getPurchaseOrderCode(),
            createdUser.getFullName(),
            supp.getSupplierCode(),
            supp.getSuppliername(),
            approvedUser.getFullName(),
            report.getOrderDate(),
            report.getExpectedDeliveryDate(),
            report.getStatus(),
            report.getTotalAmount(),
            "View"
        });
    }

    return model;
}

    
    // others
    
    // Converts PurchaseOrder object to pipe-delimited string for text file storage
    @Override
    protected String objectToString(PurchaseOrder po) {
        return String.join("|",
            po.getId().toString(),
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
    @Override
    protected PurchaseOrder stringToObject(String line) {
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
            LocalDate.parse(parts[8]),           // approvalDate
            Long.valueOf(parts[9]),              // approvedById
            Double.valueOf(parts[10]),            // totalAmount
            new ArrayList<>() // item list
        );
    }

}
