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
public class Item implements Identifiable<Long>{

    private Long id;
    private String itemCode;
    private String itemName;
    private int currentStock;
    private int minStock;
    private double unitCost;
    
    public Item() {
    }

    public Item(Long id, String itemCode, String itemName, int currentStock, int minStock, double unitCost) {
        this.id = id;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.currentStock = currentStock; 
        this.minStock = minStock;
        this.unitCost = unitCost;
    }

  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
