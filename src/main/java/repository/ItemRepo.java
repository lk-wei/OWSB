/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Item;
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
public class ItemRepo extends MasterRepo<Item>{
    // define the txt file that stores data

    public ItemRepo() {
        super(Path.of("database/item.txt"));
    }

    //custom method
    public Item getItemByItemName(String itemName) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            Item i = stringToObject(line);

            if (i.getItemName().equals(itemName)) {
                return i;
            }
        }
        return null;
    }
    
    public List<Item> getLowStock() throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<Item> itemList = new ArrayList<>();

        for (String line : lines) {
            Item i = stringToObject(line);

            if (i.getCurrentStock() < i.getMinStock()) {
                itemList.add(i);
            }
        }
        return itemList;
    }

    public DefaultTableModel getTableModel() throws IOException {

        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                // These column names must match what's in your JFrame
                new String[]{"", "Item Code", "Item Name", "Current Stock", "Min Stock", "Unit Cost", ""}
        );
        List<Item> items = getAll();

        for (Item item : items) {
            model.addRow(new Object[]{
                item.getId(),  // id hidden
                item.getItemCode(), //itemCode
                item.getItemName(), //ItemName
                item.getCurrentStock(), //currentStock
                item.getMinStock(), //minStock
                item.getUnitCost(),
                "View"}); //unit Cost
        }
        return model;
    }
    
    public DefaultTableModel getLowStockTableModel() throws IOException {

        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                // These column names must match what's in your JFrame
                new String[]{"","Item Code", "Item Name", "Current Stock", "Min Stock", "Unit Cost", "Details"}
        );

        for (Item item : getLowStock()) {
            model.addRow(new Object[]{
                item.getId(), // id hidden
                item.getItemCode(), //itemCode
                item.getItemName(), //ItemName
                item.getCurrentStock(), //currentStock
                item.getMinStock(), //minStock
                item.getUnitCost(),
                "Details"}); //unit Cost
        }
        return model;
    }
    
    // Implement required abstract methods
    @Override
    protected String objectToString(Item item) {
        return String.join("|",
                item.getId().toString(),
                item.getItemCode(),
                item.getItemName(),
                Integer.toString(item.getCurrentStock()),
                Integer.toString(item.getMinStock()),
                Double.toString(item.getUnitCost())
        );

    }

    @Override
    protected Item stringToObject(String line) {
        String[] parts = line.split("\\|");
        return new Item(
                Long.valueOf(parts[0]), // itemId
                parts[1], // itemCode
                parts[2], // itemName
                Integer.parseInt(parts[3]), // currentStock
                Integer.parseInt(parts[4]), // minStock
                Double.parseDouble(parts[5]) // unitCost
        );
       
    }

}
