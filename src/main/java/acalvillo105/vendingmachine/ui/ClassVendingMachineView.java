/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acalvillo105.vendingmachine.ui;

import acalvillo105.vendingmachine.dto.Item;
import acalvillo105.vendingmachine.dto.Money;
import java.util.List;


/**
 *
 * @author acalvillo
 */

public class ClassVendingMachineView {
    
    private UserIO io;
    
    
    public ClassVendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public boolean displayMenu(List<Item> itemList){
        //Displays menu to user
        String userInput = "";
        
        for (Item currentItem : itemList){   
            if (!currentItem.getItemInventory().equals("0"))
                io.print(currentItem.getItemId()+ "\t" + currentItem.getItemPrice() + "\t" + currentItem.getItemName() );   
        }  
        userInput = io.readEnter("Enter 0 to exit or enter to continue: ");
        if (userInput.equals("0"))
            return false;
        return true;
        
    }
    
    public String getSelection(List<Item> itemList){
        String selection = "";
        selection = io.readValidString("Please Enter your choice. ", itemList);
        //update this to not take 0 in 
       
        return selection;

    }
    
    public String getMoney(){
        String userFunds = "";
        userFunds = io.readValidCurrency("Enter cash amount in 0.00 format: ");
        
        return userFunds;
    }
    
    public void displayErrorMessage(String errorMsg){
        io.print("================||    ERROR  ||================");
        io.print(errorMsg);
    }

    public void displayExitBanner() {
        io.print("Good Bye!!");
    }
    
    public Money getMoneyInfo(){
        
        String userFunds = getMoney();
        Money currentTransaction = new Money(userFunds);
        return currentTransaction;
    }
    
    public void insufficientFunds(){
        io.print("Insufficient funds! Enter money money");
    }
    
    public void getEnter(){
        io.readEnter("Press Enter to Continue.");
    }
    
    public void vendedSuccessfully(){
        io.print("Successfully Vended!" );
    }
    
    public void displaySelection(String itemName){
        io.print("You chose " + itemName);
    }
    public void displayChange(String userChange){
        io.print("Your change is: " + userChange);
    }
    
    public void displayChangeCoins(int q, int d, int n, int p){
        io.print("Quarters: " + q + "\nDimes: " + d + "\nNickels: " + n + "\nPennies: " + p);
    }
    
    
    
}
