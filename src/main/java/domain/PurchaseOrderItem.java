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
    private int quantity;
    private double unitCost;

    // Constructors
    public PurchaseOrderItem() {
    }

    public PurchaseOrderItem(Long purchaseOrderItemId, Long purchaseOrderId, Long itemId, int quantity, double unitCost) {
        this.purchaseOrderItemId = purchaseOrderItemId;
        this.purchaseOrderId = purchaseOrderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }
}
