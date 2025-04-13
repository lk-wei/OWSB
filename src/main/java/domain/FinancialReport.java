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
public class FinancialReport {
    private Long financialReportId;
    private String reportCode;
    private Long createdBy;
    private Date creationDate;
    private String description;
    private String status;
    private List<Payment> paymentList;
    
    public FinancialReport() {
    }

    public FinancialReport(Long financialReportId, String reportCode, Long createdBy, Date creationDate, String description, String status, List<Payment> paymentList) {
        this.financialReportId = financialReportId;
        this.reportCode = reportCode;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.description = description;
        this.status = status;
        this.paymentList = paymentList;
    }
    
    public Long getFinancialReportId() {
        return financialReportId;
    }

    public void setFinancialReportId(Long financialReportId) {
        this.financialReportId = financialReportId;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }
}
