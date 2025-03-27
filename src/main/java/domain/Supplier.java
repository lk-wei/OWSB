/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author zuwei
 */
public class Supplier {
    private String supplierId;
    private String suppliername;
    private String email;
    private String phone;

    public Supplier(String supplierId, String suppliername, String email, String phone) {
        this.supplierId = supplierId;
        this.suppliername = suppliername;
        this.email = email;
        this.phone = phone;
    }

      public String getSupplierId() {
        return supplierId;
    }


   public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String role) {
        this.phone = phone;
    }
}
