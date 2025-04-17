/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author See Kai Yang
 */
public class PurchaseRequisitionItem {
    
    private long purchaseRequisitionItemId; //PK
    private long purchaseRequisitionId; //FK to PurchaseRequisition
    private long itemId; //FK to Item
    private int quantity; 
    private long supplierId;  //FK to Supplier

    public long getPurchaseRequisitionItemId() {
        return purchaseRequisitionItemId;
    }

    public long getPurchaseRequisitionId() {
        return purchaseRequisitionId;
    }

    public long getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setPurchaseRequisitionItemId(long purchaseRequisitionItemId) {
        this.purchaseRequisitionItemId = purchaseRequisitionItemId;
    }

    public void setPurchaseRequisitionId(long purchaseRequisitionId) {
        this.purchaseRequisitionId = purchaseRequisitionId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public Object getPurchaseRequisitionCode() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    
}
