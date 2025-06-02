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
        
    public DefaultTableModel getTableModel() throws IOException {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "Purchase Requisition Code", 
                "Requested By", 
                "Request Date", 
                "Required Date", 
                "Supplier Code", 
                "Supplier Name", 
                "Status", 
                ""
            }
        );

        List<PurchaseRequisition> reports = getAll();
        SupplierRepo supplierRepo = new SupplierRepo();
        UserRepo userRepo = new UserRepo();
        PurchaseRequisitionItemRepo itemRepo = new PurchaseRequisitionItemRepo();  // Add this to load items

        for (PurchaseRequisition report : reports) {
            // Fetch and assign items to the current PurchaseRequisition
            List<PurchaseRequisitionItem> items = itemRepo.getItemsByRequisitionId(report.getId());  // Method to fetch items for this PR
            report.setItem(items);  // Set the items in the PurchaseRequisition object

            // Now that the items are set, we can process them
            for (PurchaseRequisitionItem item : items) {
                User user = userRepo.getUserById(report.getRequestedById());
                Supplier supplierObj = supplierRepo.getSupplierById(item.getSupplierId());

                model.addRow(new Object[]{
                    report.getPurchaseRequisitionCode(),
                    user.getFullName(),
                    report.getRequestDate(),
                    report.getRequiredDate(),
                    supplierObj.getSupplierCode(),
                    supplierObj.getSuppliername(), // or supplier name
                    report.getStatus(),
                    ""
                });
            }
        }

        return model;
    }    

 
    // Convert object to string for file storage
    @Override
    protected String objectToString(PurchaseRequisition pr) {
        return String.join(",",
                String.valueOf(pr.getId()),
                String.valueOf(pr.getRequestedById()),
                pr.getRequestDate().toString(),
                pr.getRequiredDate().toString(),
                pr.getStatus()
        );
    }

    // Convert string to object
    @Override
    protected PurchaseRequisition stringToObject(String line) {
        String[] parts = line.split("\\|");
        PurchaseRequisition pr = new PurchaseRequisition();
        PurchaseRequisitionItemRepo prir = new PurchaseRequisitionItemRepo();

        pr.setId(Long.parseLong(parts[0]));
        pr.setPurchaseRequisitionCode(parts[1]);
        pr.setRequestedById(Long.valueOf(parts[2]));
        pr.setRequestDate(LocalDate.parse(parts[3]));
        pr.setRequiredDate(LocalDate.parse(parts[4]));
        pr.setStatus(String.valueOf(parts[5]));

        // Print statement to check data loading
        System.out.println("Loading Purchase Requisition: " + pr.getId() + ", Items: " + pr.getItem());

        return pr;
    }
    
    
}
