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
public class PurchaseRequisition implements Identifiable<Long>{
    private Long id; //PK
    private String purchaseRequisitionCode;
    private Long requestedById; //FK to User
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

    public PurchaseRequisition() {
    }

    public PurchaseRequisition(Long id, Long requestById, LocalDate requestDate, LocalDate requiredDate, Status status) {
        this.id = id;
        this.requestById = requestById;
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

    public Long getRequestById() {
        return requestById;
    }

    public void setRequestById(Long requestById) {
        this.requestById = requestById;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
}
