/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acalvillo105.vendingmachine.service;

import acalvillo105.vendingmachine.dao.ClassVendingMachineAuditDao;
import acalvillo105.vendingmachine.dao.ClassVendingMachinePersistenceException;
import acalvillo105.vendingmachine.dao.classVendingMachineDao;
import acalvillo105.vendingmachine.dao.classVendingMachineMoneyDao;
import acalvillo105.vendingmachine.dto.Item;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author acalvillo
 */


public class ClassVendingMachineServiceLayerImpl implements ClassVendingMachineServiceLayer{
    private classVendingMachineDao dao;
    private classVendingMachineMoneyDao moneyDao;
    private ClassVendingMachineAuditDao auditDao;
    
  
    @Autowired
    public ClassVendingMachineServiceLayerImpl(classVendingMachineDao dao, classVendingMachineMoneyDao moneyDao, ClassVendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.moneyDao = moneyDao;
        this.auditDao = auditDao;
    }

    
    
    @Override
    public void updateItemInventory(String vendedItemId) throws NoItemInventoryException {
        dao.updateItemInventory(vendedItemId);
        String currentItemName = dao.getItemName(vendedItemId);
        try {
            auditDao.writeAuditEntry(
                    "Item " + vendedItemId + ": " + currentItemName + " VENDED.");
        } catch (ClassVendingMachinePersistenceException ex) {
            Logger.getLogger(ClassVendingMachineServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Item> getItemInventory() throws ClassVendingMachinePersistenceException {
        return dao.getItemInventory();
    }

    @Override
    public String getTotal() throws InsufficientFundsException {
        return moneyDao.getTotal();
    }
    
    private void validateMenuSelection(Item selection)throws NoItemInventoryException{
        //will check what happens when a student selects an item that is out of stock;
        if(selection.getItemInventory().equals("0")){
            throw new NoItemInventoryException("Error: Item is out of stock.");
        }
        
    }

    @Override
    public String getItemPrice(String itemId) {
        return dao.getItemPrice(itemId);
    }

    @Override
    public String getItemName(String itemId) {
        return dao.getItemName(itemId);
    }
}
