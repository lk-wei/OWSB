/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

import domain.Alert;
import domain.Item;
import domain.PurchaseOrder;
import domain.PurchaseRequisition;
import domain.User; // To get sender ID if a User object is passed or from UserSession
import repository.AlertRepo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.ItemRepo;

/**
 *
 * @author Kang Wei
 */
public class AlertManager {
    
    private AlertManager() {}
    
    public static boolean sendAlert(Long senderId, String receiverRole, String title, String message) {
        // Basic validation for required fields
        if (senderId == null) {
            System.err.println("Alert creation failed: Sender ID cannot be null.");
            return false;
        }
        if (receiverRole == null || receiverRole.trim().isEmpty()) {
            System.err.println("Alert creation failed: Receiver role cannot be null or empty.");
            return false;
        }
        if (title == null || title.trim().isEmpty()) {
            System.err.println("Alert creation failed: Title cannot be null or empty.");
            return false;
        }
        if (message == null || message.trim().isEmpty()) {
            System.err.println("Alert creation failed: Message cannot be null or empty.");
            return false;
        }

        try {
            AlertRepo alertRepo = new AlertRepo();


            Alert newAlert = new Alert(null, senderId, receiverRole, title, message, "unread");

            alertRepo.create(newAlert); // This saves to alert.txt and sets the ID on the newAlert object

            System.out.println("Alert sent successfully: To Role [" + receiverRole + "] - Title: '" + title + "'");
            return true;

        } catch (IOException e) {
            Logger.getLogger(AlertManager.class.getName()).log(Level.SEVERE, "Failed to send alert due to IOException", e);

            return false;
        } catch (Exception e) {
            Logger.getLogger(AlertManager.class.getName()).log(Level.SEVERE, "Unexpected error while sending alert", e);
            return false;
        }
    }

    public static boolean sendAlert(String receiverRole, String title, String message) {
        User currentUser = UserSession.getInstance().getCurrentUser();

        if (currentUser == null) {
            System.err.println("Alert creation failed: No logged-in user found in session to act as sender.");
        
            return false;
        }
        if (currentUser.getId() == null) {
            System.err.println("Alert creation failed: Logged-in user has a null ID.");
            return false;
        }

        return sendAlert(currentUser.getId(), receiverRole, title, message);
    }


    public static void savePurchaseRequisition(PurchaseRequisition pr) {

        boolean alertSent = AlertManager.sendAlert(
            "PM",
            "New Purchase Requisition Created: " + pr.getPurchaseRequisitionCode(), // Alert Title
            "Purchase Requisition " + pr.getPurchaseRequisitionCode()+ " has been submitted and is awaiting your review for PO generation."
        );

        if (alertSent) {
            System.out.println("Notification sent to Purchase Manager for PR: " + pr.getPurchaseRequisitionCode());
        } else {
            System.err.println("Failed to send notification to Purchase Manager for PR: " + pr.getPurchaseRequisitionCode());
        }
    }
    
    public static void savePurchaseOrder(PurchaseOrder po) {

        boolean alertSent = AlertManager.sendAlert(
            "FM",
            "New Purchase Requisition Created: " + po.getPurchaseOrderCode(), // Alert Title
            "Purchase Requisition " + po.getPurchaseOrderCode()+ " has been submitted and is awaiting your review for PO generation."
        );

        if (alertSent) {
            System.out.println("Notification sent to Purchase Manager for PR: " + po.getPurchaseOrderCode());
        } else {
            System.err.println("Failed to send notification to Purchase Manager for PR: " + po.getPurchaseOrderCode());
        }
        

    }
    
    public static void triggerLowStock() throws IOException {
        List<Item> itemToCheck = new ItemRepo().getAll();
        List<Item> lowStockItem = new ArrayList<>();

        for (Item i : itemToCheck) {
            if (i.getCurrentStock() < i.getMinStock()) {
                lowStockItem.add(i);
            }
        }

        if (!lowStockItem.isEmpty()) {
            

            boolean alertSentIM = AlertManager.sendAlert(
                "IM",
                "Low Stock",
                "Please Check The Item Stocks Levels as there Items That need restocking"
            );
            
            boolean alertSentSM = AlertManager.sendAlert(
                "SM",
                "Low Stock",
                "Please Check The Item Stocks Levels as there Items That need restocking"
            );

            if (alertSentIM) {
                System.out.println("Low Stock Detected, Notification Sent to IM");
            }
            if (alertSentSM) {
                System.out.println("Low Stock Detected, Notification Sent to SM");
            }
        }
    }
}
