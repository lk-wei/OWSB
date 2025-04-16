/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Item;
import domain.StockUpdate;
import domain.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CK
 */
public class StockUpdateRepo {
    // define the txt file that stores data
    final private Path filePath = Path.of("database/stockUpdate.txt");

    public StockUpdateRepo() {
    }
    
    // create data and write into txt file
    public void createStockUpdate(StockUpdate stockUpdate) throws IOException{
         List<String> lines = Files.readAllLines(filePath);
         lines.add(objectToString(stockUpdate));
         
         Files.write(filePath, lines);
    }
    
    // read all lines
    public List<StockUpdate> getAllStockUpdate() throws IOException{
        List<StockUpdate> StockUpdateList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
        
        for(String line : lines){
            StockUpdate su = stringToObject(line);
            StockUpdateList.add(su);
        }
        return StockUpdateList;
    }
    
    // match id get with the id stored in txt file
    public StockUpdate getByStockUpdateId(Long suid) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
   
        for(String line : lines){
            StockUpdate su = stringToObject(line);
            
            if(Objects.equals(su.getStockUpdateId(), suid)){
                return su;
            }
        }
        return null;
    }
    
    // UI method
    public DefaultTableModel getTableModel() throws IOException {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            // These column names must match what's in your JFrame
            new String[]{"Stock Update Code", "Date", "Updated By", ""}
        );

        List<StockUpdate> stockupdates = getAllStockUpdate(); 
        UserRepo userRepo = new UserRepo();    
    
        for (StockUpdate stockupdate : stockupdates) {
            User user = userRepo.getUserById(stockupdate.getUpdatedById());
            
            model.addRow(new Object[]{
                stockupdate.getStockUpdateCode(),    // "Report Number"
                stockupdate.getDate(),   // "Description"
                user.getFullName(),    // "Created By"
                ""                        // Empty column (action buttons?)
            });
        }
        return model;
    }
    
    // updates
    public void updateStockUpdate(StockUpdate stockUpdate) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            StockUpdate su = stringToObject(line);
            
            if(su.getStockUpdateId().equals(stockUpdate.getStockUpdateId())){
                updatedLines.add(objectToString(stockUpdate));
            }else{
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // delete
    public void deleteStockUpdate(StockUpdate stockUpdate) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            StockUpdate su = stringToObject(line);
            
            if(!su.getStockUpdateId().equals(stockUpdate.getStockUpdateId())){
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // convert object into string seperated by |
    private String objectToString(StockUpdate su) {
        return String.join("|",
            su.getStockUpdateId().toString(),
            su.getStockUpdateCode().toString(),
            su.getDescription().toString(),
            su.getItemId().toString(),
            Integer.toString(su.getQuantity()),
            su.getDate().toString(),
            su.getUpdatedById().toString()
        );
    }
    
    // convert string with | into object
    private StockUpdate stringToObject(String line) throws IOException {
        String[] parts = line.split("\\|", -1); // 

        return new StockUpdate(
            Long.valueOf(parts[0]), // stockUpdateId
            parts[1], // stockUpdateCode
            parts[2], // description
            Long.valueOf(parts[3]), // itemId
            Integer.parseInt(parts[4]), // quantity
            LocalDate.parse(parts[5]), //date
            Long.valueOf(parts[6]),//updatedById
//            getItemList(Long.parseLong(parts[0])) // Problem here !!
            null
        );
    }
    
    private List<Item> getItemList(Long stockUpdateId) throws IOException{
        StockUpdateRepo suRepo = new StockUpdateRepo();
        List<StockUpdate> suList = suRepo.getByStockUpdateId(stockUpdateId);
        
        ItemRepo itemRepo = new ItemRepo();
        List<Item> itemList = new ArrayList<>();
        
        for(StockUpdate su : suList){
            itemList.add(itemRepo.getItemById(su.getItemId()));
        }
        
        return itemList;
    }
}
