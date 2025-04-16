package repository;

import domain.FinancialReport;
import domain.FinancialReportItem;
import domain.Payment;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class FinancialReportRepo extends MasterRepo<FinancialReport> {
    
    public FinancialReportRepo() {
        super(Path.of("database/financialReport.txt"));
    }
    
    // Custom methods
    public List<FinancialReport> getByCreatedBy(Long userId) throws IOException {
        List<FinancialReport> reports = new ArrayList<>();
        for (FinancialReport report : getAll()) {
            if (report.getCreatedBy().equals(userId)) {
                reports.add(report);
            }
        }
        return reports;
    }
    
    public DefaultTableModel getTableModel() throws IOException {
        String[] columnNames = {"Report Number", "Description", "Date", "Created By", ""};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (FinancialReport report : getAll()) {
            model.addRow(new Object[]{
                report.getReportCode(),
                report.getDescription(),
                report.getCreationDate(),
                report.getCreatedBy(),
                "" // Action 
            });
        }
        return model;
    }
    
    // Implement required abstract methods
    @Override
    protected String objectToString(FinancialReport report) {
        return String.join("|",
            report.getId().toString(),
            report.getReportCode(),
            report.getCreatedBy().toString(),
            report.getCreationDate().toString(),
            report.getDescription()
        );
    }

    @Override
    protected FinancialReport stringToObject(String line) {
        String[] parts = line.split("\\|", -1);
        
        return new FinancialReport(
            Long.valueOf(parts[0]), // financialReportId
            parts[1],                 // reportCode
            Long.valueOf(parts[2]), // createdBy
            LocalDate.parse(parts[3]),// creationDate
            parts[4],                 // description
            new ArrayList<>()         // empty payment list (load on demand)
        );
    }
    
    // load payments when needed
    public List<Payment> getItemsForReport(Long financialReportId) throws IOException {
        FinancialReportItemRepo friRepo = new FinancialReportItemRepo();
        PaymentRepo pRepo = new PaymentRepo();
//        Payment payment = new Payment();
        List<Payment> payments = new ArrayList<>();
        
        for (FinancialReportItem item : friRepo.getByFinancialReportId(financialReportId)) {
            payments.add(pRepo.getPaymentById(item.getPaymentId()));
        }
        return payments;
    }
}