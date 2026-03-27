/**
 * Token.java
 * Represents a single token produced by the Lexer.
 * Each token has a type and a string value.
 */
public class Token {

    public enum Type {
        NUMBER,
        PLUS,
        MINUS,
        STAR,
        SLASH,
        LPAREN,
        RPAREN,
        EOF
    }

    public final Type type;
    public final String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
