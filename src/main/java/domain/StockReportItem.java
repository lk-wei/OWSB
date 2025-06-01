/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Kang Wei
 */
public class StockReportItem implements Identifiable<Long>{
    private Long id;
    private Long stockReportId;
    private Long itemId;
    private String itemCode;
    private String itemName;
    private int stockLevel;
    
    public StockReportItem() {
    }

    public StockReportItem(Long id, Long stockReportId, Long itemId, String itemCode, String itemName, int stockLevel) {
        this.id = id;
        this.stockReportId = stockReportId;
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.stockLevel = stockLevel;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockReportId() {
        return stockReportId;
    }

    public void setStockReportId(Long stockReportId) {
        this.stockReportId = stockReportId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void getItemId(Long itemId) {
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

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }
}
