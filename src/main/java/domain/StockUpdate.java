/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author CK
 */
public class StockUpdate implements Identifiable<Long>{
    private Long id; //PK
    private String stockUpdateCode;
    private String description;
    private Long itemId; //FK to Item
    private int quantity;
    private LocalDate date;
    private Long updatedById; //FK to User
    private List<Item> itemList;    

    public StockUpdate() {
    }

    public StockUpdate(Long id, String stockUpdateCode, String description, Long itemId, int quantity, LocalDate date, Long updatedById, List<Item> itemList) {
        this.id = id;
        this.stockUpdateCode = stockUpdateCode;
        this.description = description;
        this.itemId = itemId;
        this.quantity = quantity;
        this.date = date;
        this.updatedById = updatedById;
        this.itemList = itemList;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
    
}
