/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import domain.Alert;
import java.io.IOException;
import java.util.List;
import repository.AlertRepo;

/**
 *
 * @author Kang Wei
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
//        new Login().setVisible(true);

        List<Alert> aList = new AlertRepo().getAll();
        
        for (Alert i : aList){
            System.out.println(i.getMessage());
        }
    }
    
}
