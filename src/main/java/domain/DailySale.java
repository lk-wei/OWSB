/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author CK
 */
public class DailySale implements Identifiable<Long>{
    private Long id; //PK
    private String saleCode;
    private Long itemId; //FK to Item
    private LocalDate saleDate;
    private int quantitySold;
    private Long recordedById; //FK to User
    private List<Item> itemList;
    //Constructor
    public DailySale() {
    }

    public DailySale(Long id, String saleCode, Long itemId, LocalDate saleDate, int quantitySold, Long recordedById, List<Item> itemList) {
        this.id = id;
        this.saleCode = saleCode;
        this.itemId = itemId;
        this.saleDate = saleDate;
        this.quantitySold = quantitySold;
        this.recordedById = recordedById;
        this.itemList = itemList;
    }
    
    

//    public DailySale(Long saleId, String saleCode, Long itemId, LocalDate saleDate, int quantitySold, Long recordedById) {
//        this.saleId = saleId;
//        this.saleCode = saleCode;
//        this.itemId = itemId;
//        this.saleDate = saleDate;
//        this.quantitySold = quantitySold;
//        this.recordedById = recordedById;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode;
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

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
    
    

}
