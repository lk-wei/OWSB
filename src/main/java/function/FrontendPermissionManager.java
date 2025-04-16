/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;
import domain.User;
import javax.swing.JButton;

/**
 *
 * @author Kang Wei
 */
public class FrontendPermissionManager {
    public static void applyButtonPermissions(User user, String prefix,JButton newButton, 
                                           JButton editButton, JButton deleteButton) {
        
        // Set default state (hidden + disabled)
        setButtonState(newButton, false);
        setButtonState(editButton, false);
        setButtonState(deleteButton, false);
        
        // Apply permissions
        if (newButton != null) {
            setButtonState(newButton, PermissionService.hasPermission(user, prefix + ":add"));
        }
        if (editButton != null) {
            setButtonState(editButton, PermissionService.hasPermission(user, prefix + "fr:edit"));
        }
        if (deleteButton != null) {
            setButtonState(deleteButton, PermissionService.hasPermission(user, prefix + "fr:delete"));
        }
    }
    
    private static void setButtonState(JButton button, boolean enabled) {
        if (button != null) {
            button.setVisible(enabled);
            button.setEnabled(enabled);
            button.setFocusable(enabled);
        }
    }
}

