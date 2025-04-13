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
    private Long reportItemId;
    private Long stockReportId;
    private Long paymentId;
    
    public StockReportItem() {
    }

    public StockReportItem(Long reportItemId, Long stockReportId, Long stockUpdateId) {
        this.reportItemId = reportItemId;
        this.stockReportId = stockReportId;
        this.paymentId = paymentId;
    }

    public Long getReportItemId() {
        return reportItemId;
    }

    public void setReportItemId(Long reportItemId) {
        this.reportItemId = reportItemId;
    }

    public Long getStockReportId() {
        return stockReportId;
    }

    public void setStockReportId(Long stockReportId) {
        this.stockReportId = stockReportId;
    }

    public Long getStockUpdateId() {
        return paymentId;
    }

    public void setStockUpdateId(Long paymentId) {
        this.paymentId = paymentId;
    }
   
    
}
