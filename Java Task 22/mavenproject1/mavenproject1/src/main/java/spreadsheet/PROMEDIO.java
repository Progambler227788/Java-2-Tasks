package spreadsheet;

import java.util.List;

public class PROMEDIO extends Function {
    public PROMEDIO(List<Content> arguments) {
        super(arguments);
    }

    @Override
    public double calculate(List<Content> arguments) {
        double sum = 0;
        int count = 0;
        for (Content content : arguments) {
            if (content instanceof Numerical) {
                sum += ((Numerical) content).getValue();
                count++;
            }
        }
        return count > 0 ? sum / count : 0;
    }

    @Override
    public Double getValue() {
        return calculate(getArguments());
    }
}
