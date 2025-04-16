/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author jacks
 */
public class InventoryTransaction implements Identifiable<Long>{ 
    private Long id; // PK
    private Long itemId; // FK to Item
    private String transactionType; // Purchase/Sale/Adjustment
    private int quantity;
    private Long referenceId;
    private String referenceType;
    private Long recordedById; // FK to user
    
    //Constructor
    public InventoryTransaction() {
    }

    public InventoryTransaction(Long id, Long itemId, 
                               String transactionType, int quantity, 
                               Long referenceId, String referenceType, Long recordedById) {
        this.id = id;
        this.itemId = itemId;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.referenceId = referenceId;
        this.referenceType = referenceType;
        this.recordedById = recordedById;
    }
    
    // Getters and Setters
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public Long getRecordedById() {
        return recordedById;
    }

    public void setRecordedById(Long recordedById) {
        this.recordedById = recordedById;
    }
  
}
