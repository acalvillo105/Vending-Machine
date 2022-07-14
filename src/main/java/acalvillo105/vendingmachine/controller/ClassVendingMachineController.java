/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acalvillo105.vendingmachine.controller;

import acalvillo105.vendingmachine.dao.ClassVendingMachinePersistenceException;
import acalvillo105.vendingmachine.dto.Item;
import acalvillo105.vendingmachine.dto.Money;
import acalvillo105.vendingmachine.service.ClassVendingMachineServiceLayer;
import acalvillo105.vendingmachine.service.NoItemInventoryException;
import acalvillo105.vendingmachine.ui.ClassVendingMachineView;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author acalvillo
 */
public class ClassVendingMachineController {
    //private classVendingMachineDao dao;
    
    @Autowired
    private ClassVendingMachineView view;
    private ClassVendingMachineServiceLayer service;
    
    public ClassVendingMachineController(ClassVendingMachineView view, ClassVendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

  
    public void run() throws ClassVendingMachinePersistenceException, NoItemInventoryException{
        boolean keepGoing = true;
        
        
        while (keepGoing){
            
            keepGoing = getMenu();//get selectrion from user 
            if (keepGoing){
                vendItem();
            }
            

        }
        exitMessage();
        
    }
    
    private boolean getMenu() throws ClassVendingMachinePersistenceException{
        boolean keepGoing = true;
        
        List<Item> itemList = service.getItemInventory();
        keepGoing = view.displayMenu(itemList); //if the user enters 0 it will exit, else it will ask user for money
        return keepGoing;
        
    }
     public String getSelection() throws ClassVendingMachinePersistenceException{
        List<Item> itemList = service.getItemInventory();
        String selection = "";
        
        selection = view.getSelection(itemList);
        
        return selection;
    }
     
    
    
    /*
        1. Request money
        2. calculate if correct amount
        3. vend Item
        4. give change if applicable
        5. update file
    */
    private void vendItem() throws ClassVendingMachinePersistenceException, NoItemInventoryException{
        boolean isEnough = false;
        String currentFunds = "";
        String userChange = "";
        String selection = "";
        String itemPrice = "";
        String moreFunds = "";
        String itemName = "";
        
        Money newTransaction = view.getMoneyInfo(); //creates object and initializes amount
        currentFunds = newTransaction.getTotal(); //get initial funds
        selection = getSelection(); //ask user to choose item
        itemPrice = service.getItemPrice(selection); //get price of item
        newTransaction.setItemPrice(itemPrice);
        itemName = service.getItemName(itemName);
        
        
        do{            
            //String userFunds = view.getMoney();                        
            if (newTransaction.isSufficient()){ //if its enough, check if there's change 
                userChange = newTransaction.getChange();//get change
                if(!userChange.equals("0")){
                    view.displayChange(userChange);
                    view.displayChangeCoins(newTransaction.getQuarters(), newTransaction.getDimes(), newTransaction.getNickels(), newTransaction.getPennies());
                }
                //display change to user
                isEnough = true;
            }
            else{
                view.insufficientFunds();//Display insufficient funds
                //view.displayTotalAndAdditionalFunds(newTransaction.getTotal(), newTransaction.fundsNeeded());
                moreFunds = view.getMoney();//get money money
                newTransaction.updateTotal(moreFunds); //update total funds
            }
            
        }while(!isEnough);
        
        service.updateItemInventory(selection);
        newTransaction.resetTotal();
        view.vendedSuccessfully();
        view.getEnter();
        
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    
    
}

