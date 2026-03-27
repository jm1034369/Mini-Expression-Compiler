# Mini Expression Compiler

A Java-based mini compiler that tokenizes, parses, builds an AST, and evaluates arithmetic expressions.

**Course:** CSC220 - Foundations of Computer Science, West Chester University, Spring 2026

---

## Project Summary

This project simulates the core phases of a real compiler. Given an arithmetic expression as input (e.g., `(3 + 2) * 5 - 1`), the system:

1. **Tokenizes** the input into numbers, operators, and parentheses
2. **Parses** the token stream using recursive descent and a context-free grammar
3. **Builds an Abstract Syntax Tree (AST)** from the parsed expression
4. **Evaluates** the AST to produce the final result
5. **Traces** all phases with console output

---

## Key Concepts

- Lexical Analysis (finite automata-based tokenization)
- Context-Free Grammar with recursive descent parsing
- Abstract Syntax Trees (AST)
- Java OOP and modular design

---

## Project Structure

```
Mini-Expression-Compiler/
├── src/
│   ├── Main.java           # Entry point
│   ├── Lexer.java          # Tokenizer - converts input string to token list
│   ├── Token.java          # Token class (type + value)
│   ├── Parser.java         # Recursive descent parser
│   ├── ASTNode.java        # Abstract Syntax Tree node
│   └── Evaluator.java      # Traverses AST and computes result
└── README.md
```

---

## Grammar

```
E -> E + T | E - T | T
T -> T * F | T / F | F
F -> (E) | number
```

The compiler also supports unary operators (e.g., `-3`) and provides clear error messages for invalid input.

---

## Setup Instructions

**Requirements:** Java JDK 11 or higher

**1. Clone the repository:**
```bash
git clone https://github.com/jm1034369/Mini-Expression-Compiler.git
cd Mini-Expression-Compiler
```

**2. Compile:**
```bash
javac src/*.java -d out/
```

**3. Run:**
```bash
java -cp out Main
```

---

## How to Run with Example Inputs

**Valid expression:**
```
Enter expression: (3 + 2) * 5 - 1

Tokens: [(, 3, +, 2, ), *, 5, -, 1]
Parse:  Success

Tree:
     -
    / \
   *   1
  / \
 +   5
/ \
3   2

Result: 24
```

**Invalid expression:**
```
Enter expression: 3 + * 5

Tokens: [3, +, *, 5]
Error:  Unexpected token '*' at position 2
```

---

## Test Cases

| Expression | Expected Output |
|---|---|
| `3 + 4 * 2` | 11 |
| `(1 + 2) * (3 + 4)` | 21 |
| `((3))` | 3 |
| `-3 + 5` | 2 |
| `3 + * 5` | Syntax error |
| `3 + (4 - )` | Syntax error |

---

## Demo Video

*Coming soon* — 1–3 minute video showcasing tokenization, parsing, tree building, and evaluation.

---

## Author

Joseph Mathew — CSC220, Spring 2026
