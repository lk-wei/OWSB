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
public class StockReport {
    private Long reportId;
    private String reportCode;
    private Long createdBy;
    private Date creationDate;
    private String description;
    private String status;
//    private List<StockUpdate> stockUpdate;

    public StockReport() {
    }

    public StockReport(Long reportId, String reportCode, Long createdBy, Date creationDate, String description, String status) {
        this.reportId = reportId;
        this.reportCode = reportCode;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.description = description;
        this.status = status;
    }
    
    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
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
}
