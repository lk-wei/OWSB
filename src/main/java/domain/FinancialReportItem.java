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
public class FinancialReportItem {
    private Long financialReportItemId;
    private Long financialReportId;
    private Long paymentId;
    
    public FinancialReportItem() {
    }

    public FinancialReportItem(Long financialReportItemId, Long financialReportId, Long paymentId) {
        this.financialReportItemId = financialReportItemId;
        this.financialReportId = financialReportId;
        this.paymentId = paymentId;
    }

    public Long getFinancialReportItemId() {
        return financialReportItemId;
    }

    public void setFinancialReportItemId(Long financialReportItemId) {
        this.financialReportItemId = financialReportItemId;
    }

    public Long getFinancialReportId() {
        return financialReportId;
    }

    public void setFinancialReportId(Long financialReportId) {
        this.financialReportId = financialReportId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
   
    
}
