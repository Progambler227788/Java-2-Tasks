package edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;

public interface ISpreadsheetControllerForChecker {
    void setCellContent(String cell, String content) throws CircularDependencyException;
    String getCellContent(String cell);
    double getCellContentAsDouble(String cell);
    String getCellContentAsString(String cell);
    String getCellFormulaExpression(String cell);
    void readSpreadSheetFromFile(String filename);
    void saveSpreadSheetToFile(String filename);
}
