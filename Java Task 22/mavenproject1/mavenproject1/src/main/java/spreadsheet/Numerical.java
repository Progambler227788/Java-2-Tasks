package spreadsheet;

public class Numerical extends Content {
    private double value;

    public Numerical(double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
