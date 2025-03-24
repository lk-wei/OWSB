/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

import domain.*;
import java.io.IOException;
import repository.*;

/**
 *
 * @author Kang Wei
 */
public class Login {
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public boolean authenticate() throws IOException{
        UserRepo userRep = new UserRepo("user.txt");
        User u = userRep.getUserByUsername(username);
        
        return u.getPassword().equals(password);
    }
}
