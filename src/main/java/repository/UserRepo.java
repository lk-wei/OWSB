/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.User;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kang Wei
 */
public class UserRepo {
    // define the txt file that stores data
    final private Path filePath = Path.of("user.txt");
    
    public UserRepo(){
       
    }
    
    // create
    public void createUser(User user) throws IOException{
         List<String> lines = Files.readAllLines(filePath);
         lines.add(objectToString(user));
         
         Files.write(filePath, lines);
    }
    
    // read
    
    public List<User> getAllUser() throws IOException{
        List<User> userList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
        
        for(String line : lines){
            User u = stringToObject(line);
            userList.add(u);
        }
        return userList;
    }
    
    public User getUserById(String id) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
   
        for(String line : lines){
            User u = stringToObject(line);
            
            if(u.getUserId().equals(id)){
                return u;
            }
        }
        return null;
    }
    
    public User getUserByUsername(String username) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
   
        for(String line : lines){
            User u = stringToObject(line);
            
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }
    
    // update
    public void updateUser(User user) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            User u = stringToObject(line);
            
            if(u.getUserId().equals(user.getUserId())){
                updatedLines.add(objectToString(user));
            }else{
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // delete
    public void deleteUser(User user) throws IOException{
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
   
        for(String line : lines){
            User u = stringToObject(line);
            
            if(!u.getUserId().equals(user.getUserId())){
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
    // others
    
    // converts to attribute ser intoder to store in text file
    private String objectToString(User user){
        return String.join("|",
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }
    
    private User stringToObject(String line) {
        String[] parts = line.split("\\|");
        return new User(
            parts[0], // id
            parts[1], // username
            parts[2], // password hash
            parts[3] // role
        );
    }
}
