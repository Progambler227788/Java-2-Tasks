/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spreadsheet;

/**
 *
 * @author farahpoor
 */
import java.util.List;

interface Operand {
    Value evaluate();
}

interface Operator {
    Value apply(List<Operand> operands);
}

