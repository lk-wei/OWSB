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
public class PaymentItem implements Identifiable<Long>{
    private Long id;    
    private Long paymentId;  
    private String paymentCode;
    private Long purchaseOrderId;       
    private double TotalAmount;
    private String purchaseOrderCode;
    
    public PaymentItem() {
    }

    public PaymentItem(Long id, Long paymentId, String paymentCode, Long purchaseOrderId, double totalAmount, String purchaseOrderCode) {
        this.id = id;
        this.paymentId = paymentId; //fk
        this.paymentCode = paymentCode; //fk
        this.purchaseOrderId = purchaseOrderId; //fk
        this.TotalAmount = totalAmount;  //fk
        this.purchaseOrderCode = purchaseOrderCode; //fk
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.TotalAmount = totalAmount;
    }

    public String getPurchaseOrderCode() {
        return purchaseOrderCode;
    }

    public void setPurchaseOrderCode(String purchaseOrderCode) {
        this.purchaseOrderCode = purchaseOrderCode;
    }
}
