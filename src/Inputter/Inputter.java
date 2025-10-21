/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inputter;

import java.util.Scanner;

/**
 *
 * @author Harry
 */
public class Inputter {
    Scanner sc=new Scanner(System.in);

    public Inputter() {
    }
    public String getString(String mess){
        String result;
        do {
            System.out.print(mess);
            result = sc.nextLine();
            if (result.isEmpty()) System.out.println("Cannot be empty!");
        } while (result.isEmpty());
        
        return result;
    }
    public String getStringWithEmpty(String mess){
        System.out.print(mess);
        return sc.nextLine();
    }
    public int getInt(String mess){
        int result = 0;
        String temp = getString(mess);
        if (Acceptable.isValid(temp, Acceptable.INTEGER_VALID ))
            result=Integer.parseInt(temp);
        return result;     
    }
    
    public double getDouble(String mess){
        double result =0;
        String temp = getString(mess);
        if (Acceptable.isValid(temp, Acceptable.DOUBLE_VALID))
            result=Double.parseDouble(temp);
        return result;
    }
    public String getYesNo(String mess){
        boolean check = false;
        String temp;
        while (true) {
            temp = getString(mess);
            if (temp.equalsIgnoreCase("Y")||temp.equalsIgnoreCase("N")){
                return temp;
        } else {
                 System.out.println("Invalid. Please enter Y or N.");
            }
        }
        }
    public String inputAndLoop(String mess, String pattern){
        String result=null;
        boolean loop=true;
        do{
            result=getString(mess);
            loop=!Acceptable.isValid(result, pattern);
            if (loop) System.out.println("Data is invalid! Re-enter...");
            
        } while (loop);
        return result.trim();
    }
}
