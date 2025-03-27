/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.math.BigDecimal;

/**
 *
 * @author Kang Wei
 */
public class PurchaseOrderItem {
    private Long purchaseOrderItemId; // PK
    private Long purchaseOrderId;    // FK to PurchaseOrder
    private Long itemId;             // FK to Item
    private Integer quantity;
    private BigDecimal unitCost;
    private Integer receivedQuantity;

    // Constructors
    public PurchaseOrderItem() {
    }

    public PurchaseOrderItem(Long purchaseOrderItemId, Long purchaseOrderId, Long itemId, 
                            Integer quantity, BigDecimal unitCost, Integer receivedQuantity) {
        this.purchaseOrderItemId = purchaseOrderItemId;
        this.purchaseOrderId = purchaseOrderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.receivedQuantity = receivedQuantity;
    }

    // Getters and Setters
    public Long getPurchaseOrderItemId() {
        return purchaseOrderItemId;
    }

    public void setPurchaseOrderItemId(Long purchaseOrderItemId) {
        this.purchaseOrderItemId = purchaseOrderItemId;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public Integer getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(Integer receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    @Override
    public String toString() {
        return "PurchaseOrderItem{" +
                "purchaseOrderItemId=" + purchaseOrderItemId +
                ", purchaseOrderId=" + purchaseOrderId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                ", unitCost=" + unitCost +
                ", receivedQuantity=" + receivedQuantity +
                '}';
    }
}
