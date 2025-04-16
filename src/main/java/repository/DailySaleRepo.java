/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.DailySale;
import domain.User;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CK
 */
public class DailySaleRepo extends MasterRepo<DailySale>{
    public DailySaleRepo() {
        super(Path.of("database/dailySale.txt"));
    }
    
    
    // UI method
    public DefaultTableModel getTableModel() throws IOException {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            // These column names must match what's in your JFrame
            new String[]{"Sale Code", "Sale Date", "Recorded By", ""}
        );

        List<DailySale> dailysales = getAll(); 
        UserRepo userRepo = new UserRepo();    

        for (DailySale dailysale : dailysales) {
            User user = userRepo.getUserById(dailysale.getRecordedById());
            
            model.addRow(new Object[]{
                dailysale.getSaleCode(),    // Sale Code
                dailysale.getSaleDate(),   // Sale Date
                user.getFullName(),  // Recorded By
                ""                        // Empty column (action buttons?)
            });
        }
        return model;
    }
    
    // convert object into string seperated by |
    @Override
    protected String objectToString(DailySale ds) {
        return String.join("|",
            ds.getId().toString(),
            ds.getSaleCode(),
            ds.getItemId().toString(),
            ds.getSaleDate().toString(),
            Integer.toString(ds.getQuantitySold()),
            ds.getRecordedById().toString()
        );
    }
    
    // convert string with | into object
    @Override
    protected DailySale stringToObject(String line) {
        String[] parts = line.split("\\|", -1); // 

        return new DailySale(
            Long.valueOf(parts[0]), // saleId
            parts[1],// saleCode
            Long.valueOf(parts[2]), // itemId
            LocalDate.parse(parts[3]), //saleDate
            Integer.parseInt(parts[4]), // quantitySold
            Long.valueOf(parts[5]),// recordedBy
//            getItemList(Long.parseLong(parts[0])) // Problem here !!!
            null
        );
    }
    
//    private List<Item> getItemList(Long saleId) throws IOException{
//        DailySaleRepo dsRepo = new DailySaleRepo();
//        List<DailySale> dsList = dsRepo.getByDailySaleId(saleId);
////        List<DailySale> dsList = getByDailySaleId(saleId); // Problem here !!!
//
//        ItemRepo itemRepo = new ItemRepo();
//        List<Item> itemList = new ArrayList<>();
//        
//        for(DailySale ds : dsList){
//            itemList.add(itemRepo.getItemById(ds.getItemId()));
//        }
//        return itemList;
//    }
    
}
