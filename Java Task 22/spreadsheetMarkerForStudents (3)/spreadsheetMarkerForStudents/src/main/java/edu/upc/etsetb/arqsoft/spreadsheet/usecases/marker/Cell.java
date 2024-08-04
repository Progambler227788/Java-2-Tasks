package edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.Coordinate;

public class Cell {
    private String content;

    public Cell(String content) {
        this.content = content;
    }

    public Cell(Coordinate coordinate, Content content) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public double getContentAsDouble() {
        // Assume this converts the content to double if it's a number or formula result
        try {
            if (content.startsWith("=")) {
                // Evaluate the formula
                return evaluateFormula(content.substring(1));
            } else {
                return Double.parseDouble(content);
            }
        } catch (Exception e) {
            return Double.NaN; // or throw an exception
        }
    }

    public String getContentAsString() {
        return content;
    }

    public String getFormulaExpression() {
        return content.startsWith("=") ? content.substring(1) : content;
    }

    private double evaluateFormula(String formula) {
        // Simple implementation of formula evaluation
        // This would need to be expanded to fully support spreadsheet formulas
        // For now, it can handle simple arithmetic
        try {
            return new javax.script.ScriptEngineManager().getEngineByName("JavaScript").eval(formula).hashCode();
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    public Object getContent() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setValue(double evaluateFormula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
