/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Kang Wei
 */
public class StockReportItem {
    private Long stockReportItemId;
    private Long stockReportId;
    private Long stockUpdateId;
    
    public StockReportItem() {
    }

    public StockReportItem(Long stockReportItemId, Long stockReportId, Long stockUpdateId) {
        this.stockReportItemId = stockReportItemId;
        this.stockReportId = stockReportId;
        this.stockUpdateId = stockUpdateId;
    }

    public Long getStockReportItemId() {
        return stockReportItemId;
    }

    public void setStockReportItemId(Long stockReportItemId) {
        this.stockReportItemId = stockReportItemId;
    }

    public Long getStockReportId() {
        return stockReportId;
    }

    public void setStockReportId(Long stockReportId) {
        this.stockReportId = stockReportId;
    }

    public Long getStockUpdateId() {
        return stockUpdateId;
    }

    public void setStockUpdateId(Long stockUpdateId) {
        this.stockUpdateId = stockUpdateId;
    }
   
    
}
