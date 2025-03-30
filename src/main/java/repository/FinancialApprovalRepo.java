/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.FinancialApproval;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jacks
 */
public class FinancialApprovalRepo {
    
    // define the txt file that stores data
    final private Path filePath = Path.of("database/financialApproval.txt");
    
    public FinancialApprovalRepo(){
       
    }
    
    // create
    public void createFinancialApproval(FinancialApproval FinancialApproval) throws IOException{
         List<String> lines = Files.readAllLines(filePath);
         lines.add(objectToString(FinancialApproval));
         
         Files.write(filePath, lines);
    }
    
    // read
    public List<FinancialApproval> getAllFinancialApproval() throws IOException{
        List<FinancialApproval> FinancialApprovalList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
        
        for(String line : lines){
            FinancialApproval fa = stringToObject(line);
            FinancialApprovalList.add(fa);
        }
        return FinancialApprovalList;
    }
    
    // update
    public void updateFinancialApproval(FinancialApproval FinancialApproval) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            FinancialApproval fa = stringToObject(line);
            
            if(fa.getFinancialApprovalId().equals(FinancialApproval.getFinancialApprovalId())){
                updatedLines.add(objectToString(FinancialApproval));
            }else{
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // delete
    public void deleteFinancialApproval(FinancialApproval FinancialApproval) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            FinancialApproval fa = stringToObject(line);
            
            if(!fa.getFinancialApprovalId().equals(FinancialApproval.getFinancialApprovalId())){
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // others
    
    // Converts InventoryTransaction object to pipe-delimited string for text file storage
    private String objectToString(FinancialApproval fa) {
        return String.join("|",
            fa.getFinancialApprovalId().toString(),
            fa.getPurchaseOrderId().toString(),
            fa.getApprovedById().toString(),
            fa.getApprovalDate().toString(),
            fa.getApprovedAmount().toString()
        );
    }

    // Converts pipe-delimited string back to InventoryTransaction object
    private FinancialApproval stringToObject(String line) {
        String[] parts = line.split("\\|", -1); // -1 keeps empty values

        return new FinancialApproval(
            Long.valueOf(parts[0]), // financialApprovalId
            Long.valueOf(parts[1]), // purchaseOrderId
            Long.valueOf(parts[2]), // approvedById
            LocalDate.parse(parts[3]), // approvalDate
            Double.valueOf(parts[4]) // approvedAmount
        );
    }
    
}
