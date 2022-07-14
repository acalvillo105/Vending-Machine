/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acalvillo105.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author acalvillo
 */
public class Money {
    //this machine will not take pennies
    private double quartersCount = 0;
    private double dimesCount = 0;
    private double nickelsCount = 0;
    private double penniesCount = 0;
    
    private double quarter = 25;
    private double dimes = 10;
    private double nickels = 5;
    private double pennies = 1;
    
    private double totalDouble = 0;
    
    private BigDecimalMath myMath = new BigDecimalMath(); 
    private BigDecimal total;
    private BigDecimal additionalFunds;
    private BigDecimal itemPrice;
    private String stringTotal;

    
    //big decimal for monetary calculations
    
    public Money(String stringAmount) {
        stringTotal = stringAmount;
        total = new BigDecimal(stringAmount).setScale(2,RoundingMode.HALF_UP);
    }
    
    public void updateQuarters(int total){
        quartersCount = total;
    }
    public void updateDimes(int total){
        dimesCount = total;
    }
    public void updateNickels(int total){
        nickelsCount = total;
    }
    public void updatePennies(int total){
        penniesCount = total;
    }
    
    public int getQuarters(){
        return (int)quartersCount;
    }
    public int getDimes(){
        return (int)dimesCount ;
    }
    public int getNickels(){
        return (int)nickelsCount;
    }
    public int getPennies(){
        return (int)penniesCount;
    }
    
    public void setItemPrice(String itemPrice){
        this.itemPrice = new BigDecimal(itemPrice).setScale(2,RoundingMode.HALF_UP);
    }
    public String getTotal(){
        return stringTotal;
    }
    
    public void updateTotal(String funds){
        //update coins here;
        additionalFunds = new BigDecimal(funds).setScale(2,RoundingMode.HALF_UP);
        total = myMath.calculate(MathOperator.PLUS, total, additionalFunds);
        //total += additionalFunds;
        stringTotal = total.toString();
    }
    public void resetTotal(){
        quartersCount = 0;
        dimesCount = 0;
        nickelsCount = 0;
        stringTotal = "";       
    }
    public String getChange(){
        String change = "";
        change = myMath.calculate(MathOperator.MINUS, total, itemPrice).toString();
        
        totalDouble = (Double.valueOf(change))*100;
        
        quartersCount = (totalDouble / quarter);
        updateQuarters((int) quartersCount);        
        totalDouble %= quarter;
        
        dimesCount = (totalDouble / dimes);
        updateDimes((int) dimesCount);
        totalDouble %= dimes;
        
        nickelsCount = (totalDouble / nickels);
        updateNickels((int) nickelsCount);
        totalDouble %= nickels;
        
        penniesCount = (totalDouble / pennies);
        updatePennies((int) penniesCount);
        totalDouble %= pennies;
        
        
        
        return change;
    }
    public boolean isSufficient(){
        //checks if user entered enough money
        //calculate if enough use BigDecimal here
       
        if (total.compareTo(itemPrice) >= 0)
            return true;
            
        return false;
    }
    
}

/* 
    To-do List:
        - add total
        - calculate change for user given based on item cost
        - fix big decimal

*/