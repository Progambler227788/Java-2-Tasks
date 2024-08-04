package spreadsheet;

import java.util.List;

public class SUMA extends Function {
    public SUMA(List<Content> arguments) {
        super(arguments);
    }

    @Override
    public double calculate(List<Content> arguments) {
        double sum = 0;
        for (Content content : arguments) {
            if (content instanceof Numerical) {
                sum += ((Numerical) content).getValue();
            }
        }
        return sum;
    }

    @Override
    public Double getValue() {
        return calculate(getArguments());
    }
}
