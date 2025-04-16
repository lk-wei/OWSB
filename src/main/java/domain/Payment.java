/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;

/**
 *
 * @author zuwei
 */
public class Payment implements Identifiable<Long>{
    private Long id; 
    private String paymentCode;
    private Long supplierId;    
    private LocalDate paymentDate;           
    private double paymentAmount;
    
    public Payment() {
    }

    public Payment(Long id, String paymentCode, Long supplierId, LocalDate paymentDate, double paymentAmount) {
        this.id = id;
        this.paymentCode = paymentCode;
        this.supplierId = supplierId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }


    
   
    
    }



 


    

