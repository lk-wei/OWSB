/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

/**
 *
 * @author zuwei
 */
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class ItemFunction {

    private final Path filePath = Path.of("database/item.txt");

    public void addNewItem(String itemCode, String itemName, int currentStock, int minStock, double unitCost) {
        try {
            IdGenerator ig = new IdGenerator(filePath);
            long itemId = ig.getNewId(); // Generate ID from file

            String formattedItem = formatItemData(itemId, itemCode, itemName, currentStock, minStock, unitCost);

            // Append the line to the file
            FileWriter writer = new FileWriter(filePath.toFile(), true);
            writer.write(formattedItem + System.lineSeparator());
            writer.close();

        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }

    private String formatItemData(long itemId, String itemCode, String itemName, int currentStock, int minStock, double unitCost) {
        return itemId + "|" + itemCode + "|" + itemName + "|" + currentStock + "|" + minStock + "|" + unitCost;
    }
}






