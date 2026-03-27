import java.util.List;
import java.util.Scanner;

/**
 * Main.java
 * Entry point for the Mini Expression Compiler.
 *
 * Runs the full compilation pipeline:
 *   1. Lexer       — tokenizes the input expression
 *   2. Parser      — builds an AST via recursive descent
 *   3. Evaluator   — traverses the AST and computes the result
 *
 * Prints a full trace of each phase to the console.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("       Mini Expression Compiler v1.0         ");
        System.out.println("==============================================");
        System.out.println("Type an arithmetic expression and press Enter.");
        System.out.println("Type 'exit' to quit.\n");

        while (true) {
            System.out.print("Enter expression: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (input.isEmpty()) continue;

            try {
                // ── Phase 1: Lexical Analysis ──────────────────────
                Lexer lexer = new Lexer(input);
                List<Token> tokens = lexer.tokenize();

                System.out.println("\n[Phase 1] Tokens:");
                System.out.println("  " + tokens);

                // ── Phase 2: Parsing ───────────────────────────────
                Parser parser = new Parser(tokens);
                ASTNode root = parser.parse();

                System.out.println("\n[Phase 2] Parse: Success");
                System.out.println("\n[Phase 3] Expression Tree:");
                System.out.print(root.toTreeString("", true));

                // ── Phase 3: Evaluation ────────────────────────────
                Evaluator evaluator = new Evaluator();
                double result = evaluator.evaluate(root);

                // Print result — show as integer if no decimal part
                System.out.println("\n[Phase 4] Evaluation Result: " +
                    (result == Math.floor(result) && !Double.isInfinite(result)
                        ? (long) result
                        : result));

            } catch (RuntimeException e) {
                System.out.println("\n[Error] " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }
}
