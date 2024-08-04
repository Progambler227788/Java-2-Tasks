package spreadsheet;

import java.util.List;

public abstract class Function extends Content {
    private List<Content> arguments;

    public Function(List<Content> arguments) {
        this.arguments = arguments;
    }

    protected List<Content> getArguments() {
        return arguments;
    }

    public abstract double calculate(List<Content> arguments);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + arguments.toString() + ")";
    }
}



interface Parameter {
    Value getValue();
}

class Range implements Parameter {
    private Coordinate start;
    private Coordinate end;
    private Spreadsheet spreadsheet;

    public Range(Coordinate start, Coordinate end, Spreadsheet spreadsheet) {
        this.start = start;
        this.end = end;
        this.spreadsheet = spreadsheet;
    }

    @Override
    public Value getValue() {
        double sum = 0;
        for (int row = start.getRow(); row <= end.getRow(); row++) {
            for (char col = start.getColumn().charAt(0); col <= end.getColumn().charAt(0); col++) {
                String cellCoordinate = col + Integer.toString(row);
                try {
                    sum += spreadsheet.getCellValue(cellCoordinate);
                } catch (Exception e) {
                    System.err.println("Error evaluating range " + start + ":" + end + ": " + e.getMessage());
                }
            }
        }
        return new MyNumber(sum);
    }
}
