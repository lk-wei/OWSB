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
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author See Kai Yang
 */
public class PurchaseRequisitionRepo {
    
     private final Path filePath = Path.of("purchaseRequisition.txt");

    public PurchaseRequisitionRepo() {
    }

    // Create
    public void createPurchaseRequisition(PurchaseRequisition pr) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        lines.add(objectToString(pr));
        Files.write(filePath, lines);
    }

    // Read all
    public List<PurchaseRequisition> getAllPurchaseRequisitions() throws IOException {
        List<PurchaseRequisition> prList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            prList.add(stringToObject(line));
        }
        return prList;
    }

    // Read by ID
    public PurchaseRequisition getPurchaseRequisitionById(long prId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            PurchaseRequisition pr = stringToObject(line);
            if (pr.getPurchaseRequisitionID() == prId) {
                return pr;
            }
        }
        return null;
    }

    // Update
    public void updatePurchaseRequisition(PurchaseRequisition pr) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            PurchaseRequisition existingPR = stringToObject(line);
            if (existingPR.getPurchaseRequisitionID() == pr.getPurchaseRequisitionID()) {
                updatedLines.add(objectToString(pr));
            } else {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // Delete
    public void deletePurchaseRequisition(long prId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            PurchaseRequisition pr = stringToObject(line);
            if (pr.getPurchaseRequisitionID() != prId) {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // Convert object to string for file storage
    private String objectToString(PurchaseRequisition pr) {
        return String.join(",",
                String.valueOf(pr.getPurchaseRequisitionID()),
                String.valueOf(pr.getRequestById()),
                pr.getRequestDate().toString(),
                pr.getRequiredDate().toString(),
                pr.getStatus().name()
        );
    }

    // Convert string to object
    private PurchaseRequisition stringToObject(String line) {
        String[] parts = line.split(",");
        PurchaseRequisition pr = new PurchaseRequisition();
        pr.setPurchaseRequisitionID(Long.parseLong(parts[0]));
        pr.setRequestById(Long.parseLong(parts[1]));
        pr.setRequestDate(LocalDate.parse(parts[2]));
        pr.setRequiredDate(LocalDate.parse(parts[3]));
        pr.setStatus(Status.valueOf(parts[4]));
        return pr;
    }
    
    
    
}
