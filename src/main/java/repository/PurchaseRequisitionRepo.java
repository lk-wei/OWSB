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
    public DefaultTableModel getTableModel() throws IOException {
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "",
                    "Purchase Requisition Code", 
                    "Requested By", 
                    "Request Date", 
                    "Required Date", 
                    "Status", 
                    "View"
                }
            );

            List<PurchaseRequisition> reports = getAll();
            UserRepo userRepo = new UserRepo();

            for (PurchaseRequisition report : reports) {
                User user = new UserRepo().getById(report.getRequestedById());

                model.addRow(new Object[]{
                        report.getId().toString(),
                        report.getPurchaseRequisitionCode(),
                        user.getFullName(),
                        report.getRequestDate(),
                        report.getRequiredDate(),
                        report.getStatus(),
                        "View"
                    });
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
