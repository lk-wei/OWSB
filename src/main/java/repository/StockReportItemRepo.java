/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;



import domain.Item;
import domain.ItemSupplier;
import domain.StockReportItem;
import domain.Supplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author See Kai Yang
 */
public class StockReportItemRepo extends MasterRepo<StockReportItem>{
    public StockReportItemRepo() {
        super(Path.of("database/stockReportItem.txt"));
    }

    // Read by ID
    public StockReportItem getStockReportItemById(long itemId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            StockReportItem item = stringToObject(line);
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }
    
    // Read by Financial Report ID
     public List<StockReportItem> getByStockReportId(Long financialReportId) throws IOException {
        List<StockReportItem> itemList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            StockReportItem item = stringToObject(line);
            if (item.getStockReportId() == financialReportId) {
                itemList.add(item);
            }
        }
        return itemList;
    }
     
     // UI
    public DefaultTableModel getTableModel(Long id) throws IOException {
        String[] columnNames = {"", "Item Code", "Name", "Stock On Record", "Minimum Stock", "Unit Cost", "Details"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (StockReportItem sri : getByStockReportId(id)) {
            Item i = new ItemRepo().getById(sri.getItemId());
            int minStock = 0;
            double unitCost = 0;
            
            // incase the item is deleted
            if (i != null) {
                minStock = i.getMinStock();
                unitCost = i.getUnitCost();
            }
            
            model.addRow(new Object[]{
                i.getId(),
                sri.getItemCode(),
                sri.getItemName(),
                sri.getStockLevel(),
                minStock,
                unitCost,
                "Details" // Action 
            });
        }
        return model;
    }
     
    // Convert object to string for file storage
    @Override
    protected String objectToString(StockReportItem item) {
        return String.join("|",
                String.valueOf(item.getId()),
                String.valueOf(item.getStockReportId()),
                String.valueOf(item.getItemId()),
                item.getItemCode(),
                item.getItemName(),
                String.valueOf(item.getStockLevel())
        );
    }

    // Convert string to object
    @Override
    protected StockReportItem stringToObject(String line) {
        String[] parts = line.split("\\|", -1);
        
        return new StockReportItem(
            Long.valueOf(parts[0]),
            Long.valueOf(parts[1]),
            Long.valueOf(parts[2]),
            parts[3],   
            parts[4],
            Integer.parseInt(parts[5])
        );
    }
    
    
    
    
}
