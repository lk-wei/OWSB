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
public class FinancialApproval {
    private Long financialApprovalId; // PK
    private Long purchaseOrderId; // FK to PurchaseOrder
    private Long approvedById; // FK to User
    private LocalDate approvalDate;
    private Double approvedAmount;
    
    // Constructors
    public FinancialApproval() {
    }

    public FinancialApproval(Long financialApprovalId, Long purchaseOrderId, 
                            Long approvedById, LocalDate approvalDate, Double approvedAmount) {
        this.financialApprovalId = financialApprovalId;
        this.purchaseOrderId = purchaseOrderId;
        this.approvedById = approvedById;
        this.approvalDate = approvalDate;
        this.approvedAmount = approvedAmount;
    }
    
    // Getters and Setters
    public Long getFinancialApprovalId() {
        return financialApprovalId;
    }

    public void setFinancialApprovalId(Long financialApprovalId) {
        this.financialApprovalId = financialApprovalId;
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

    public Double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }
    
}
