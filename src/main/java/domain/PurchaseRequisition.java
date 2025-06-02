/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author See Kai Yang
 */
public class PurchaseRequisition implements Identifiable<Long>{
    private Long id; //PK
    private String purchaseRequisitionCode;
    private Long requestedById; //FK to User
    private LocalDate requestDate; 
    private LocalDate requiredDate;
    private String status;  //(Draft/Submitted/Approved/Rejected/ConvertedToPurchaseOrder)
    private List<PurchaseRequisitionItem> item;
    private long purchaseRequisitionItemId;
    
    public PurchaseRequisition() {
        this.item = new ArrayList<>();
    }

    public PurchaseRequisition(Long id, String purchaseRequisitionCode, Long requestedById, LocalDate requestDate, LocalDate requiredDate, String status) {
        this.id = id;
        this.purchaseRequisitionCode = purchaseRequisitionCode;
        this.requestedById = requestedById;
        this.requestDate = requestDate;
        this.requiredDate = requiredDate;
        this.status = status;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurchaseRequisitionCode() {
        return purchaseRequisitionCode;
    }

    public void setPurchaseRequisitionCode(String purchaseRequisitionCode){
        this.purchaseRequisitionCode = purchaseRequisitionCode;
    }

    public Long getRequestedById() {
        return requestedById;
    }

    public void setRequestedById(Long requestedById) {
        this.requestedById = requestedById;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PurchaseRequisitionItem> getItem(){
        return item;
    }

    public void setItem(List<PurchaseRequisitionItem> item){
        this.item = item;
    }

    
    public long getPurchaseRequisitionItemId() {
        return purchaseRequisitionItemId;
    }

    public void setPurchaseRequisitionItemId(long purchaseRequisitionItemId) {
        this.purchaseRequisitionItemId = purchaseRequisitionItemId;
    }

}
