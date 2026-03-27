import java.util.List;

/**
 * Parser.java
 * Implements a recursive descent parser based on the grammar:
 *
 *   E  -> T (('+' | '-') T)*
 *   T  -> F (('*' | '/') F)*
 *   F  -> '-' F | '(' E ')' | NUMBER
 *
 * Builds and returns an AST (ASTNode) from the token list.
 */
public class Parser {

    private final List<Token> tokens;
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.pos = 0;
    }

    // Returns the current token without consuming it
    private Token peek() {
        return tokens.get(pos);
    }

    // Consumes and returns the current token
    private Token consume() {
        return tokens.get(pos++);
    }

    // Consumes the current token only if it matches the expected type
    private Token expect(Token.Type type) {
        Token token = consume();
        if (token.type != type) {
            throw new RuntimeException(
                "Parse error: expected " + type + " but found '" + token.value + "' at position " + (pos - 1)
            );
        }
        return token;
    }

    /**
     * Entry point — parses the full expression and returns the root AST node.
     */
    public ASTNode parse() {
        ASTNode result = parseExpression();
        if (peek().type != Token.Type.EOF) {
            throw new RuntimeException(
                "Parse error: unexpected token '" + peek().value + "' at position " + pos
            );
        }
        return result;
    }

    /**
     * E -> T (('+' | '-') T)*
     */
    private ASTNode parseExpression() {
        ASTNode left = parseTerm();

        while (peek().type == Token.Type.PLUS || peek().type == Token.Type.MINUS) {
            String op = consume().value;
            ASTNode right = parseTerm();
            left = new ASTNode(op, left, right);
        }

        return left;
    }

    /**
     * T -> F (('*' | '/') F)*
     */
    private ASTNode parseTerm() {
        ASTNode left = parseFactor();

        while (peek().type == Token.Type.STAR || peek().type == Token.Type.SLASH) {
            String op = consume().value;
            ASTNode right = parseFactor();
            left = new ASTNode(op, left, right);
        }

        return left;
    }

    /**
     * F -> '-' F | '(' E ')' | NUMBER
     * Handles unary minus and parenthesized expressions.
     */
    private ASTNode parseFactor() {
        Token current = peek();

        // Unary minus: treated as (0 - x)
        if (current.type == Token.Type.MINUS) {
            consume();
            ASTNode operand = parseFactor();
            return new ASTNode("-", new ASTNode("0"), operand);
        }

        // Parenthesized expression
        if (current.type == Token.Type.LPAREN) {
            consume(); // consume '('
            ASTNode node = parseExpression();
            expect(Token.Type.RPAREN); // consume ')'
            return node;
        }

        // Number
        if (current.type == Token.Type.NUMBER) {
            consume();
            return new ASTNode(current.value);
        }

        throw new RuntimeException(
            "Parse error: unexpected token '" + current.value + "' at position " + pos
        );
    }
}
