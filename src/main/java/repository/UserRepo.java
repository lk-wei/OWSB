/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.User;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kang Wei
 */
public class UserRepo extends MasterRepo<User>{
    public UserRepo(){
       super(Path.of("database/user.txt"));
    }
    
    public User getUserById(Long id) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
   
        for(String line : lines){
            User u = stringToObject(line);
            
            if(u.getId().equals(id)){
                return u;
            }
        }
        return null;
    }
    
    public User getUserByUsername(String username) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
   
        for(String line : lines){
            User u = stringToObject(line);
            
            if(u.getUserName().equals(username)){
                return u;
            }
        }
        return null;
    }
    
    // UI method
    public DefaultTableModel getTableModel() throws IOException {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            // These column names must match what's in your JFrame
            new String[]{"", "Username", "Full Name", "Role", ""}
        );

        List<User> users = getAll(); 
            

        for (User user : users) {
            model.addRow(new Object[]{
                user.getId(),
                user.getUserName(),    
                user.getFullName(),   
                user.getRole(), 
                "View"   // Action
            });
        }
        return model;
    }
    
    public String getRoleCode(String role) {
        switch(role) {
            case "SalesManager":
                return "SM";
            case "PurchaseManager":
                return "PM";
            case "Administrator":
                return "AD";
            case "InventoryManager":
                return "IM";
            case "FinanceManager":
                return "FM";
            default:
                return "";
        }
    }
    
    public String getRoleByCode(String roleCode) {
        switch(roleCode) {
            case "SM": 
                return "SalesManager";
            case "PM": 
                return "PurchaseManager";
            case "AD": 
                return "Administrator";
            case "IM": 
                return "InventoryManager";
            case "FM": 
                return "FinanceManager";
            default: 
                return "";
        }
    }

   // Converts User object to pipe-delimited string
    @Override
    protected String objectToString(User user) {
        return String.join("|",
            user.getId().toString(),
            user.getUserName(),
            user.getPassword(),
            user.getFullName(),
            user.getRole()
        );
    }

    // Converts pipe-delimited string back to User object
    @Override
    protected User stringToObject(String line) {
        String[] parts = line.split("\\|", 5);

        return new User(
            Long.valueOf(parts[0]),  // userId
            parts[1],                  // userName
            parts[2],                  // password
            parts[3],                  // fullName
            parts[4]                   // role
        );
    }
}
