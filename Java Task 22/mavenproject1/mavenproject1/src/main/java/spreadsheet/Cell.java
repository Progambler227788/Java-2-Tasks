package spreadsheet;

public class Cell {
    private Coordinate coordinate;
    private Content content;
    private double value; // Add this field to store the evaluated value of a formula

    public Cell(Coordinate coordinate, Content content) {
        this.coordinate = coordinate;
        this.content = content;
        this.value = Double.NaN; // Default to NaN to indicate no value has been computed yet
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    // Add these methods
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
