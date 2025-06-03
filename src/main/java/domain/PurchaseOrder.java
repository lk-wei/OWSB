/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kang Wei
 */
public class PurchaseOrder implements Identifiable<Long>{
    private Long id; // PK
    private String purchaseOrderCode; //
    private Long purchaseRequisitionId; // FK to PurchaseRequisition//
    private Long createdById; // FK to User
    private Long supplierId; // FK to Supplier //
    private LocalDate orderDate; 
    private LocalDate expectedDeliveryDate;
    private String status; // Draft/PendingApproval/Approved/Rejected/Shipped/Received
    private Double totalAmount; //d
    private List<PurchaseOrderItem> item;

    // Constructors
    public PurchaseOrder() {
    }

    public PurchaseOrder(Long id, String purchaseOrderCode, Long purchaseRequisitionId, Long createdById, Long supplierId, LocalDate orderDate, LocalDate expectedDeliveryDate, String status, Double totalAmount, List<PurchaseOrderItem> item) {
        this.id = id;
        this.purchaseOrderCode = purchaseOrderCode;
        this.purchaseRequisitionId = purchaseRequisitionId;
        this.createdById = createdById;
        this.supplierId = supplierId;
        this.orderDate = orderDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.item = item;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurchaseRequisitionId() {
        return purchaseRequisitionId;
    }

    public void setPurchaseRequisitionId(Long purchaseRequisitionId) {
        this.purchaseRequisitionId = purchaseRequisitionId;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<PurchaseOrderItem> getItem() {
        return item;
    }

    public void setItem(List<PurchaseOrderItem> item) {
        this.item = item;
    }
    
    public String getPurchaseOrderCode() {
        return purchaseOrderCode;
    }

    public void setPurchaseOrderCode(String purchaseOrderCode) {
        this.purchaseOrderCode = purchaseOrderCode;
    }    
    // toString method
    @Override
    public String toString() {
        return purchaseOrderCode;
    }
}
