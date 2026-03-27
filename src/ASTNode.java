/**
 * ASTNode.java
 * Represents a node in the Abstract Syntax Tree (AST).
 *
 * A node is either:
 *   - A NUMBER node (leaf): holds a numeric value
 *   - An OPERATOR node (internal): holds an operator and left/right children
 */
public class ASTNode {

    public final String value;       // number or operator (+, -, *, /)
    public final ASTNode left;
    public final ASTNode right;

    // Constructor for operator nodes
    public ASTNode(String value, ASTNode left, ASTNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    // Constructor for leaf (number) nodes
    public ASTNode(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Returns a text representation of the tree using indentation.
     */
    public String toTreeString(String indent, boolean isLeft) {
        StringBuilder sb = new StringBuilder();
        if (right != null) {
            sb.append(right.toTreeString(indent + (isLeft ? "│   " : "    "), false));
        }
        sb.append(indent).append(isLeft ? "└── " : "┌── ").append(value).append("\n");
        if (left != null) {
            sb.append(left.toTreeString(indent + (isLeft ? "    " : "│   "), true));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if (isLeaf()) return value;
        return "(" + left + " " + value + " " + right + ")";
    }
}
