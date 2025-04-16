/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Item;
import function.IdGenerator;
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

    // custom method
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

    public DefaultTableModel getTableModel() throws IOException {

        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                // These column names must match what's in your JFrame
                new String[]{"Item Code", "Item Name", "Current Stock", "Min Stock", "Unit Cost", ""}
        );
        List<Item> items = getAll();

        for (Item item : items) {
            model.addRow(new Object[]{
                item.getItemCode(), //itemCode
                item.getItemName(), //ItemName
                item.getCurrentStock(), //currentStock
                item.getMinStock(), //minStock
                item.getUnitCost(),
                ""}); //unit Cost
        }
        return model;
    }
    
    // Implement required abstract methods
    @Override
    protected Long getId(Item entity) {
        return entity.getItemId();
    }
    
    @Override
    protected void setId(Item entity, long id) {
        entity.setItemId(id);
    }

    @Override
    protected String objectToString(Item item) {
        return String.join("|",
                item.getItemId().toString(),
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
