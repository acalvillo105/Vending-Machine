/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package acalvillo105.vendingmachine;

import acalvillo105.vendingmachine.controller.ClassVendingMachineController;
import acalvillo105.vendingmachine.dao.ClassVendingMachinePersistenceException;
import acalvillo105.vendingmachine.service.NoItemInventoryException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 * @author acalvillo
 */
public class VendingMachine {

    public static void main(String[] args) throws ClassVendingMachinePersistenceException, NoItemInventoryException {
        
        ApplicationContext ctx =  new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassVendingMachineController controller = 
        ctx.getBean("controller", ClassVendingMachineController.class);
        controller.run();       
        
    }
}
