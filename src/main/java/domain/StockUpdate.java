/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;

/**
 *
 * @author CK
 */
public class StockUpdate {
    private Long stockUpdateId; //PK
    private String stockUpdateCode;
    private String description;
    private Long itemId; //FK to Item
    private int quantity;
    private LocalDate date;
    private Long updatedById; //FK to User

    public StockUpdate() {
    }

    public StockUpdate(Long stockUpdateId, String stockUpdateCode, String description, Long itemId, int quantity, LocalDate date, Long updatedById) {
        this.stockUpdateId = stockUpdateId;
        this.stockUpdateCode = stockUpdateCode;
        this.description = description;
        this.itemId = itemId;
        this.quantity = quantity;
        this.date = date;
        this.updatedById = updatedById;
    }

    public Long getStockUpdateId() {
        return stockUpdateId;
    }

    public void setStockUpdateId(Long stockUpdateId) {
        this.stockUpdateId = stockUpdateId;
    }

    public String getStockUpdateCode() {
        return stockUpdateCode;
    }

    public void setStockUpdateCode(String stockUpdateCode) {
        this.stockUpdateCode = stockUpdateCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    
}
