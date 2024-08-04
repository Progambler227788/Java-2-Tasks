package spreadsheet;

import java.util.List;

public class MIN extends Function {
    public MIN(List<Content> arguments) {
        super(arguments);
    }

    @Override
    public double calculate(List<Content> arguments) {
        double min = Double.POSITIVE_INFINITY;
        for (Content content : arguments) {
            if (content instanceof Numerical) {
                min = Math.min(min, ((Numerical) content).getValue());
            }
        }
        return min;
    }

    @Override
    public Double getValue() {
        return calculate(getArguments());
    }
}
