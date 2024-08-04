package edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker;

public class SpreadsheetFactory {

    public static ISpreadsheetControllerForChecker createSpreadsheetController() {
        return new SpreadsheetController();
    }
}
