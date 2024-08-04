package edu.upc.etsetb.arqsoft.spreadsheet.entities;

public class Cell {
    private String content;

    public Cell(String content) {
        this.content = content;
    }

    public String getContentAsString() {
        return content;
    }

    public String getFormulaExpression() {
        return content.startsWith("=") ? content : null;
    }
}
