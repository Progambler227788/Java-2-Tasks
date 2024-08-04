package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.List;
import java.util.Stack;

public class Parser {
    public boolean parse(List<Token> tokens) {
        int parenthesisCount = 0;
        Stack<Token> stack = new Stack<>();

        for (Token token : tokens) {
            if (token.getType() == Token.TokenType.PARENTHESIS) {
                if (token.getValue().equals("(")) {
                    parenthesisCount++;
                    stack.push(token);
                } else if (token.getValue().equals(")")) {
                    parenthesisCount--;
                    if (parenthesisCount < 0 || stack.isEmpty()) {
                        return false;
                    }
                    stack.pop();
                }
            }
        }

        return parenthesisCount == 0 && stack.isEmpty();
    }
}
