/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

import domain.DailySale;
import repository.ItemRepo;
import domain.Item;
import domain.StockUpdate;
import java.io.IOException;
import repository.DailySaleRepo;
import repository.StockUpdateRepo;

/**
 *
 * @author User
 */
public class ManageStock {
    // Deduct stock quantity when create new daily sale
    public void deductStockQuantity(Item item, int saleQuantity) throws IOException {
        ItemRepo ir = new ItemRepo();
        
        if (item == null) {
            System.out.println("Item not found for itemId: " + item.getId());
            return;
        }

        // Calculate new quantity after sale
        int updatedQuantity = item.getCurrentStock() - saleQuantity;

        // Create updated Item object with new quantity
        Item updatedItem = new Item(
            item.getId(),
            item.getItemCode(),
            item.getItemName(),
            updatedQuantity,
            item.getMinStock(),
            item.getUnitCost()
        );

        ir.update(updatedItem);
    }
    
    // Add stock quantity when delete daily sale
    public void addStockQuantity(Long saleId) throws IOException {
        ItemRepo ir = new ItemRepo();
        DailySaleRepo dsr = new DailySaleRepo();
                
        DailySale sale = dsr.getById(saleId);
        int saleQuantity = sale.getQuantitySold();
        
        Item item = ir.getById(sale.getItemId());
        
        // Calculate new quantity after delete daily sale
        int updatedQuantity = item.getCurrentStock() + saleQuantity;

        // Create updated Item object with new quantity
        Item updatedItem = new Item(
            item.getId(),
            item.getItemCode(),
            item.getItemName(),
            updatedQuantity,
            item.getMinStock(),
            item.getUnitCost()
        );

        ir.update(updatedItem);
    }
    
    // Update stock quantity when edit daily sale
    public void editStockQuantity(Long viewId, int newQuantity) throws IOException{
        ItemRepo ir = new ItemRepo();
        DailySaleRepo dsr = new DailySaleRepo();
        
        DailySale sale = dsr.getById(viewId);
        int currentSaleQuantity = sale.getQuantitySold();
        
        Item item = ir.getById(sale.getItemId());
        int currentStockQuantity = item.getCurrentStock();
        
        int updatedQuantity = currentStockQuantity + currentSaleQuantity - newQuantity;
        
        // Create updated Item object with new quantity
        Item updatedItem = new Item(
            item.getId(),
            item.getItemCode(),
            item.getItemName(),
            updatedQuantity,
            item.getMinStock(),
            item.getUnitCost()
        );

        ir.update(updatedItem);
        
    }
    
    // Add stock when create new stock update
    public void addStockUpdateQuantity(Item item, int updateStockQuantity) throws IOException {
        ItemRepo ir = new ItemRepo();
        
        if (item == null) {
            System.out.println("Item not found for itemId: " + item.getId());
            return;
        }

        // Calculate new stock quantity after create new stock update
        int updatedQuantity = item.getCurrentStock() + updateStockQuantity;

        // Create updated Item object with new quantity
        Item updatedItem = new Item(
            item.getId(),
            item.getItemCode(),
            item.getItemName(),
            updatedQuantity,
            item.getMinStock(),
            item.getUnitCost()
        );

        ir.update(updatedItem);
    }
    
    // Remove Stock Update Quantity
    public void removeStockUpdateQuantity(Long update) throws IOException {
        ItemRepo ir = new ItemRepo();
        StockUpdateRepo sur = new StockUpdateRepo();
                
        StockUpdate su = sur.getById(update);
        int stockUpdateQuantity = su.getQuantity();
        
        Item item = ir.getById(su.getItemId());
        
        // Calculate new quantity after sale
        int updatedQuantity = item.getCurrentStock() - stockUpdateQuantity;

        // Create updated Item object with new quantity
        Item updatedItem = new Item(
            item.getId(),
            item.getItemCode(),
            item.getItemName(),
            updatedQuantity,
            item.getMinStock(),
            item.getUnitCost()
        );

        ir.update(updatedItem);
    }
    
    // Edit Stock Update Quantity
    public void editStockUpdateQuantity(Long viewId, int newStockUpdateQuantity) throws IOException{
        ItemRepo ir = new ItemRepo();
        StockUpdateRepo sur = new StockUpdateRepo();
        
        StockUpdate update = sur.getById(viewId);
        int currentStockUpdateQuantity = update.getQuantity();
        
        Item item = ir.getById(update.getItemId());
        int currentStockQuantity = item.getCurrentStock();
        
        int updatedQuantity = currentStockQuantity - currentStockUpdateQuantity + newStockUpdateQuantity;
        
        // Create updated Item object with new quantity
        Item updatedItem = new Item(
            item.getId(),
            item.getItemCode(),
            item.getItemName(),
            updatedQuantity,
            item.getMinStock(),
            item.getUnitCost()
        );

        ir.update(updatedItem);
        
    }
    
}
