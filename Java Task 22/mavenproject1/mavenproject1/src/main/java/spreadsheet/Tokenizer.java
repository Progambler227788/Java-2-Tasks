package spreadsheet;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private static final Pattern TOKEN_PATTERNS = Pattern.compile(
        "\\d+(\\.\\d+)?|[a-zA-Z_][a-zA-Z_0-9]*|[+\\-*/^()]|;|:"
    );

    public List<Token> tokenize(String formula) {
        List<Token> tokens = new ArrayList<>();
        Matcher matcher = TOKEN_PATTERNS.matcher(formula);

        while (matcher.find()) {
            String token = matcher.group();
            tokens.add(determineTokenType(token));
        }

        return tokens;
    }

    private Token determineTokenType(String token) {
        if (token.matches("\\d+(\\.\\d+)?")) {
            return new Token(Token.TokenType.NUMBER, token);
        } else if (token.matches("[+\\-*/^()]")) {
            return new Token(Token.TokenType.OPERATOR, token);
        } else if (token.matches(";")) {
            return new Token(Token.TokenType.SEMICOLON, token);
        } else if (token.matches("[a-zA-Z_][a-zA-Z_0-9]*")) {
            if (isFunctionName(token)) {
                return new Token(Token.TokenType.FUNCTION, token);
            }
            return new Token(Token.TokenType.IDENTIFIER, token);
        } else if (token.matches("[()]")) {
            return new Token(Token.TokenType.PARENTHESIS, token);
        } else if (token.matches(":")) {
            return new Token(Token.TokenType.COLON, token);
        } else {
            throw new IllegalArgumentException("Unknown token: " + token);
        }
    }

    private boolean isFunctionName(String token) {
        return Arrays.asList("SUMA", "MIN", "MAX", "PROMEDIO").contains(token.toUpperCase());
    }
}
