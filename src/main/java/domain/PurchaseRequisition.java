/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;

import java.util.List;

/**
 *
 * @author See Kai Yang
 */
public class PurchaseRequisition {
    private long purchaseRequisitionID; //PK
    private String purchaseRequisitionCode;
    private long requestedById; //FK to User
    private LocalDate requestDate; 
    private LocalDate requiredDate;
    private String status;  //(Draft/Submitted/Approved/Rejected/ConvertedToPurchaseOrder)
    private List<PurchaseRequisitionItem> item;

    public PurchaseRequisition() {
    }

    public PurchaseRequisition(long purchaseRequisitionID, long requestedById, LocalDate requestDate, LocalDate requiredDate, String status, List<PurchaseRequisitionItem> item) {
        this.purchaseRequisitionID = purchaseRequisitionID;
        this.purchaseRequisitionCode = purchaseRequisitionCode;
        this.requestedById = requestedById;
        this.requestDate = requestDate;
        this.requiredDate = requiredDate;
        this.status = status;
        this.item = item;
    }
    

    public long getPurchaseRequisitionID() {
        return purchaseRequisitionID;
    }

    public long getRequestById() {
        return requestedById;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public String getStatus() {
        return status;
    }

    public void setPurchaseRequisitionID(long purchaseRequisitionID) {
        this.purchaseRequisitionID = purchaseRequisitionID;
    }

    public void setRequestedById(long requestById) {
        this.requestedById = requestById;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PurchaseRequisitionItem> getItem() {
        return item;
    }

    public void setItem(List<PurchaseRequisitionItem> item) {
        this.item = item;
    }

    public String getPurchaseRequisitionCode() {
        return purchaseRequisitionCode;
    }

    public void setPurchaseRequisitionCode(String purchaseRequisitionCode) {
        this.purchaseRequisitionCode = purchaseRequisitionCode;
    }
    
    
    
    
    
}
