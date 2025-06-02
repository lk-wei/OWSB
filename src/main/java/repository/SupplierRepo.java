/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Item;
import domain.ItemSupplier;
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
public class SupplierRepo extends MasterRepo<Supplier>{
 public SupplierRepo() {
        super(Path.of("database/supplier.txt"));
    }

    public Supplier getSupplierById(Long supplierId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            Supplier s = stringToObject(line);

            if (s.getId().equals(supplierId)) {
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
            new String[]{"", "Code", "Name", "Email", "Phone", ""}
        );

        List<Supplier> suppliers = getAll(); 
            

        for (Supplier supplier : suppliers) {
            model.addRow(new Object[]{
                supplier.getId(),
                supplier.getSupplierCode(),
                supplier.getSuppliername(),
                supplier.getEmail(),
                supplier.getPhone(),
                "View"   //  Action
            });
        }
        return model;
    }

    // others
    // converts to attribute ser intoder to store in text file
    @Override
    protected String objectToString(Supplier supplier) {
        return String.join("|",
                supplier.getId().toString(),
                supplier.getSupplierCode(),
                supplier.getSuppliername(),
                supplier.getEmail(),
                supplier.getPhone()
        );
    }

    @Override
    protected Supplier stringToObject(String line) {
        
        ItemSupplierRepo itemSupplierRepo = new ItemSupplierRepo();
        String[] parts = line.split("\\|", -1); // -1 keeps empty values
        
        return new Supplier(
                Long.valueOf(parts[0]), // userId
                parts[1], // userName
                parts[2], // password
                parts[3], // fullName
                parts[4],
                new ArrayList<>()

        );
    }
    
    // load payments when needed
    public List<Item> getItemsForSupplier(Long supplierId) throws IOException {
        ItemSupplierRepo isr = new ItemSupplierRepo();
        ItemRepo ir = new ItemRepo();
//        Payment payment = new Payment();
        List<Item> items = new ArrayList<>();
        
        for (ItemSupplier item : isr.getBySupplierId(supplierId)) {
            items.add(ir.getById(item.getItemId()));
        }
        return items;
    }

}
