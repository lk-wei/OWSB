/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author CK
 */
public class ItemSupplier implements Identifiable<Long>{
    private Long id; //PK
    private Long itemId; // FK to Item
    private Long supplierId; //FK to Supplier

    public ItemSupplier() {
    }

    public ItemSupplier(Long id, Long itemId, Long supplierId) {
        this.id = id;
        this.itemId = itemId;
        this.supplierId = supplierId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
