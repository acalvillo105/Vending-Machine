/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package acalvillo105.vendingmachine.dao;

/**
 *
 * @author acalvillo
 */
public interface ClassVendingMachineAuditDao {
    public void writeAuditEntry(String entry) throws ClassVendingMachinePersistenceException;
}
