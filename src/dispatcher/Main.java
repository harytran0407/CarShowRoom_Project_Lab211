/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dispatcher;

import Business.Brands;
import Business.Cars;
import Inputter.Inputter;

/**
 *
 * @author Harry
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Inputter ip = new Inputter();
        Brands brand = new Brands();
        Cars car = new Cars();
        int choice = 0;
        
        do {
            System.out.println("--------------CAR SHOW ROOM--------------");
            System.out.println("1. List all brand");
            System.out.println("2. Add a new brand");
            System.out.println("3. Search for a brand by ID");
            System.out.println("4. Update a brand by ID");
            System.out.println("5. List all brands with prices less than or equal to an input value");
            System.out.println("6. List all cars in ascending order of brands names");
            System.out.println("7. Search cars by partial brand name match");
            System.out.println("8. Add a new car");
            System.out.println("9. Remove a car by ID");
            System.out.println("10. Update a car by ID");
            System.out.println("11. List all cars by a specific color");
            System.out.println("12. Save data to files");
            System.out.println("13. Quit program");
            System.out.println("------------------------------------------");
            
            try {
                choice = ip.getInt("Select option: ");
            } catch (NumberFormatException e) {
                System.out.println("Please input 1-13 please!");
                continue;
            }
            switch (choice){
                case 1:brand.showList();break;
                case 2:brand.addBrand();break;
                case 3:brand.searchBrandByID();break;
                case 4:brand.updateBrandByID();break;
                case 5:brand.showListByValue();break;
                case 6:brand.showListAcseding();break; 
                case 7:brand.searchCarByBrandName();break; 
                case 8:car.addCar();break; 
                case 9:car.removeCarById();break; 
                case 10:car.updateCarById();break; 
                case 11:car.showListByColor();break; 
                case 12:brand.saveDataToFile("brands.txt");car.saveDataToFile("cars.txt");break; 
                case 13:brand.exitProgram(car);break;
                default: System.out.println("The function is not available!");break;
            }
            
            
        } while(choice!=13);
    }
    
}
