package edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker;

public class ISpreadsheetFactoryForChecker {
    public static ISpreadsheetControllerForChecker createSpreadsheetController() {
        return new SpreadsheetController();
    }
}
