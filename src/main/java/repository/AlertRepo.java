/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Alert;
import java.nio.file.Path;

/**
 *
 * @author Kang Wei
 */
public class AlertRepo extends MasterRepo<Alert>{

    public AlertRepo(Path filePath) {
        super(filePath);
    }
    
    // Custom Method
    
    
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
