/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package acalvillo105.vendingmachine.service;

import acalvillo105.vendingmachine.dao.ClassVendingMachineDaoFileImpl;
import acalvillo105.vendingmachine.dao.ClassVendingMachinePersistenceException;
import acalvillo105.vendingmachine.dao.classVendingMachineDao;
import acalvillo105.vendingmachine.dto.Item;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author acalvillo
 */
public class ClassVendingMachineServiceLayerImplTest {
    
    classVendingMachineDao testDao;
    
    public ClassVendingMachineServiceLayerImplTest() {
    }

    //setting up the test file 
    @BeforeEach
    public void testSomeMethod() throws IOException {
        String testFile = "testroster.txt";
        new FileWriter(testFile);
        testDao = new ClassVendingMachineDaoFileImpl(testFile);
    }
    
    //create new 
    @Test
    public void testUpdateItem() throws ClassVendingMachinePersistenceException{
        List<Item> itemList = testDao.getItemInventory();
        int countBefore = 0;
        int countAfter = 0;
        for(Item currentItem: itemList){
           countBefore = Integer.parseInt(currentItem.getItemInventory());
           testDao.updateItemInventory(currentItem.getItemId());
           countAfter = Integer.parseInt(currentItem.getItemInventory()) + 1; //should equal the one before
           assertEquals(countBefore, countAfter);
        }
    }
    
    
}
