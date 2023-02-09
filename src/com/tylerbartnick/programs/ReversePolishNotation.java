package com.tylerbartnick.programs;

import com.tylerbartnick.datastructures.Stack;

import java.util.List;

/**
 * A Reverse Polish Notation calculator to be used as a standalone CLI program.
 * The program expects data entered in standard RPN fashion, without respect to other variances of the notation.
 * As such, the only format accepted is "operand operand operator", but that can be repeated endlessly so long as there is
 * enough heap memory to support the stack. Important to note that the first evaluated expression will always become the first
 * operand for the next sequence of operands and operator.
 *
 * @author Tyler Bartnick
 * @version 1.0.0
 */
public final class ReversePolishNotation {
    /**
     * The standard set of operators to be used for arithmetic.
     */
    private static final List<Character> operatorTokens = List.of('+', '-', '*', '/', '_', '^');

    /**
     * The stack to hold all data once validated.
     */
    private static final Stack<String> stack = new Stack<>();

    /**
     * The main entry point into the program. Expects CLI args to be present, fails silently without output otherwise.
     * @param args The whitespace-delimited expression to be evaluated
     */
    public static void main(String[] args) {
        if (args.length >= 3) { return; }
        if (!tokenize(args)) { return; }
        process();
    }

    /**
     * Takes the CLI arguments and adds them to the stack. Validations are done on the fly.
     * @param args The CLI arguments to be evaluated as an expression
     * @return true if the expression is valid, false otherwise
     * @throws IllegalArgumentException Thrown if supplied arguments are not satisfactory
     */
    private static boolean tokenize(String[] args) throws IllegalArgumentException {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Arguments cannot be null or empty.");
        }

        try {
            // reverse traversal to ensure proper order of operations are followed, cheaper than reversing a list
            for (int i = args.length - 1; i >= 0; i--) {
                if (operatorTokens.contains(args[i].charAt(0))) {
                    stack.push(args[i]);
                    continue;
                }
                // if the following conversion fails, it will throw a NumberFormatException, be caught, and exit as expected
                double temp = Double.parseDouble(args[i]);
                stack.push(args[i]);
            }
        } catch (Exception ex) {
            // whatever input was sent into the program is invalid, we can't continue. Fail gracefully.
            stack.clear();
            return false;
        }
        return true;
    }

    /**
     * Processes the data that has been parsed and validated to determine the solution to the supplied expression.
     * @throws IllegalArgumentException Thrown if supplied arguments are not satisfactory
     */
    private static void process() throws IllegalArgumentException {
        if (stack.empty()) { return; }
        while (stack.count() >= 3) {
            // we will always have two operands and an operator
            String token1 = stack.pop(); // operand 1
            String token2 = stack.pop(); // operand 2
            String token3 = stack.pop(); // operator

            if (!operatorTokens.contains(token3.charAt(0))) {
                throw new IllegalArgumentException(String.format("Token %s is not a valid operator.", token3));
            }

            double answer = calculate(token1, token2, token3);
            stack.push(String.valueOf(answer));
        }

        // output final answer
        System.out.println(stack.pop());
    }

    /**
     * Evaluates and returns the result of the operands and operator. Division by zero is protected against.
     * @param token1 The first operand
     * @param token2 The second operand
     * @param token3 The operator
     * @return The result of the expression
     */
    private static double calculate(String token1, String token2, String token3) {
        double dToken1 = Double.parseDouble(token1);
        double dToken2 = Double.parseDouble(token2);
        switch (token3) {
            case "+" -> {
                return dToken1 + dToken2;
            }
            case "-" -> {
                return dToken1 - dToken2;
            }
            case "*" -> {
                return dToken1 * dToken2;
            }
            case "/" -> {
                // Don't allow divide by zero
                return dToken1 / (dToken2 == 0.0d ? 1.0d : dToken2);
            }
            case "_" -> {
                // Don't allow divide by zero
                return Math.floorDiv((int) dToken1, (int) (dToken2 == 0.0d ? 1.0d : dToken2));
            }
            case "^" -> {
                return Math.pow(dToken1, dToken2);
            }
            default -> {
                // should never happen
                return 0.0d;
            }
        }
    }
}
