/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

import domain.User;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Kang Wei
 */

public class PermissionService {
    private static final Map<String, Set<String>> PERMISSION = Map.of(
        // Sales Manager Permissions
        "SM", Set.of(
            "item:view", "item:add", "item:edit","item:delete",
            "supplier:view", "supplier:add", "supplier:edit", "supplier:delete",
            "ds:view", "ds:add", "ds:edit", "ds:delete",
            "pr:view", "pr:add", "pr:edit","pr:delete",
            "po:view"
        ),
        
        // Purchase Manager Permissions
        "PM", Set.of(
            "item:view", 
            "supplier:view", 
            "pr:view",
            "po:view", "po:add", "po:edit","po:delete"
        ),
        
        // Administrator Permissions
        "AD", Set.of("*"), // All permissions
        
        // Inventory Manager Permissions
        "IM", Set.of(
            "item:view", "item:add", "item:edit","item:delete",
            "su:view","su:add", "su:edit","su:delete",
            "sr:view","sr:add", "sr:edit","sr:delete",
            "po:view"
        ),
        
        // Finance Manager Permissions
        "FM", Set.of(
            "po:view", "po:approve",
            "su:view",
            "pay:view","pay:add", "pay:edit","pay:delete",
            "fr:view","fr:add", "fr:edit","fr:delete",
            "pr:view"
    
        )
    );

    public static boolean hasPermission(User user, String permission) {
        // Admins have all permissions
        if ("AD".equals(user.getRole())) return true;
        
        // Check if the role has the permission
        return PERMISSION.getOrDefault(user.getRole(), Set.of())
                              .contains(permission) ||
               PERMISSION.get(user.getRole()).contains("*");
    }
}