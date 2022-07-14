/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package acalvillo105.vendingmachine.dao;

import acalvillo105.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author acalvillo
 */
public interface classVendingMachineDao {
    void updateItemInventory(String vendedItemId);
    List<Item> getItemInventory() throws ClassVendingMachinePersistenceException;
    String getItemPrice(String itemId);
    String getItemName(String itemId);
}
