/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;

/**
 *
 * @author CK
 */
public class DailySale {
    private Long saleId; //PK
    private Long itemId; //FK to Item
    private LocalDate saleDate;
    private int quantitySold;
    private Long recordedById; //FK to User
    
    //Constructor
    public DailySale() {
    }

    public DailySale(Long saleId, Long itemId, LocalDate saleDate, int quantitySold, Long recordedById) {
        this.saleId = saleId;
        this.itemId = itemId;
        this.saleDate = saleDate;
        this.quantitySold = quantitySold;
        this.recordedById = recordedById;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Long getRecordedById() {
        return recordedById;
    }

    public void setRecordedById(Long recordedById) {
        this.recordedById = recordedById;
    }   
}
