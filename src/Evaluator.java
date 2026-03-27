/**
 * Evaluator.java
 * Traverses the Abstract Syntax Tree (AST) recursively and computes the result.
 *
 * Supports: +, -, *, / (integer and floating-point results)
 */
public class Evaluator {

    /**
     * Evaluates the given AST node and returns the numeric result.
     */
    public double evaluate(ASTNode node) {
        if (node == null) {
            throw new RuntimeException("Evaluation error: null node encountered.");
        }

        // Leaf node — return its numeric value
        if (node.isLeaf()) {
            try {
                return Double.parseDouble(node.value);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Evaluation error: invalid number '" + node.value + "'");
            }
        }

        // Evaluate left and right subtrees
        double left  = evaluate(node.left);
        double right = evaluate(node.right);

        // Apply the operator
        switch (node.value) {
            case "+": return left + right;
            case "-": return left - right;
            case "*": return left * right;
            case "/":
                if (right == 0) {
                    throw new RuntimeException("Evaluation error: division by zero.");
                }
                return left / right;
            default:
                throw new RuntimeException("Evaluation error: unknown operator '" + node.value + "'");
        }
    }
}
