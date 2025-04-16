package repository;

import domain.FinancialReportItem;
import domain.Payment;
import domain.Supplier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class FinancialReportItemRepo extends MasterRepo<FinancialReportItem> {
    
    public FinancialReportItemRepo() {
        super(Path.of("database/financialReport.txt"));
    }
    
    // Custom methods
    public List<FinancialReportItem> getByFinancialReportId(Long frid) throws IOException{
        List<FinancialReportItem> itemList = new ArrayList<>();
        for (FinancialReportItem item : getAll()) {
            if(item.getFinancialReportId().equals(frid)) {
                itemList.add(item);
            }
        }
        return itemList;
    }
    
    public DefaultTableModel getTableModel() throws IOException {
        String[] columnNames = {"Payment Number", "Date", "Supplier Code", "Supplier Name", "Amount", ""};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (FinancialReportItem item : getAll()) {
            Payment py = new PaymentRepo().getPaymentById(item.getPaymentId());
            Supplier s = new SupplierRepo().getSupplierById(py.getSupplierId());
            
            model.addRow(new Object[]{
                py.getPaymentCode(),
                py.getPaymentDate(),
                s.getSupplierCode(),
                s.getSuppliername(),
                py.getPaymentAmount(),
                "" // Action 
            });
        }
        return model;
    }
    
    // Implement required abstract methods
    @Override
    protected Long getId(FinancialReportItem entity) {
        return entity.getFinancialReportItemId();
    }
    
    @Override
    protected void setId(FinancialReportItem entity, long id) {
        entity.setFinancialReportItemId(id);
    }

    @Override
    protected String objectToString(FinancialReportItem report) {
        return String.join("|",
            report.getFinancialReportItemId().toString(),
            report.getFinancialReportId().toString(),
            report.getPaymentId().toString()
        );
    }

    @Override
    protected FinancialReportItem stringToObject(String line) {
        String[] parts = line.split("\\|", -1);
        
        return new FinancialReportItem(
            Long.valueOf(parts[0]), // financialReport item Id
            Long.valueOf(parts[1]),                 // financial report id
            Long.valueOf(parts[2])    // payment id
        );
    }
}