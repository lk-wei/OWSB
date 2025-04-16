/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.math.BigDecimal;

/**
 *
 * @author zuwei
 */
public class Item {

    private Long itemId;
    private String itemCode;
    private String itemName;
    private int currentStock;
    private int minStock;
    private double unitCost;

    public Item(Long itemId, String itemCode, String itemName, int currentStock, int minStock, double unitCost) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.currentStock = currentStock; 
        this.minStock = minStock;
        this.unitCost = unitCost;
    }

  

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }
    
    








 
  


}
