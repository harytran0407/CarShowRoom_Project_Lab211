 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Harry
 */
public class Car {
    private String carID;
    private String brandID;
    private String carColor;
    private String frameID;
    private String engineID;

    @Override
    public String toString() {
        return String.format("| %-12s |  %-20s | %-9s | %-14s | %-12s |\n",getCarID(),getBrandID(),getCarColor(),getFrameID(),getEngineID());
    }

    public String getCarID() {
        return carID.toUpperCase();
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getBrandID() {
        return brandID.toUpperCase();
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getCarColor() {
        return carColor.toLowerCase();
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getFrameID() {
        return frameID.toUpperCase();
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID.toUpperCase();
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    public Car(String carID, String brandID, String carColor, String frameID, String engineID) {
        this.carID = carID;
        this.brandID = brandID;
        this.carColor = carColor;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public Car() {
    }
}
