package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.*;

public class ShuntingYard {
    public static List<Token> infixToPostfix(List<Token> infixTokens) {
        List<Token> output = new ArrayList<>();
        Deque<Token> operators = new ArrayDeque<>();

        for (Token token : infixTokens) {
            String value = token.getValue();
            if (isNumeric(value) || isCellReference(value)) {
                output.add(token);
            } else if (isFunction(value)) {
                operators.push(token);
            } else if (value.equals("(")) {
                operators.push(token);
            } else if (value.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().getValue().equals("(")) {
                    output.add(operators.pop());
                }
                operators.pop();
                if (!operators.isEmpty() && isFunction(operators.peek().getValue())) {
                    output.add(operators.pop());
                }
            } else if (isOperator(value)) {
                while (!operators.isEmpty() && precedence(operators.peek().getValue()) >= precedence(value)) {
                    output.add(operators.pop());
                }
                operators.push(token);
            } else if (value.equals(";")) {
                while (!operators.isEmpty() && !operators.peek().getValue().equals("(")) {
                    output.add(operators.pop());
                }
            }
        }

        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }

        return output;
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isCellReference(String str) {
        return str.matches("[A-Z]+[0-9]+");
    }

    private static boolean isFunction(String str) {
        return Arrays.asList("SUMA", "MIN", "MAX", "PROMEDIO").contains(str.toUpperCase());
    }

    private static boolean isOperator(String str) {
        return Arrays.asList("+", "-", "*", "/").contains(str);
    }

    private static int precedence(String op) {
        switch (op) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }
}
