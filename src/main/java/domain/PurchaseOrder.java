/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Kang Wei
 */
public class PurchaseOrder {
    private Long purchaseOrderId; // PK
    private Long purchaseRequisitionId; // FK to PurchaseRequisition
    private Long createdById; // FK to User
    private Long supplierId; // FK to Supplier
    private LocalDate orderDate;
    private LocalDate expectedDeliveryDate;
    private String status; // Draft/PendingApproval/Approved/Rejected/Shipped/Received
    private Long approvedById; // FK to User
    private Double totalAmount;
    private List<PurchaseOrderItem> item;

    // Constructors
    public PurchaseOrder() {
    }

    public PurchaseOrder(Long purchaseOrderId, Long purchaseRequisitionId, Long createdById, 
                        Long supplierId, LocalDate orderDate, LocalDate expectedDeliveryDate, 
                        String status, Long approvedById, Double totalAmount, List<PurchaseOrderItem> item) {
        this.purchaseOrderId = purchaseOrderId;
        this.purchaseRequisitionId = purchaseRequisitionId;
        this.createdById = createdById;
        this.supplierId = supplierId;
        this.orderDate = orderDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.status = status;
        this.approvedById = approvedById;
        this.totalAmount = totalAmount;
        this.item = item;
    }

    // Getters and Setters
    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
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

    public Long getApprovedById() {
        return approvedById;
    }

    public void setApprovedById(Long approvedById) {
        this.approvedById = approvedById;
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
    
    // toString method
    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "purchaseOrderId=" + purchaseOrderId +
                ", purchaseRequisitionId=" + purchaseRequisitionId +
                ", createdById=" + createdById +
                ", supplierId=" + supplierId +
                ", orderDate=" + orderDate +
                ", expectedDeliveryDate=" + expectedDeliveryDate +
                ", status='" + status + '\'' +
                ", approvedById=" + approvedById +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
