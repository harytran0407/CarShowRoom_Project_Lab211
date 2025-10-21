/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Model.Brand;
import Business.Brands;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Harry
 */
public class ShowBrand extends ArrayList<Brand> {
    private final String BRAND_PATHFILE = "brands.txt";
    private final String TABLE = "-------------------------------------------------";
   
    private final String HEADER_TABLE =
            "| --------------------------------------------------------------------------------------|\n" +
            "|   BRAND ID   |  BRAND NAME                             |  SOUND BRAND   | PRICE       |\n" +
            "| --------------------------------------------------------------------------------------|";
    private final String FOOTER_TABLE =
            "|---------------------------------------------------------------------------------------|";
    
    public ShowBrand() {
       Brands brands = new Brands();
        try {
            brands.readFromBrandFile(BRAND_PATHFILE);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    //show thông tin của một Brand nhất định
    public void showInfoBrand(Brand t){
        System.out.println(TABLE);
        System.out.println      ("                BRAND INFORMATION                ");
        System.out.println(TABLE);
        System.out.println("Brand ID: "+t.getBrandID());
        System.out.println("Brand Name: "+t.getBrandName());
        System.out.println("Sound By Brand: "+t.getSoundBrand());
        System.out.println("Brand Price: "+t.getBrandPrice());
        System.out.println(TABLE);
    }
    //show danh sách Brand theo dạng table
    public void showList(ArrayList<Brand> t) {
        System.out.println(HEADER_TABLE);
        for (Brand i:t){
            System.out.print(i);
        }
        System.out.println(FOOTER_TABLE);
    }

    
}
