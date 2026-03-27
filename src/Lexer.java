import java.util.ArrayList;
import java.util.List;

/**
 * Lexer.java
 * Performs lexical analysis (tokenization) on an arithmetic expression string.
 * Converts the input into a list of Token objects.
 *
 * Supported tokens: integers, +, -, *, /, (, )
 * Also handles unary minus (e.g. -3).
 */
public class Lexer {

    private final String input;
    private int pos;

    public Lexer(String input) {
        this.input = input;
        this.pos = 0;
    }

    /**
     * Tokenizes the full input string and returns a list of tokens.
     * The list always ends with an EOF token.
     */
    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (pos < input.length()) {
            char current = input.charAt(pos);

            // Skip whitespace
            if (Character.isWhitespace(current)) {
                pos++;
                continue;
            }

            // Number (multi-digit supported)
            if (Character.isDigit(current)) {
                StringBuilder sb = new StringBuilder();
                while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                    sb.append(input.charAt(pos));
                    pos++;
                }
                tokens.add(new Token(Token.Type.NUMBER, sb.toString()));
                continue;
            }

            // Operators and parentheses
            switch (current) {
                case '+':
                    tokens.add(new Token(Token.Type.PLUS, "+"));
                    break;
                case '-':
                    tokens.add(new Token(Token.Type.MINUS, "-"));
                    break;
                case '*':
                    tokens.add(new Token(Token.Type.STAR, "*"));
                    break;
                case '/':
                    tokens.add(new Token(Token.Type.SLASH, "/"));
                    break;
                case '(':
                    tokens.add(new Token(Token.Type.LPAREN, "("));
                    break;
                case ')':
                    tokens.add(new Token(Token.Type.RPAREN, ")"));
                    break;
                default:
                    throw new RuntimeException("Unknown character: '" + current + "' at position " + pos);
            }
            pos++;
        }

        tokens.add(new Token(Token.Type.EOF, "EOF"));
        return tokens;
    }
}
