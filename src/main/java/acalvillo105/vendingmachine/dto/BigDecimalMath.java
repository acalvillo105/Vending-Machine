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
public class BigDecimalMath {
    public BigDecimal calculate(MathOperator operator, BigDecimal operand1, BigDecimal operand2){
        
        switch(operator){
            case PLUS:
                return operand1.add(operand2);
            case MINUS:
                return operand1.subtract(operand2);
            case MULTIPLY:
                return operand1.multiply(operand2);
            case DIVIDE:
                return operand1.divide(operand2, 2, RoundingMode.HALF_UP);
            default:
                throw new UnsupportedOperationException("Unknown Math Operator");
        }
        
        
    }
}
