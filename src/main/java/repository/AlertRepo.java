/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Alert;
import domain.User;
import java.io.IOException;
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
   public DefaultTableModel getTableModel(String role) throws IOException {
        String[] columnNames = {"", "From", "Title", ""};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (Alert i : getByReciverRole(role)) {
            User u = new UserRepo().getById(i.getSender());
            
            model.addRow(new Object[]{
                i.getId(),
                u.getUserName(),         // From
                i.getTitle(),        // Title
                "View"
            });
        }
        System.out.println("Rows added to table model: " + model.getRowCount());
        return model;
    }
    
    public List<Alert> getByReciverRole(String role) throws IOException {
        List<Alert> list = new ArrayList<>();

        for (Alert a : getAll()) {
            if ("read".equals(a.getStatus())){
                continue;
            }
            
            if ("AD".equals(role) || a.getReciverRole().equals(role)) {
                list.add(a);
            }
        }
//        System.out.println("Role: " + role + " | Matching alerts: " + list.size());

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
//        System.out.println(line);
        
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
