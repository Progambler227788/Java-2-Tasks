package spreadsheet;

public class Token {
    public enum TokenType {
        NUMBER, OPERATOR, FUNCTION, IDENTIFIER, PARENTHESIS, COMMA, SEMICOLON, COLON
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
