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

/**
 *
 * @author zuwei
 */
public class ItemRepo {
    // define the txt file that stores data

    final private Path filePath = Path.of("database/item.txt");

    // create
    public void createItem(Item item) throws IOException {
        
        IdGenerator ig = new IdGenerator(filePath);
        item.setItemId(ig.getId());
        List<String> lines = Files.readAllLines(filePath);
        lines.add(objectToString(item));

        Files.write(filePath, lines);
    }

    // read
    public List<Item> getAllItem() throws IOException {
        List<Item> itemList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            Item i = stringToObject(line);
            itemList.add(i);
        }
        return itemList;
    }

    public Item getItemById(String itemId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            Item i = stringToObject(line);

            if (i.getItemId().equals(itemId)) {
                return i;
            }
        }
        return null;
    }

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

    // update
    public void updateItem(Item item) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            Item i = stringToObject(line);

            if (i.getItemId().equals(item.getItemId())) {
                updatedLines.add(objectToString(item));
            } else {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // delete
    public void deleteItem(Item item) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            Item i = stringToObject(line);

            if (!i.getItemId().equals(item.getItemId())) {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    private String objectToString(Item item) {
        return String.join("|",
                item.getItemId().toString(),
                item.getItemCode(),
                item.getItemName(),
                
                Integer.toString(item.getCurrentStock()),
                Integer.toString(item.getMinStock()),
                Double.toString(item.getUnitCost())
        );

    }

    private Item stringToObject(String line) {
        String[] parts = line.split("\\|");
        return new Item(
                Long.valueOf(parts[0]),// id
                parts[1], // code
                parts[2], // name               
                Integer.parseInt(parts[3]),// currentStock
                Integer.parseInt(parts[4]),// min
                Double.parseDouble(parts[5])
        );
    }

}
