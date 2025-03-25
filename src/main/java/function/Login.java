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
    
    public boolean authenticate() {
        try {
            UserRepo userRep = new UserRepo();
            User u = userRep.getUserByUsername(username); // Fetch user by username

            // Handle case where user is not found (u is null)
            if (u == null) {
                System.out.println("User not found!");
                return false;  // Authentication failed if user is null
            }

            // Compare password
            return u.getPassword().equals(password);

        } catch (IOException e) {
            // Handle IO exceptions (e.g., file read issues)
            System.out.println("An error occurred while accessing the user repository: " + e.getMessage());
            return false;

        } catch (Exception e) {
            // Catch any other exceptions (e.g., unexpected errors)
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }
}
