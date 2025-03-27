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

/**
 *
 * @author zuwei
 */
public class SupplierRepo {

    // define the txt file that stores data
    final private Path filePath = Path.of("supplier.txt");

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
                supplier.getSuppliername(),
                supplier.getEmail(),
                supplier.getPhone()
        );
    }

    private Supplier stringToObject(String line) {
        String[] parts = line.split("\\|");
        return new Supplier(
                Long.valueOf(parts[0]), // userId
                parts[1], // userName
                parts[2], // password
                parts[3] // fullName

        );
    }

}
