/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.FinancialApproval;
import java.nio.file.*;
import java.time.LocalDate;

/**
 *
 * @author jacks
 */
public class FinancialApprovalRepo extends MasterRepo<FinancialApproval>{
    public FinancialApprovalRepo(){
       super(Path.of("database/financialApproval.txt"));
    }
    
    // Custom Method
    
    // Converts InventoryTransaction object to pipe-delimited string for text file storage
    @Override
    protected String objectToString(FinancialApproval fa) {
        return String.join("|",
            fa.getId().toString(),
            fa.getPurchaseOrderId().toString(),
            fa.getApprovedById().toString(),
            fa.getApprovalDate().toString()
        );
    }

    // Converts pipe-delimited string back to InventoryTransaction object
    @Override
    protected FinancialApproval stringToObject(String line) {
        String[] parts = line.split("\\|", -1); // -1 keeps empty values

        return new FinancialApproval(
            Long.valueOf(parts[0]), // financialApprovalId
            Long.valueOf(parts[1]), // purchaseOrderId
            Long.valueOf(parts[2]), // approvedById
            LocalDate.parse(parts[3]) // approvalDate
        );
    }
    
}
