/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Business;

import Model.Brand;
import java.util.ArrayList;

/**
 *
 * @author Harry
 */
public interface BrandList {
    
    
    public void showList();
    public void addBrand();
    public void searchBrandByID();
    public void updateBrandByID();
    public void showListByValue();
    public void showListAcseding();
    public void searchCarByBrandName();
    public void saveDataToFile(String path);
    public void exitProgram(Cars car);
    
    
    
    
}
