/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acalvillo105.vendingmachine.dto;

import java.util.Objects;

/**
 *
 * @author acalvillo
 */
public class Item {
    private String itemId;
    private String itemName;
    private String itemPrice;
    private String itemInventory;
    
    //constructor
    public Item(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.itemId, other.itemId)) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemPrice, other.itemPrice)) {
            return false;
        }
        return Objects.equals(this.itemInventory, other.itemInventory);
    }
    
    public String toString(){
        return "Item{ ID: " + itemId + " Name: "+ itemName + " Price " + itemPrice + " Inventory: "+ itemInventory + "}";
    }
    
    //setters
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    
    public void setItemPrice(String itemPrice){
        this.itemPrice = itemPrice;
    }
    
    public void setItemInventory(String itemInventory){
        this.itemInventory = itemInventory;
    }
    
    //getters
    public String getItemId(){
        return itemId;
    }
    
    public String getItemName(){
        return itemName;
    }
    
    public String getItemPrice(){
        return itemPrice;
    }
    
    public String getItemInventory(){
        return itemInventory;
    }
    
}
