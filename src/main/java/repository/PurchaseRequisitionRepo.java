/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import domain.PurchaseRequisition;
import domain.PurchaseRequisition.Status;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
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

    // Convert object to string for file storage
    @Override
    protected String objectToString(PurchaseRequisition pr) {
        return String.join(",",
                String.valueOf(pr.getId()),
                String.valueOf(pr.getRequestById()),
                pr.getRequestDate().toString(),
                pr.getRequiredDate().toString(),
                pr.getStatus().name()
        );
    }

    // Convert string to object
    @Override
    protected PurchaseRequisition stringToObject(String line) {
        String[] parts = line.split(",");
        PurchaseRequisition pr = new PurchaseRequisition();
        pr.setId(Long.parseLong(parts[0]));
        pr.setRequestById(Long.valueOf(parts[1]));
        pr.setRequestDate(LocalDate.parse(parts[2]));
        pr.setRequiredDate(LocalDate.parse(parts[3]));
        pr.setStatus(Status.valueOf(parts[4]));
        return pr;
    }
    
    
    
}
