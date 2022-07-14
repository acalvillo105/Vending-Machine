/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acalvillo105.vendingmachine.dao;

import acalvillo105.vendingmachine.dto.Item;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author acalvillo
 */

public class ClassVendingMachineDaoFileImpl implements classVendingMachineDao{
   // private Map<String, Item> itemInventory = new HashMap<>();
    private List<Item> itemInventory = new ArrayList<Item>();
    private String ITEM_FILE= "itemsInfo.txt";
    public final String DELIMITER = "::"; 

    @Autowired
    public ClassVendingMachineDaoFileImpl() throws ClassVendingMachinePersistenceException {
        loadItemFile();
        this.ITEM_FILE = "itemsInfo.txt";
    }
    public ClassVendingMachineDaoFileImpl(String ITEM_FILE) {
        this.ITEM_FILE = ITEM_FILE;
    }      
    
    @Override
    public void updateItemInventory(String vendedItemId){
        
        String itemCount = "";
        int count = 0 ;
        for (Item currentItem: itemInventory){
            if(currentItem.getItemId().equals(vendedItemId)){
                itemCount = currentItem.getItemInventory();
                count = parseInt(itemCount);
                count--;
                itemCount = Integer.toString(count);
                currentItem.setItemInventory(itemCount);
                break;
            }
        }
        try {                 
            writeItems();
        } catch (ClassVendingMachinePersistenceException ex) {
            Logger.getLogger(ClassVendingMachineDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
    public List<Item> getItemInventory() throws ClassVendingMachinePersistenceException {
        //loadItemFile();
        return itemInventory;
        //return new ArrayList(itemInventory.values);
    }
    @Override
    public String getItemPrice(String itemId) {
        String itemPrice = "";
        for (Item currentItem: itemInventory){
            if(currentItem.getItemId().equals(itemId)){
                itemPrice = currentItem.getItemPrice();
                break;
            }
        }
        //System.out.println("price is: " + itemPrice);
        return itemPrice;
    }
    
    @Override
    public String getItemName(String itemId){
        String itemName = "";
        for (Item currentItem: itemInventory){
            if(currentItem.getItemId().equals(itemId)){
                itemName = currentItem.getItemName();
                break;
            }
        }
        return itemName;
    }
    
     /* 
        Functionality:
            1. Read line
            2. Create object
            3. Return object
    */
    private Item unmarshallItem(String itemAsText){
        //itemId::itemName::itemPrice::Inventory
        // [0]      [1]         [2]         [3]
        String[] itemTokens = itemAsText.split(DELIMITER);
        
        String itemId = itemTokens[0];
        Item itemFromFile = new Item(itemId);
        
        itemFromFile.setItemName(itemTokens[1]);
        itemFromFile.setItemPrice(itemTokens[2]);
        itemFromFile.setItemInventory(itemTokens[3]);
        
        return itemFromFile;
        
    }
    
    /* 
        Functionality:
            1. Open file
            2. Pass each line to unmarshall to create Item objects
            3. Store new object in map
            4. Close file
    */
    private void loadItemFile() throws ClassVendingMachinePersistenceException{
        Scanner scanner;
        
        try{
            scanner = new Scanner( 
                        new BufferedReader(
                            new FileReader(ITEM_FILE)));
            
            
        }catch (FileNotFoundException e){
            throw new ClassVendingMachinePersistenceException(
            "Could not load item data into memory.", e);
        }
        
        String currentLine;        
        Item currentItem;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            
            itemInventory.add(currentItem);
            //itemInventory.put(currentItem.getItemId(), currentItem);// Object is added to the map 
        }
        
        scanner.close();
    }
    /* 
        Functionality:
            1. Take Item
            2. Create a String consisting of itemId::itemName::itemPrice::Inventory in that order seperated by :: delimiter
            3. Returns String
    */
    private String marshallItem(Item anItem){
        String itemAsText = anItem.getItemId() + DELIMITER;
        itemAsText += anItem.getItemName() + DELIMITER;
        itemAsText += anItem.getItemPrice() + DELIMITER;
        itemAsText += anItem.getItemInventory();
        
        return itemAsText;
    }
    
   
    private void writeItems() throws ClassVendingMachinePersistenceException {
        PrintWriter out;
        
        try{
            out = new PrintWriter( 
                    new FileWriter(ITEM_FILE));
        } catch(IOException e) { 
            throw new ClassVendingMachinePersistenceException("Could not save item data.", e);
        }
        
        
        String itemAsText;
        List<Item> itemList = this.getItemInventory();
        
        for (Item currentItem : itemList){
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        
        out.close();
    }

    
    
    
}
