/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Inputter.Inputter;
import Model.Brand;
import Views.ShowBrand;
import Inputter.Acceptable;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harry
 */
public class Brands extends ArrayList<Brand> implements BrandList{
    private final String BRAND_PATHFILE = "brands.txt";
    private boolean isSaved ;
    Inputter ip = new Inputter();
    
    
    
    //Constructor Brand
    public Brands(){
        super();
        
        try {
            readFromBrandFile(BRAND_PATHFILE);
        } catch (Exception e) {
        }
        
    }
    
    //1.LIST ALL BRAND
    public void showList(){
        ShowBrand show = new ShowBrand();
        show.showList(this);
    }
   
    
    //2.ADD A NEW BRAND
    @Override
    public void addBrand() {
        try {
            readFromBrandFile(BRAND_PATHFILE);
            
        } catch (FileNotFoundException e) {
        }
        String id;
        do{
            id = ip.getString("Enter the brand's ID: ");
            if (isBrandIdExist(id)) System.out.println("Brand has been exist!Please enter another ID!");
            else break;
        } while (true);
        
        String name = ip.getString("Enter the brand's name: ");
        String sound = ip.getString("Enter the sound system brand: ");
        String price = ip.inputAndLoop("Enter the price (0.00):  ",Acceptable.DOUBLE_VALID);
        Brand br = new Brand(id, name, sound, price);
        this.add(br);
        isSaved = false;
        System.out.println("Registration Successfully!");
        
        
        
    }
   
    //3.SEARCH FOR A BRAND BY ID
    @Override
    public void searchBrandByID() {
        ShowBrand show = new ShowBrand();
        boolean found = false;
        String id = ip.getString("Search for a brand by ID: ");
        for (Brand i:this){
            //hàm replaceAll dùng để xóa các ký tự đặc biệt
            if (i.getBrandID().replaceAll("[^a-zA-Z0-9]","").toLowerCase().contains(id.toLowerCase())){
               show.showInfoBrand(i);
               found = true;
            }
        }
        if (!found) System.out.println("This brand does not exist!");
       
    }
    
    //4.UPDATE A BRAND BY ID
    @Override
    public void updateBrandByID() {
        String search;
        ShowBrand show = new ShowBrand();
        boolean found = false;
        boolean update = false;
        show.showList(this);
        search = ip.getString("Enter the brand's ID to update: ");
        for (Brand i:this){
            //hàm replaceAll dùng để xóa các ký tự đặc biệt
            if (i.getBrandID().replaceAll("[^a-zA-Z0-9]","").toLowerCase().equalsIgnoreCase(search.toLowerCase())){
                found=true;
                
                show.showInfoBrand(i);
                String name = ip.getStringWithEmpty("Enter the new name (Skip = No update): ");
                if (!name.isEmpty()) {
                    i.setBrandName(name);
                    update = true;
                }
                String sound = ip.getStringWithEmpty("Enter the new sound system brand (Skip = No update): ");
                if (!sound.isEmpty()) {
                    i.setSoundBrand(sound);
                    update = true;
                }
                String price = ip.getStringWithEmpty("Enter the new price (Skip = No update): ");
                if (!price.isEmpty()) {
                    if (Acceptable.isValid(price,Acceptable.DOUBLE_VALID))  {
                        i.setBrandPrice(price);
                        update = true;
                    }
                    else System.out.println("Invalid price format. Price not update. ");
                }
                
                if (update){
                    System.out.println("Update Successfully!");
                isSaved = false;
                } else {
                    System.out.println("Nothing changes!");
                }
                
                break;
            }
        }
        if (!found) {
            System.out.println("This brand does not exist!");
        }
        
    }
    
    //5.LIST ALL BRANDS WITH PRICES LESS THAN OR EQUAL TO AN INPUT VALUE
    @Override
    public void showListByValue() {
        ShowBrand show = new ShowBrand();
        double value = ip.getDouble("Enter the price vale (0.00): ");
        ArrayList<Brand> result = new ArrayList<>();
        for (Brand i:this){
            if (normalizePrice(i.getBrandPrice())<=value){
                result.add(i);
            }
        }
        if (!result.isEmpty()) show.showList(result);
        else System.out.println("This price does not exist!");
    }
    
    
    //6.LIST ALL CARS IN ASCENDING ORDER OF BRAND NAMES
    @Override
    public void showListAcseding() {
        Collections.sort(this, new Comparator<Brand>(){
            @Override
            public int compare(Brand a, Brand b) {
                int cmp = a.getBrandName().compareToIgnoreCase(b.getBrandName());
                if (cmp!=0) return cmp;
                double price1 = normalizePrice(a.getBrandPrice());
                double price2 = normalizePrice(b.getBrandPrice());
                return Double.compare(price2, price1);
                
            }
            
        });
        
        ShowBrand show = new ShowBrand();
        show.showList(this);
        //Nếu muốn lưu luôn vào trong file
        //saveDataToFile(BRAND_PATHFILE);
        
    }
    
