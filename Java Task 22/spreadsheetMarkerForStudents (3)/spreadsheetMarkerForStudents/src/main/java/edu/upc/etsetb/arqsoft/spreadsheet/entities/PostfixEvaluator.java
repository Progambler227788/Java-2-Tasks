package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.*;

public class PostfixEvaluator {
    public double evaluate(List<Token> postfixTokens, Spreadsheet spreadsheet) throws Exception {
        Deque<Double> stack = new LinkedList<>();

        for (Token token : postfixTokens) {
            String value = token.getValue();

            if (isNumeric(value)) {
                stack.push(Double.parseDouble(value));
            } else if (isCellReference(value)) {
                stack.push(spreadsheet.getCellValue(value));
            } else if (isFunction(value)) {
                List<Double> args = new ArrayList<>();
                while (!stack.isEmpty()) {
                    args.add(0, stack.pop());
                }
                double result = evaluateFunction(value, args);
                stack.push(result);
            } else if (isOperator(value)) {
                double b = stack.pop();
                double a = stack.pop();
                double result = evaluateOperator(a, b, value);
                stack.push(result);
            } else {
                throw new Exception("Unknown token: " + value);
            }
        }

        return stack.pop();
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isCellReference(String str) {
        return str.matches("[A-Z]+[0-9]+");
    }

    private boolean isFunction(String str) {
        return Arrays.asList("SUMA", "MIN", "MAX", "PROMEDIO").contains(str.toUpperCase());
    }

    private boolean isOperator(String str) {
        return Arrays.asList("+", "-", "*", "/").contains(str);
    }

    private double evaluateFunction(String functionName, List<Double> args) {
        switch (functionName.toUpperCase()) {
            case "MIN":
                return args.stream().min(Double::compare).orElse(Double.NaN);
            case "MAX":
                return args.stream().max(Double::compare).orElse(Double.NaN);
            case "SUMA":
                return args.stream().mapToDouble(Double::doubleValue).sum();
            case "PROMEDIO":
                return args.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
            default:
                throw new UnsupportedOperationException("Unknown function: " + functionName);
        }
    }

    private double evaluateOperator(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            default:
                throw new UnsupportedOperationException("Unknown operator: " + operator);
        }
    }
}
