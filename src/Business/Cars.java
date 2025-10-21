/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Inputter.Acceptable;
import Inputter.Inputter;
import Model.Brand;
import Model.Car;
import Views.ShowBrand;
import Views.ShowCar;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Harry
 */
public class Cars extends ArrayList<Car> implements CarList{
    private final String CAR_PATHFILE = "cars.txt";
    boolean isSaved;
    private Inputter ip = new Inputter();
    private ArrayList<Brand> brand;
    private Brands brands = new Brands();
    public Cars() {
        super();
        try {
            readFromCarFile(CAR_PATHFILE);
            brands.readFromBrandFile("brands.txt");
            brand=brands;
        } catch (Exception e) {
        }
    }
    
    

    
    //8. ADD A NEW CAR
    @Override
    public void addCar() {
        String carID,brandID,color,frameID,engineID;
        //Car ID
        do {
        carID = ip.getString("Enter car ID: ");
        if (findCarByID(carID)){
            System.out.println("Car ID already exits!Please enter again!");
        }
        } while (findCarByID(carID)); 
        //BrandID
        
        System.out.println("Available brands: ");
        for (int i=0;i<brand.size();i++){
            System.out.printf("%-3d%s", i + 1, brand.get(i));
        }
        int choice;
        do {
            choice = ip.getInt("Choose brand (1-" + brand.size()+"): ");
        } while (choice < 1 || choice > brand.size());
        brandID = brand.get(choice - 1).getBrandID();
        //Color
        color = ip.getString("Enter the color of car: ");
        //Frame ID
        do {
            frameID=ip.getString("Enter the frame ID: ");
            if (!Acceptable.isValid(frameID, Acceptable.FRAME_VALID)){
                System.out.println("Frame ID must follow format Fxxxxx (e.g., F00001).");}
            if (findFrameByID(frameID)) System.out.println("Frame ID already exist! Please enter again!");
        } while (!Acceptable.isValid(frameID, Acceptable.FRAME_VALID)||findFrameByID(frameID));
        //Engine ID
        do {
            engineID=ip.getString("Enter the engine ID: ");
            if (!Acceptable.isValid(engineID, Acceptable.ENGINE_VALID)){
            System.out.println("Engine ID must follow format Exxxxx (e.g., E00001).");}
            if (findEngineByID(engineID)) System.out.println("Engine ID already exist! Please enter again!");
        } while (!Acceptable.isValid(engineID, Acceptable.ENGINE_VALID)||findEngineByID(engineID));
        //Bỏ vào Car sau khi nhập xong
        Car c = new Car(carID, brandID, color, frameID, engineID);
        this.add(c);
        System.out.println("Registration successfully!");
        isSaved = false;
        
    }
    
    //9. REMOVE A CAR BY ID
    @Override
    public void removeCarById() {
        String search;
        ShowCar show = new ShowCar();
        boolean found=false;
        show.showList(this);
        search = ip.getString("Enter the car's ID to remove: ");
        for (Car i:this){
            if (i.getCarID().equalsIgnoreCase(search)){
                found=true;
                show.showInfoCar(i);
                String result = ip.getYesNo("Are you want to delete this Car? (Y/N): ");
                if (result.equalsIgnoreCase("Y")){
                    this.remove(i);
                    isSaved = false;
                    System.out.println("The car has been successfully deleted!");
                } else {System.out.println("Deletion cancelled.");} break;
            }
        }
        if (!found) System.out.println("This car does not exist!");
    }
    
