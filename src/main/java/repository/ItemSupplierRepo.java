/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.ItemSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author CK
 */
public class ItemSupplierRepo {
    
    // define the txt file that stores data
    final private Path filePath = Path.of("database/itemSupplier.txt");

    public ItemSupplierRepo() {
    }
    
    // create data and write into txt file
    public void createItemSupplier(ItemSupplier itemSupplier) throws IOException{
         List<String> lines = Files.readAllLines(filePath);
         lines.add(objectToString(itemSupplier));
         
         Files.write(filePath, lines);
    }
    
    // read all iines
    public List<ItemSupplier> getAllItemSupplier() throws IOException{
        List<ItemSupplier> ItemSupplierList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
        
        for(String line : lines){
            ItemSupplier is = stringToObject(line);
            ItemSupplierList.add(is);
        }
        return ItemSupplierList;
    }
    
    // match id get with the id stored in txt file
    public List<ItemSupplier> getByItemSupplierId(Long isid) throws IOException{
        List<ItemSupplier> ItemSupplierList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
   
        for(String line : lines){
            ItemSupplier is = stringToObject(line);
            
            if(Objects.equals(is.getItemSupplierId(), isid)){
                ItemSupplierList.add(is);
            }
        }
        return ItemSupplierList;
    }
    
    // update
    public void updateItemSupplier(ItemSupplier itemSupplier) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            ItemSupplier is = stringToObject(line);
            
            if(is.getItemSupplierId().equals(itemSupplier.getItemSupplierId())){
                updatedLines.add(objectToString(itemSupplier));
            }else{
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // delete
    public void deleteItemSupplier(ItemSupplier itemSupplier) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            ItemSupplier is = stringToObject(line);
            
            if(!is.getItemSupplierId().equals(itemSupplier.getItemSupplierId())){
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // convert object into string seperated by |
    private String objectToString(ItemSupplier is) {
        return String.join("|",
            is.getItemSupplierId().toString(),
            is.getItemId().toString(),
            is.getSupplierId().toString()
        );
    }
    
    // convert string with | into object
    private ItemSupplier stringToObject(String line) {
        String[] parts = line.split("\\|", -1);

        return new ItemSupplier(
            Long.valueOf(parts[0]), // itemSupplierId
            Long.valueOf(parts[1]), // itemId
            Long.valueOf(parts[2]) // supplierId
        );
    }
}
