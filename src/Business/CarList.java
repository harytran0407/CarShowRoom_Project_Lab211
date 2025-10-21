/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Business;

/**
 *
 * @author Harry
 */
public interface CarList {
    
    public void addCar();
    public void removeCarById();
    public void updateCarById();
    public void showListByColor();
    public void saveDataToFile(String path);
    
}
