package edu.upc.etsetb.arqsoft.spreadsheet.entities;

public class Text extends Content {
    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public String getValue() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
