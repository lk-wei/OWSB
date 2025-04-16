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
