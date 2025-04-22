/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Kang Wei
 */
public class Alert implements Identifiable<Long>{
    private Long id;
    private Long sender;
    private String reciverRole;
    private String title;
    private String message;
    private String status;
    
    Alert(){
        
    }

    public Alert(Long id, Long sender, String reciverRole, String title, String message, String status) {
        this.id = id;
        this.sender = sender;
        this.reciverRole = reciverRole;
        this.title = title;
        this.message = message;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public String getReciverRole() {
        return reciverRole;
    }

    public void setReciverRole(String reciverRole) {
        this.reciverRole = reciverRole;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
