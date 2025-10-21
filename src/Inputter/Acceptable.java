/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Inputter;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harry
 */
public interface Acceptable {

    public final String NAME_VALID = "^.{2,20}$";
    public final String DOUBLE_VALID = "^[+-]?\\d*\\.?\\d+$";
    public final String INTEGER_VALID ="[+-]?\\d+$";
    public final String FRAME_VALID ="^(F|f)\\d{5}$";
    public final String ENGINE_VALID = "^(E|e)\\d{5}$";
    
    public static boolean isValid(String data, String pattern){
        return data.matches(pattern);
    }
    
    
        
}
