/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.StockUpdate;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            
            if(su.getStockUpdateId() == suid){
                return su;
            }
        }
        return null;
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
    private StockUpdate stringToObject(String line) {
        String[] parts = line.split("\\|", -1); // 

        return new StockUpdate(
            Long.valueOf(parts[0]), // stockUpdateId
            parts[1], // stockUpdateCode
            parts[2], // description
            Long.valueOf(parts[3]), // itemId
            Integer.parseInt(parts[4]), // quantity
            LocalDate.parse(parts[5]), //date
            Long.valueOf(parts[6])// updatedById
        );
    }
}
