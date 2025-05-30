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
public class Authenticate {
    public static boolean login(String username, String password) {
        try {
            UserRepo userRep = new UserRepo();
            User u = userRep.getUserByUsername(username); // Fetch user by username

            // Handle case where user is not found (u is null)
            if (u == null) {
                System.out.println("User not found!");
                return false;
            }
            
            if(u.getPassword().equals(password)){
                UserSession.getInstance().setCurrentUser(u);
                return true;
            }else{
                System.out.println("Incorrect passowrd!");
                return false;
            }
            
        } catch (IOException e) {
            System.out.println("An error occurred while accessing the user repository: " + e.getMessage());
            return false;

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }
}

