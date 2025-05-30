/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

import domain.User;

/**
 *
 * @author Kang Wei
 */
public class UserSession {
    private static UserSession instance;
    private User currentUser;
    
    private UserSession(){
        
    }
    
    public static synchronized UserSession getInstance(){
        if(instance == null){
            instance = new UserSession();
        }
        return instance;
    }
    
    public void setCurrentUser(User user){
        this.currentUser = user;
    }
    
    public User getCurrentUser(){
        return this.currentUser;
    }
    
    public void clearSession() {
        this.currentUser = null;
    }
    
    public boolean isLoggedIn() {
        return this.currentUser != null;
    }
}
