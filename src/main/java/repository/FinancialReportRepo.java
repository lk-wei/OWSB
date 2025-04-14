/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.FinancialReport;
import domain.FinancialReportItem;
import domain.Payment;
import function.IdGenerator;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kang Wei
 */
public class FinancialReportRepo {
    // define the txt file that stores data
    final private Path filePath = Path.of("database/financialReport.txt");
    
    public FinancialReportRepo(){
       
    }
    
    // create
    public void createFinancialReport(FinancialReport FinancialReport) throws IOException{
        IdGenerator ig = new IdGenerator(filePath);
        FinancialReport.setFinancialReportId(ig.getId());
        
        List<String> lines = Files.readAllLines(filePath);
        lines.add(objectToString(FinancialReport));

        Files.write(filePath, lines);
    }
    
    // read
    public List<FinancialReport> getAllFinancialReport() throws IOException{
        List<FinancialReport> FinancialReportList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
        
        for(String line : lines){
            FinancialReport po = stringToObject(line);
            FinancialReportList.add(po);
        }
        return FinancialReportList;
    }
    
    public List<FinancialReport> getByCreatedBy(Long id) throws IOException{
        List<FinancialReport> financialReportList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
        
        for(String line : lines){
            FinancialReport po = stringToObject(line);
            if(po.getCreatedBy() == id){
                financialReportList.add(po);
            }
        }
        return financialReportList;
    }
    
    // UI method
    public DefaultTableModel getTableModel() throws IOException {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            // These column names must match what's in your JFrame
            new String[]{"Report Number", "Description", "Date", "Created By", ""}
        );

        List<FinancialReport> reports = getAllFinancialReport(); 
            

        for (FinancialReport report : reports) {
            model.addRow(new Object[]{
                report.getReportCode(),    // "Report Number"
                report.getDescription(),   // "Description"
                report.getCreationDate(),  // "Date"
                report.getCreatedBy(),    // "Created By"
                ""                        // Empty column (action buttons?)
            });
        }
        return model;
    }
    
    // update
    public void updateFinancialReport(FinancialReport FinancialReport) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            FinancialReport u = stringToObject(line);
            
            if(u.getFinancialReportId().equals(FinancialReport.getFinancialReportId())){
                updatedLines.add(objectToString(FinancialReport));
            }else{
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // delete
    public void deleteFinancialReport(FinancialReport FinancialReport) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            FinancialReport u = stringToObject(line);
            
            if(!u.getFinancialReportId().equals(FinancialReport.getFinancialReportId())){
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // others
    
    // Converts FinancialReport object to pipe-delimited string for text file storage
    private String objectToString(FinancialReport po) {
        return String.join("|",
            po.getFinancialReportId().toString(),
            po.getReportCode().toString(),
            po.getCreatedBy().toString(),
            po.getCreationDate().toString(),
            po.getDescription().toString(),
            po.getStatus().toString()

        );
    }

    // Convert string to object    
    private FinancialReport stringToObject(String line) throws IOException {
        String[] parts = line.split("\\|", -1); // -1 keeps empty values

        return new FinancialReport(
            Long.parseLong(parts[0]),
            parts[1],
            Long.parseLong(parts[2]),
            LocalDate.parse(parts[3]),
            parts[4],
            parts[5],
            getPaymentList(Long.parseLong(parts[0]))
        );
    }

    private List<Payment> getPaymentList(Long financialReportId) throws IOException{
        FinancialReportItemRepo itemRepo = new FinancialReportItemRepo();
        List<FinancialReportItem> itemList = itemRepo.getByFinancialReportId(financialReportId);
        
        PaymentRepo paymentRepo = new PaymentRepo();
        List<Payment> paymentList = new ArrayList<>();
        
        for(FinancialReportItem item : itemList){
            paymentList.add(paymentRepo.getPaymentById(item.getPaymentId()));
        }

        return paymentList;
    }
}
