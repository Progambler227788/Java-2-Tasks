package edu.upc.etsetb.arqsoft.spreadsheet.entities;

public class Token {
    public enum TokenType {
        NUMBER, OPERATOR, FUNCTION, IDENTIFIER, PARENTHESIS, SEMICOLON, COLON
    }

    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
