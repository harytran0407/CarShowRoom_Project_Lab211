/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Harry
 */
public class Brand {
    private String brandID;
    private String brandName;
    private String soundBrand;
    private String brandPrice;

    @Override
    public String toString() {
        return String.format("|   %-10s |  %-38s | %-14s | %-11s |\n",getBrandID(),getBrandName(),getSoundBrand(),getBrandPrice());
    }

    public String getBrandID() {
        return brandID.toUpperCase();
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSoundBrand() {
        return soundBrand;
    }

    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }

    public String getBrandPrice() {
        //Xử lý nếu nhập giá tiền không có chữ B
        if (!brandPrice.toUpperCase().contains("B")) return brandPrice+"B";
        else
        return brandPrice.toUpperCase();
    }

    public void setBrandPrice(String brandPrice) {
        this.brandPrice = brandPrice;
    }

    public Brand(String brandID, String brandName, String soundBrand, String brandPrice) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.brandPrice = brandPrice;
    }

    public Brand() {
        
    }
    
    }
   

  