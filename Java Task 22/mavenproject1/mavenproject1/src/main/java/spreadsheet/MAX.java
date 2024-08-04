package spreadsheet;

import java.util.List;

public class MAX extends Function {
    public MAX(List<Content> arguments) {
        super(arguments);
    }

    @Override
    public double calculate(List<Content> arguments) {
        double max = Double.NEGATIVE_INFINITY;
        for (Content content : arguments) {
            if (content instanceof Numerical) {
                max = Math.max(max, ((Numerical) content).getValue());
            }
        }
        return max;
    }

    @Override
    public Double getValue() {
        return calculate(getArguments());
    }
}
