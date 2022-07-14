/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package acalvillo105.vendingmachine.ui;

import acalvillo105.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author acalvillo
 */
public interface UserIO {
    void print(String msg);
    
    double readDouble(String prompt);
    double readDouble(String prompt, double min, double max);
    
    int readInt(String prompt);
    int readInt(String prompt, int min, int max);
    
    String readString(String prompt);
    String readEnter(String promopt);
    String readValidString(String prompt, List<Item> itemList);
    String readValidCurrency(String prompt);
       
}
