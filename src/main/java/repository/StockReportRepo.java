package repository;

import domain.StockReportItem;
import domain.StockReport;
import domain.StockUpdate;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class StockReportRepo extends MasterRepo<StockReport> {
    
    public StockReportRepo() {
        super(Path.of("database/stockReport.txt"));
    }
    
    // Custom methods
    public List<StockReport> getByCreatedBy(Long userId) throws IOException {
        List<StockReport> reports = new ArrayList<>();
        for (StockReport report : getAll()) {
            if (report.getCreatedBy().equals(userId)) {
                reports.add(report);
            }
        }
        return reports;
    }
    
    public DefaultTableModel getTableModel() throws IOException {
        String[] columnNames = {"Report Number", "Description", "Date", "Created By", ""};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (StockReport report : getAll()) {
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
    protected String objectToString(StockReport report) {
        return String.join("|",
            report.getId().toString(),
            report.getReportCode(),
            report.getCreatedBy().toString(),
            report.getCreationDate().toString(),
            report.getDescription()
        );
    }

    @Override
    protected StockReport stringToObject(String line) {
        String[] parts = line.split("\\|", -1);
        
        return new StockReport(
            Long.valueOf(parts[0]), // stockReportId
            parts[1],                 // reportCode
            Long.valueOf(parts[2]), // createdBy
            LocalDate.parse(parts[3]),// creationDate
            parts[4],                 // description
            new ArrayList<>()         // empty payment list (load on demand)
        );
    }
    
    // load payments when needed
    public List<StockUpdate> getItemsForReport(Long stockReportId) throws IOException {
        StockReportItemRepo sriRepo = new StockReportItemRepo();
        StockUpdateRepo suRepo = new StockUpdateRepo();
        List<StockUpdate> su = new ArrayList<>();
        
        
        for (StockReportItem item : sriRepo.getByStockReportId(stockReportId)) {
            su.add(suRepo.getByStockUpdateId(item.getStockUpdateId()));
        }
        return su;
    }
}