/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.DailySale;
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
public class DailySaleRepo {
    
    // define the txt file that stores data
    final private Path filePath = Path.of("database/dailySale.txt");

    public DailySaleRepo() {
    }
    
    // create data and write into txt file
    public void createDailySales(DailySale dailySale) throws IOException{
         List<String> lines = Files.readAllLines(filePath);
         lines.add(objectToString(dailySale));
         
         Files.write(filePath, lines);
    }
    
    // read all lines
    public List<DailySale> getAllDailySale() throws IOException{
        List<DailySale> DailySaleList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
        
        for(String line : lines){
            DailySale ds = stringToObject(line);
            DailySaleList.add(ds);
        }
        return DailySaleList;
    }
    
    // match id get with the id stored in txt file
    public List<DailySale> getByDailySaleId(Long dsid) throws IOException{
        List<DailySale> DailySaleList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
   
        for(String line : lines){
            DailySale ds = stringToObject(line);
            
            if(Objects.equals(ds.getSaleId(), dsid)){
                DailySaleList.add(ds);
            }
        }
        return DailySaleList;
    }
    
    // update
    public void updateDailySale(DailySale dailySale) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            DailySale ds = stringToObject(line);
            
            if(ds.getSaleId().equals(dailySale.getSaleId())){
                updatedLines.add(objectToString(dailySale));
            }else{
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // delete
    public void deleteDailySale(DailySale dailySale) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            DailySale ds = stringToObject(line);
            
            if(!ds.getSaleId().equals(dailySale.getSaleId())){
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // convert object into string seperated by |
    private String objectToString(DailySale ds) {
        return String.join("|",
            ds.getSaleId().toString(),
            ds.getSaleCode().toString(),
            ds.getItemId().toString(),
            ds.getSaleDate().toString(),
            Integer.toString(ds.getQuantitySold()),
            ds.getRecordedById().toString()
        );
    }
    
    // convert string with | into object
    private DailySale stringToObject(String line) {
        String[] parts = line.split("\\|", -1); // 

        return new DailySale(
            Long.valueOf(parts[0]), // saleId
            parts[1],// saleCode
            Long.valueOf(parts[2]), // itemId
            LocalDate.parse(parts[3]), //saleDate
            Integer.parseInt(parts[4]), // quantitySold
            Long.valueOf(parts[5])// recordedBy
        );
    }
}