    //7.SEARCH CAR BY PARTIAL BRAND NAME MATCH
    @Override
    public void searchCarByBrandName() {
        ShowBrand show = new ShowBrand();
        boolean found = false;
        ArrayList<Brand> result = new ArrayList<>();
        
        String id = ip.getString("Search for a brand by Name: ");
        String[] keyword = id.split("\\s+");
        for (Brand i:this){
            String name =i.getBrandName().toLowerCase();
            boolean match = true;
            for (String key:keyword){
                if (!name.contains(key)){
                    match=false;
                    break;
                }
            }
            if (match) {
                result.add(i);
                found=true;
            }
        }
        if (!found) System.out.println("This brand does not exist!"); 
        else show.showList(result);
    }

   
    //12.SAVE DATA TO "brands.txt" FILES
    @Override
    public void saveDataToFile(String path) {
        File f = new File(path);
        if (this.isEmpty()){
            return;
        }
         try (PrintWriter pw = new PrintWriter(new FileWriter(f))){
             for (Brand i:this){
                 pw.printf("%s, %s, %s: %s%n",
                         i.getBrandID(),
                         i.getBrandName(),
                         i.getSoundBrand(),
                         i.getBrandPrice());
             }
             isSaved=true;
             System.out.println("Save to "+BRAND_PATHFILE+" successfully!");
         } catch (IOException e) {
             System.out.println("Error saving data: "+e.getMessage());
        }
    }
    
    //13.EXIT PROGRAM
    @Override
    public void exitProgram(Cars car) {
        if ((!this.isEmpty() && !isSaved) || (!car.isEmpty() && !car.isSaved )) {
            String result = ip.getYesNo("Do you want to save the changes before exiting? (Y/N): ");
        
        
            if (result.equalsIgnoreCase("y")) {
                saveDataToFile(BRAND_PATHFILE);
                car.saveDataToFile("cars.txt");
                System.out.println("Brand data saved! Goodbye.");
                return;
                
            } else {
                String sure = ip.getYesNo("You have unsaved changes. Are you sure you want to exit without saving? (Y/N): ");
                if (!sure.equalsIgnoreCase("y")){
                    saveDataToFile(BRAND_PATHFILE);
                    car.saveDataToFile("cars.txt");
                    System.out.println("Data saved! Goodbye.");
                } else {
                    System.out.println("Exit without saving...");
                }
            }
        } System.out.println("GoodBye.");
        
    }
    
    
    //===================================CÁC HÀM HỖ TRỢ==========================================//
    //Hàm mã hóa tiền từ String qua Double
    public double normalizePrice(String price){
        double num = Double.parseDouble(price.replace("B", ""));
        return num;
    }
     //Hàm xác định unique 
    public boolean isBrandIdExist(String id){
        for (Brand m:this){
            if (m.getBrandID().equalsIgnoreCase(id)){
                return true;
            }
        }
        return false;
    }
    
    //đọc file từ brand.txt
    public void readFromBrandFile(String path) throws FileNotFoundException {
        this.clear();
    File f = new File(path);
    if (!f.exists()) {
        System.out.println(path + " file not found!");
        return;
    }
    try (BufferedReader br = new BufferedReader(new FileReader(f))) {
        String line;
        
        while ((line = br.readLine()) != null) {
            
            Brand m = dataToObject(line);
            if (m != null) this.add(m);
            isSaved=true;
        }
    } catch (IOException e) {
        System.out.println("Error reading " + path + ": " + e.getMessage());
    }
    }
    
    //chuyển file đã đọc từ brand.txt thành từng đối tượng
    private Brand dataToObject(String text) {
        String[] parts = text.split(",",-1);
        if (parts.length >=3){
            String id = parts[0].trim();
            String name = parts[1].trim();
            
            String [] subParts = parts[2].split(":",-1);
            String sound = subParts[0].trim();
            String price = subParts[1].trim();
            
            return new Brand(id, name, sound ,price);
        }
        return null;
    }
    
    
    
}
