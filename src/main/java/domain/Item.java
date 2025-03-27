/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author zuwei
 */
public class Item {

    private String itemId;
    private String itemCode;
    private String itemName;
    private String currentStock;
    private String minStock;
    private String unitCost;

    public Item(String itemId, String itemCode, String itemName, String currentStock, String minStock, String unitCost) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.unitCost = unitCost;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemId) {
        this.itemCode = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(String currentStock) {
        this.currentStock = currentStock;
    }

    public String getMinStock() {
        return minStock;
    }

    public void setMinStock(String minStock) {
        this.minStock = minStock;
    }
    
    public String getUnitCost(){
        return unitCost;
    }
    
    public void setUnitCost(String unitCost){
        this.unitCost = unitCost;
    }
}
