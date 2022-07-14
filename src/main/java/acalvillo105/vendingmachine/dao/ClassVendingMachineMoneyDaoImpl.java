/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acalvillo105.vendingmachine.dao;

import acalvillo105.vendingmachine.dto.Money;


/**
 *
 * @author acalvillo
 */

public class ClassVendingMachineMoneyDaoImpl implements classVendingMachineMoneyDao{
    private Money currentMoney = new Money("0");
    
    @Override
    public void addMoney(String money) {
        currentMoney.updateTotal(money);
    }

    @Override
    public String getTotal() {
        return currentMoney.getTotal();
    }
    
    
    
}
