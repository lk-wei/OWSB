/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;

/**
 *
 * @author jacks
 */
public class FinancialApproval implements Identifiable<Long>{
    private Long id; // PK
    private Long purchaseOrderId; // FK to PurchaseOrder
    private Long approvedById; // FK to User
    private LocalDate approvalDate;
    
    // Constructors
    public FinancialApproval() {
    }

    public FinancialApproval(Long id, Long purchaseOrderId, 
                            Long approvedById, LocalDate approvalDate) {
        this.id = id;
        this.purchaseOrderId = purchaseOrderId;
        this.approvedById = approvedById;
        this.approvalDate = approvalDate;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getApprovedById() {
        return approvedById;
    }

    public void setApprovedById(Long approvedById) {
        this.approvedById = approvedById;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }
}
