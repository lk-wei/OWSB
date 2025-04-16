/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.List;

/**
 *
 * @author zuwei
 */
public class Supplier {
    private Long supplierId;
    private String supplierCode;
    private String suppliername;
    private String email;
    private String phone;
    private List<ItemSupplier> item;
    
    public Supplier() {
    }

    public Supplier(Long supplierId, String supplierCode, String suppliername, String email, String phone, List<ItemSupplier> item) {
        this.supplierId = supplierId;
        this.supplierCode = supplierCode;
        this.suppliername = suppliername;
        this.email = email;
        this.phone = phone;
        this.item = item;
    }

    public Long getSupplierId() {
        return supplierId;
    }


    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
