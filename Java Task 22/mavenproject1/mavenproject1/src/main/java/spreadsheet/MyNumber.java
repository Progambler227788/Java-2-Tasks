package spreadsheet;

public class MyNumber extends Value {
    private double number;

    public MyNumber(double number) {
        this.number = number;
    }

    @Override
    public Double getValue() {
        return number;
    }

    @Override
    public String toString() {
        return Double.toString(number);
    }
}
