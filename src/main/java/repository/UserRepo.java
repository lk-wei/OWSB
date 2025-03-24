/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 *
 * @author Kang Wei
 */
public class UserRepo {
    final private Path filePath;
    
    public UserRepo(String filePath){
        this.filePath = Path.of(filePath);
    }
    
    // create
    public void createUser(User user) throws IOException{
         List<String> lines = Files.readAllLines(filePath);
         lines.add(objectToString(user));
         
         Files.write(filePath, lines);
    }
    
    // read
    
    
    // update
    
    
    // delete
    
    // others
    private String objectToString(User user){
        return String.join(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }
    
    private User stringToObject(String line){
        return new User()
    }
}
