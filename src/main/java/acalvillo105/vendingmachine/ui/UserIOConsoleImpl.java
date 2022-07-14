/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acalvillo105.vendingmachine.ui;

import acalvillo105.vendingmachine.dto.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author acalvillo
 */


public class UserIOConsoleImpl implements UserIO {
    final private Scanner console = new Scanner(System.in);
    
    @Override
    public void print(String msg) {
        System.out.println(msg);//will print msg    }
    }
    
    @Override
    public double readDouble(String prompt) {
        boolean invalidInput = true; //asume it's invalid
        double num = 0;
        
        while(invalidInput){//will keep running until a valid double is entered
            
            try{
                String stringValue = this.readString(prompt);
                num = Double.parseDouble(stringValue);//will throw exception unless it's double
                invalidInput = false; //this line will run iff it is an double
            }catch(NumberFormatException e){
                this.print("You did not enter a Double. Please try again.");
            }
        }
        return num;   
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double result;
        do {
            result = this.readDouble(prompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    public int readInt(String prompt) {
        boolean invalidInput = true; //asume it's invalid
        int num = 0;
        
        while(invalidInput){//will keep running until a valid Int is entered
            
            try{
                String stringValue = this.readString(prompt);
                num = Integer.parseInt(stringValue);//will throw exception unless it's int
                invalidInput = false; //this line will run iff it is an Int
            }catch(NumberFormatException e){
                this.print("You did not enter an Integer. Please try again.");
            }
        }
        return num;   
    }

    @Override
    public int readInt(String prompt, int min, int max) {
       int result;
        do {
            result = this.readInt(prompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    public String readString(String prompt) {
        boolean isValid = false;
        String input;
        do {
            System.out.println(prompt);
            input = console.nextLine();
            
            if(input == null || input.isEmpty()){
             System.out.println("You did not enter anything");
            }
            else{
                isValid = true;
            }
            
        }while(!isValid);
        
        return input;// will read string from user
    }
    
   
    @Override
    public String readEnter(String prompt){
        System.out.println(prompt);
        return console.nextLine();
    }
    
    @Override
    public String readValidString(String prompt, List<Item> itemList){
        boolean validInput = false;
        List<String> itemId = new ArrayList<String>();
        String userInput = "";
        
        //add all valid item id to list
        for (Item currentItem : itemList){
            itemId.add(currentItem.getItemId());
        }
        //itemId.add("0");
        do {
            userInput = readString(prompt).toUpperCase(); //get a string from user
            
            
            if (itemId.contains(userInput) && !itemList.get(itemId.indexOf(userInput)).getItemInventory().contentEquals("0")){ //if it matches with one of out items then return that string 
                validInput = true;
                
                print("You choose: " + userInput + " " + itemList.get(itemId.indexOf(userInput)).getItemName());
                
            }            
            else{
                print(userInput);
                print("Invalid choice, try again!");
            }
            
        }while(!validInput);
        
        return userInput;
        
        
    }    
    
    // Implement read curreny here. should have precision of 2!!! 
    @Override
    public String readValidCurrency(String prompt){
        boolean isInvalid = true;
        String validCurrency = "";
        
        do{
            validCurrency = readString(prompt);
            Pattern p = Pattern.compile("([0-9]{1,3}\\,)*[0-9]{1,3}\\.[0-9]{2}");
            Matcher m = p.matcher(validCurrency);
            if(m.matches())
                isInvalid = false;
            else
                print("Invalid amout! Try again.");
            
        }while(isInvalid);
        return validCurrency;
    }
    
}
