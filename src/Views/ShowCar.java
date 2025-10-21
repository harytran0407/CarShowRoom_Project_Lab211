/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Business.Brands;
import Business.Cars;
import Model.Brand;
import Model.Car;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Harry
 */
public class ShowCar {
    private final String CAR_PATHFILE = "cars.txt";
    private final String TABLE = "-------------------------------------------------";
   
    private final String HEADER_TABLE =
            "| ---------------------------------------------------------------------------------|\n" +
            "|   BRAND ID   |  BRAND ID             | COLOR     | FRAME ID       | ENGINE ID    |\n" +
            "| ---------------------------------------------------------------------------------|";
    private final String FOOTER_TABLE =
            "|----------------------------------------------------------------------------------|";
    
    public ShowCar() {
       Cars cars = new Cars();
        try {
            cars.readFromCarFile(CAR_PATHFILE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //show thông tin một Car nhất định
    public void showInfoCar(Car t){
        System.out.println(TABLE);
        System.out.println      ("                CAR INFORMATION                ");
        System.out.println(TABLE);
        System.out.println("Car ID: "+t.getCarID());
        System.out.println("Brand ID: "+t.getBrandID());
        System.out.println("Car's color: "+t.getCarColor());
        System.out.println("Frame ID: "+t.getFrameID());
        System.out.println("Engine ID: "+t.getEngineID());
        System.out.println(TABLE);
    }
    //show danh sách Car theo dạng table
    public void showList(ArrayList<Car> t) {
        System.out.println(HEADER_TABLE);
        for (Car i:t){
            System.out.print(i);
        }
        System.out.println(FOOTER_TABLE);
    }
}
