/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Alert;
import domain.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kang Wei
 */
public class AlertRepo extends MasterRepo<Alert>{

    public AlertRepo() {
        super(Path.of("database/alert.txt"));
    }
    
    // Custom Method
    // UI method
    public DefaultTableModel getTableModel(String currentUserRole) throws IOException {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            // These column names must match what's in your JFrame
            new String[]{"From", "Title", ""}
        );

        List<Alert> alerts = getByReciverRole(currentUserRole); 
        UserRepo userRepo = new UserRepo();    

        for (Alert alert : alerts) {
            User sender = userRepo.getUserById(alert.getSender());
            
            model.addRow(new Object[]{
                sender.getUserName(),    // Sender Name
                alert.getTitle(),   // Title
                ""                        // Empty column (action buttons?)
            });
        }
        return model;
    }
    
    public List<Alert> getByReciverRole(String role) throws IOException{
        List<Alert> list = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            Alert alert = stringToObject(line);
            
            if(alert.getReciverRole().equals(role) || "AD".equals(role)){
                 list.add(alert);
            }
            
            
        }
        return list;
    }
    
    // Implement required abstract methods
    @Override
    protected String objectToString(Alert alert) {
        return String.join("|",
            alert.getId().toString(),
            alert.getSender().toString(),
            alert.getReciverRole(),
            alert.getTitle(),
            alert.getMessage(),
            alert.getStatus()
        );
    }

    @Override
    protected Alert stringToObject(String line) {
        String[] parts = line.split("\\|", -1);
        
        return new Alert(
                Long.valueOf(parts[0]),
                Long.valueOf(parts[1]),
                parts[2],
                parts[3],
                parts[4],
                parts[5]
        );
    }
    
}
