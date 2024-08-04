package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.Objects;

public class Coordinate {
    private final String column;
    private final int row;

    public Coordinate(String coordinateStr) {
        this.column = coordinateStr.replaceAll("[0-9]", "");
        this.row = Integer.parseInt(coordinateStr.replaceAll("[^0-9]", ""));
    }

    public String getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public static String toColumnLabel(int columnIndex) {
        StringBuilder columnLabel = new StringBuilder();
        while (columnIndex > 0) {
            columnIndex--;
            columnLabel.insert(0, (char) ('A' + (columnIndex % 26)));
            columnIndex /= 26;
        }
        return columnLabel.toString();
    }

    public static int columnToIndex(String column) {
        int index = 0;
        for (int i = 0; i < column.length(); i++) {
            index = index * 26 + (column.charAt(i) - 'A' + 1);
        }
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return row == that.row && column.equals(that.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return column + row;
    }
}
