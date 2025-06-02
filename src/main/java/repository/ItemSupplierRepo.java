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
import java.util.Objects;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CK
 */
public class ItemSupplierRepo extends MasterRepo<ItemSupplier>{
    public ItemSupplierRepo() {
        super(Path.of("database/itemSupplier.txt"));
    }
    
    // match id get with the id stored in txt file
    public List<ItemSupplier> getByItemSupplierId(Long isid) throws IOException{
        List<ItemSupplier> ItemSupplierList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
   
        for(String line : lines){
            ItemSupplier is = stringToObject(line);
            
            if(Objects.equals(is.getId(), isid)){
                ItemSupplierList.add(is);
            }
        }
        return ItemSupplierList;
    }
    
    public List<ItemSupplier> getBySupplierId(Long sid) throws IOException{
        List<ItemSupplier> ItemSupplierList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
   
        for(String line : lines){
            ItemSupplier is = stringToObject(line);
            
            if(Objects.equals(is.getSupplierId(), sid)){
                ItemSupplierList.add(is);
            }
        }
        return ItemSupplierList;
    }
    
    // for supplier deletion
    public void deleteBySupplierId(Long supplierId) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            ItemSupplier is = stringToObject(line);

            if (!Objects.equals(is.getSupplierId(), supplierId)) {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // for item deletion
    public void deleteByItemId(Long itemId) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            ItemSupplier is = stringToObject(line);

            if (!Objects.equals(is.getItemId(), itemId)) {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // UI
    public DefaultTableModel getSupplierTableModel(Long itemId) throws IOException {
        String[] columnNames = {"", "Report Number", "Description", "Date", "Created By", ""};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (ItemSupplier is : getBySupplierId(itemId)) {
            Supplier s = new SupplierRepo().getById(is.getItemId());
            
            model.addRow(new Object[]{
                s.getId(),
                s.getSupplierCode(),
                s.getSuppliername(),
                "delete" // Action 
            });
        }
        return model;
    }
    
    public DefaultTableModel getItemTableModel(Long supplierId) throws IOException {
        String[] columnNames = {"", "Item Code", "Item Name", ""};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (ItemSupplier is : getBySupplierId(supplierId)) {
            Item i = new ItemRepo().getById(is.getItemId());
            
            model.addRow(new Object[]{
                i.getId(),
                i.getItemCode(),
                i.getItemName(),
                "delete" // Action 
            });
        }
        return model;
    }
    
    // convert object into string seperated by |
    @Override
    protected String objectToString(ItemSupplier is) {
        return String.join("|",
            is.getId().toString(),
            is.getItemId().toString(),
            is.getSupplierId().toString()
        );
    }
    
    // convert string with | into object
    @Override
    protected ItemSupplier stringToObject(String line) {
        String[] parts = line.split("\\|", -1);

        return new ItemSupplier(
            Long.valueOf(parts[0]), // itemSupplierId
            Long.valueOf(parts[1]), // itemId
            Long.valueOf(parts[2]) // supplierId
        );
    }
}
