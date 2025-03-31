/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;

/**
 *
 * @author See Kai Yang
 */
public class PurchaseRequisition {
    private long purchaseRequisitionID; //PK
    private long requestById; //FK to User
    private LocalDate requestDate; 
    private LocalDate requiredDate;
    private Status status;  //(Draft/Submitted/Approved/Rejected/ConvertedToPurchaseOrder)
    
    public enum Status {
        DRAFT,
        SUBMITTED,
        APPROVED,
        REJECTED,
        CONVERTED_TO_PURCHASE_ORDER
    }

    public long getPurchaseRequisitionID() {
        return purchaseRequisitionID;
    }

    public long getRequestById() {
        return requestById;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setPurchaseRequisitionID(long purchaseRequisitionID) {
        this.purchaseRequisitionID = purchaseRequisitionID;
    }

    public void setRequestById(long requestById) {
        this.requestById = requestById;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
}
