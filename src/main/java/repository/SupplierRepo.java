/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Supplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zuwei
 */
public class SupplierRepo {

    // define the txt file that stores data
    final private Path filePath = Path.of("database/supplier.txt");

    public SupplierRepo() {

    }

    // create
    public void createSupplier(Supplier supplier) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        lines.add(objectToString(supplier));

        Files.write(filePath, lines);
    }

    // read
    public List<Supplier> getAllSupplier() throws IOException {
        List<Supplier> supplierList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            Supplier s = stringToObject(line);
            supplierList.add(s);
        }
        return supplierList;
    }

    public Supplier getSupplierById(String supplierId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            Supplier s = stringToObject(line);

            if (s.getSupplierId().equals(supplierId)) {
                return s;
            }
        }
        return null;
    }

    public Supplier getSupplierBySuppliername(String supplierName) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            Supplier s = stringToObject(line);

            if (s.getSuppliername().equals(supplierName)) {
                return s;
            }
        }
        return null;
    }
    
    // UI method
    public DefaultTableModel getTableModel() throws IOException {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            // These column names must match what's in your JFrame
            new String[]{"Code", "Name", "Email", "Phone", ""}
        );

        List<Supplier> suppliers = getAllSupplier(); 
            

        for (Supplier supplier : suppliers) {
            model.addRow(new Object[]{
                supplier.getSupplierCode(),
                supplier.getSuppliername(),
                supplier.getEmail(),
                supplier.getPhone(),
                ""                  // Empty column (action buttons?)
            });
        }
        return model;
    }

    // update
    public void updateSupplier(Supplier supplier) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            Supplier s = stringToObject(line);

            if (s.getSupplierId().equals(supplier.getSupplierId())) {
                updatedLines.add(objectToString(supplier));
            } else {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // delete
    public void deleteSupplier(Supplier supplier) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            Supplier s = stringToObject(line);

            if (!s.getSupplierId().equals(supplier.getSupplierId())) {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // others
    // converts to attribute ser intoder to store in text file
    private String objectToString(Supplier supplier) {
        return String.join("|",
                supplier.getSupplierId().toString(),
                supplier.getSupplierCode(),
                supplier.getSuppliername(),
                supplier.getEmail(),
                supplier.getPhone()
        );
    }

    private Supplier stringToObject(String line) throws IOException {
        
        ItemSupplierRepo itemSupplierRepo = new ItemSupplierRepo();
        String[] parts = line.split("\\|", -1); // -1 keeps empty values
        
        return new Supplier(
                Long.valueOf(parts[0]), // userId
                parts[1], // userName
                parts[2], // password
                parts[3], // fullName
                parts[4],
                itemSupplierRepo.getBySupplierId(Long.valueOf(parts[0]))

        );
    }

}
