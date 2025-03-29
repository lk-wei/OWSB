/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author CK
 */
public class ItemSupplier {
    private Long itemSupplierId; //PK
    private Long itemId; // FK to Item
    private Long supplierId; //FK to Supplier

    public ItemSupplier() {
    }

    public ItemSupplier(Long itemSupplierId, Long itemId, Long supplierId) {
        this.itemSupplierId = itemSupplierId;
        this.itemId = itemId;
        this.supplierId = supplierId;
    }

    public Long getItemSupplierId() {
        return itemSupplierId;
    }

    public void setItemSupplierId(Long itemSupplierId) {
        this.itemSupplierId = itemSupplierId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
    
    
}
