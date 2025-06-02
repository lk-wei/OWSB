/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;



import domain.Item;
import domain.PurchaseRequisition;
import domain.PurchaseRequisitionItem;
import domain.Supplier;
import domain.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author See Kai Yang
 */
public class PurchaseRequisitionRepo extends MasterRepo<PurchaseRequisition>{
    public PurchaseRequisitionRepo() {
        super(Path.of("database/purchaseRequisition.txt"));
        
    }
    
    // Read by ID
    public PurchaseRequisition getPurchaseRequisitionById(long prId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            PurchaseRequisition pr = stringToObject(line);
            if (pr.getId() == prId) {
                return pr;
            }
        }
        return null;
    }
    
    public void save(PurchaseRequisition pr) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        lines.add(objectToString(pr)); // Convert the object to string and add to the list
        Files.write(filePath, lines);  // Save the lines back to the file
    }
    
    
    public void delete(PurchaseRequisition pr) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        // Filter out the line corresponding to the PurchaseRequisition you want to delete
        for (String line : lines) {
            PurchaseRequisition existingPr = stringToObject(line);
            if (!existingPr.getId().equals(pr.getId())) {
                updatedLines.add(line);
            }
        }

        // Write the updated lines back to the file
        Files.write(filePath, updatedLines);
    }

        
    public DefaultTableModel getTableModel() throws IOException {
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "",
                    "Purchase Requisition Code", 
                    "Requested By", 
                    "Request Date", 
                    "Required Date", 
                    "Supplier Code", 
                    "Supplier Name", 
                    "Status", 
                    "View"
                }
            );

            List<PurchaseRequisition> reports = getAll();
            SupplierRepo supplierRepo = new SupplierRepo();
            UserRepo userRepo = new UserRepo();
            PurchaseRequisitionItemRepo itemRepo = new PurchaseRequisitionItemRepo();  // Add this to load items

            for (PurchaseRequisition report : reports) {
                // Fetch and assign items to the current PurchaseRequisition using purchaseRequisitionItemId
                List<PurchaseRequisitionItem> items = itemRepo.getItemsByRequisitionId(report.getPurchaseRequisitionItemId());
                report.setItem(items);  // Set the items in the PurchaseRequisition object

                // Debugging: check if items are fetched correctly
                System.out.println("Items for Purchase Requisition " + report.getPurchaseRequisitionCode() + ": " + items.size());

                // Process the items and populate the table
                for (PurchaseRequisitionItem item : items) {
                    User user = userRepo.getUserById(report.getRequestedById());
                    Supplier supplierObj = supplierRepo.getSupplierById(item.getSupplierId());

                    model.addRow(new Object[]{
                        report.getId().toString(),
                        report.getPurchaseRequisitionCode(),
                        user.getFullName(),
                        report.getRequestDate(),
                        report.getRequiredDate(),
                        supplierObj.getSupplierCode(),
                        supplierObj.getSuppliername(), // or supplier name
                        report.getStatus(),
                        "View"
                    });
                }
            }

            return model;
    }    


    // Convert object to string for file storage
    @Override
    protected String objectToString(PurchaseRequisition pr) {
        return String.join("|",
                String.valueOf(pr.getId()),
                pr.getPurchaseRequisitionCode(),
                String.valueOf(pr.getPurchaseRequisitionItemId()),
                String.valueOf(pr.getRequestedById()),
                pr.getRequestDate().toString(),
                pr.getRequiredDate().toString(),
                pr.getStatus()
        );
    }

    // Convert string to object
    @Override
    protected PurchaseRequisition stringToObject(String line) {
        String[] parts = line.split("\\|"); // Split by pipe delimiter
        PurchaseRequisition pr = new PurchaseRequisition();

        pr.setId(Long.parseLong(parts[0]));
        pr.setPurchaseRequisitionCode(parts[1]);
        pr.setPurchaseRequisitionItemId(Long.parseLong(parts[2]));
        pr.setRequestedById(Long.valueOf(parts[3]));
        pr.setRequestDate(LocalDate.parse(parts[4]));
        pr.setRequiredDate(LocalDate.parse(parts[5]));
        pr.setStatus(parts[6]);

        return pr;
    }
    
    
}