    //10. UPDATE A CAR BY ID
    @Override
    public void updateCarById() {
        String carID,color,frameID,engineID;
        ShowCar show = new ShowCar();
        boolean update = false; //check coi có update không
        show.showList(this);
        carID = ip.getString("Enter the car ID to Update: ");
        if (!findCarByID(carID)) {
            System.out.println("This car does not exist!");
            return;
        } 
        
        for (Car i:this){
            
            
                 if (i.getCarID().equalsIgnoreCase(carID)) {
                 show.showInfoCar(i);
                //Update color
                color = ip.getStringWithEmpty("Enter the new color of car (Skip = No update): ");
                 if (!color.isEmpty()) {
                     i.setCarColor(color);
                     update = true;
                 } 
                
                //Update Frame ID
                do {
                    frameID=ip.getStringWithEmpty("Enter the frame ID (Skip = No update): ");
               
                    if (frameID.isEmpty()) break;
                    if (!Acceptable.isValid(frameID, Acceptable.FRAME_VALID)){
                        System.out.println("Frame ID must follow format Fxxxxx (e.g., F00001).");
                        continue;
                    } 
                    if (findFrameByID(frameID)){
                        System.out.println("Frame ID already exist! Please enter again!");
                        continue;
                    }
                
                    i.setFrameID(frameID); 
                    update = true;
                    break;
                
                } while (!Acceptable.isValid(frameID, Acceptable.FRAME_VALID)||findFrameByID(frameID));
                
                //Update Frame ID
                do {
                    engineID=ip.getStringWithEmpty("Enter the engine ID (Skip = No update): ");
               
                    if (engineID.isEmpty()) break;
                    if (!Acceptable.isValid(engineID, Acceptable.ENGINE_VALID)){
                        System.out.println("Engine ID must follow format Exxxxx (e.g., E00001).");
                        continue;
                    } 
                    if (findEngineByID(engineID)){
                        System.out.println("Engine ID already exist! Please enter again!");
                        continue;
                    }
                    i.setEngineID(engineID); 
                    update = true; break;
                } while (!Acceptable.isValid(engineID, Acceptable.ENGINE_VALID)||findEngineByID(engineID));
                break;
        }
    }
        if (update) {
            isSaved=false;
            System.out.println("Update Successfully!");
        } 
        else {
            
            System.out.println("Nothing changes!");
        }
        
        
    }
    
    //11. List all car by specific color
    @Override
    public void showListByColor() {
        ShowCar show = new ShowCar();
        ArrayList<Car> result = new ArrayList<>();
        String color = ip.getString("Enter the color to view: ");
        for (Car i:this){
            if (i.getCarColor().equalsIgnoreCase(color)){
                result.add(i);
            }
        }
        if (result.isEmpty()) System.out.println("This color does not exist!");
        else show.showList(result);
    }

    //12.SAVE DATA TO "cars.txt" FILES 
    @Override
    public void saveDataToFile(String path) {
        File f = new File(path);
        if (this.isEmpty()){ 
            return;
        }
         try (PrintWriter pw = new PrintWriter(new FileWriter(f))){
             for (Car i:this){
                 pw.printf("%s, %s, %s, %s, %s%n",
                         i.getCarID(),
                         i.getBrandID(),
                         i.getCarColor(),
                         i.getFrameID(),
                         i.getEngineID());
             }
             isSaved=true;
             System.out.println("Save to "+CAR_PATHFILE+" successfully!");
         } catch (IOException e) {
             System.out.println("Error saving data: "+e.getMessage());
        }
    }

    //===================================CÁC HÀM HỖ TRỢ==========================================
    
    //Find exist car
    public boolean findCarByID(String id){
        for (Car i:this){
            if (i.getCarID().equalsIgnoreCase(id)){
                return true;
            }
        } return false;
    }
    //Find exist frameID
    public boolean findFrameByID(String id){
        for (Car i:this){
            if (i.getFrameID().equalsIgnoreCase(id)){
                return true;
            }
        } return false;
    }
    //find exist engineID
    public boolean findEngineByID(String id){
        for (Car i:this){
            if (i.getEngineID().equalsIgnoreCase(id)){
                return true;
            }
        } return false;
    }
    
    //HÀM ĐỌC FILE TỪ "cars.txt"
    public void readFromCarFile(String path) {
        this.clear();
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " file not found!");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                Car c = dataToObject(line);
                if (c != null) this.add(c);
            }
            isSaved = true;
        } catch (IOException e) {
            System.out.println("Error reading " + path + ": " + e.getMessage());
        }
    
    }
    //chuyển file đã đọc từ car.txt thành từng đối tượng
    private Car dataToObject(String text) {
        String[] parts = text.split(",",-1);
        if (parts.length >=5){
            String carID = parts[0].trim();
            String brandID = parts[1].trim();
            String color = parts[2].trim();
            String frameID = parts[3].trim();
            String engineID=parts[4].trim();
            return new Car(carID, brandID, color, frameID, engineID);
        }
        return null;
    }
    
}
