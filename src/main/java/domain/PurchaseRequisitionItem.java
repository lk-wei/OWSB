/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author See Kai Yang
 */
public class PurchaseRequisitionItem implements Identifiable<Long>{
    
    private Long id; //PK
    private Long purchaseRequisitionId; //FK to PurchaseRequisition
    private Long itemId; //FK to Item
    private int quantity; 
    private Long supplierId;  //FK to Supplier
    
    public PurchaseRequisitionItem(){
        
    }

    public PurchaseRequisitionItem(Long id, Long purchaseRequisitionId, Long itemId, int quantity, Long supplierId) {
        this.id = id;
        this.purchaseRequisitionId = purchaseRequisitionId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.supplierId = supplierId;
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}
