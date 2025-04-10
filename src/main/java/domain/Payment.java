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
public class Payment {
    private Long paymentId; 
    private Long supplierId;    
    private LocalDate paymentDate;           
    private double paymentAmount;

    public Payment(Long paymentId, Long supplierId, LocalDate paymentDate, double paymentAmount) {
        this.paymentId = paymentId;
        this.supplierId = supplierId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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



 


    

