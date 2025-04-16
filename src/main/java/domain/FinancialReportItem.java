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
public class FinancialReportItem implements Identifiable<Long>{
    private Long id;
    private Long financialReportId;
    private Long paymentId;
    
    public FinancialReportItem() {
    }

    public FinancialReportItem(Long id, Long financialReportId, Long paymentId) {
        this.id = id;
        this.financialReportId = financialReportId;
        this.paymentId = paymentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
