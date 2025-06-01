/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.StockUpdate;
import domain.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CK
 */
public class StockUpdateRepo extends MasterRepo<StockUpdate>{
    public StockUpdateRepo() {
        super(Path.of("database/stockUpdate.txt"));
    }
    
    // Needed?
    // match id get with the id stored in txt file
    public StockUpdate getByStockUpdateId(Long suid) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
   
        for(String line : lines){
            StockUpdate su = stringToObject(line);
            
            if(Objects.equals(su.getId(), suid)){
                return su;
            }
        }
        return null;
    }
    
    // UI method
    public DefaultTableModel getTableModel() throws IOException {
        
        String[] columnNames = {"", "Stock Update Code", "Date", "Updated By", ""};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (StockUpdate update : getAll()){
            User u = new UserRepo().getById(update.getUpdatedById());
            
            model.addRow(new Object[]{
                update.getId(),    
                update.getStockUpdateCode(),  
                update.getDate(),
                u.getFullName(),
                "View"  //Action                        
            });
        }
        return model;
    }
    
//   public DefaultTableModel getTableModel() throws IOException {
//        DefaultTableModel model = new DefaultTableModel(
//            new Object[][]{},
//            // These column names must match what's in your JFrame
//            new String[]{"Stock Update Code", "Date", "Updated By", ""}
//        );
//
//        List<StockUpdate> stockupdates = getAll(); 
//        UserRepo userRepo = new UserRepo();    
//
//        for (StockUpdate stockupdate : stockupdates) {
//            User user = userRepo.getById(stockupdate.getUpdatedById());
//            
//            model.addRow(new Object[]{
//                stockupdate.getStockUpdateCode(),   
//                stockupdate.getDate(),   
//                user != null ? user.getFullName() : "Unknown", // avoid null pointer
//                ""
//            });
//        }
//
//        return model;
//    }

    
    // convert object into string seperated by |
    @Override
    protected String objectToString(StockUpdate su) {
        return String.join("|",
            su.getId().toString(),
            su.getStockUpdateCode().toString(),
            su.getDescription().toString(),
            su.getItemId().toString(),
            Integer.toString(su.getQuantity()),
            su.getDate().toString(),
            su.getUpdatedById().toString()
        );
    }
    
    // convert string with | into object
    @Override
    protected StockUpdate stringToObject(String line) {
        String[] parts = line.split("\\|", -1); // 

        return new StockUpdate(
            Long.valueOf(parts[0]), // stockUpdateId
            parts[1], // stockUpdateCode
            parts[2], // description
            Long.valueOf(parts[3]), // itemId
            Integer.parseInt(parts[4]), // quantity
            LocalDate.parse(parts[5]), //date
            Long.valueOf(parts[6])//updatedById
        );
    }
    
//    private List<Item> getItemList(Long stockUpdateId) throws IOException{
//        StockUpdateRepo suRepo = new StockUpdateRepo();
//        List<StockUpdate> suList = suRepo.getByStockUpdateId(stockUpdateId);
//        
//        ItemRepo itemRepo = new ItemRepo();
//        List<Item> itemList = new ArrayList<>();
//        
//        for(StockUpdate su : suList){
//            itemList.add(itemRepo.getById(su.getItemId()));
//        }
//        
//        return itemList;
//    }
}
