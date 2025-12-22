package org.gbl.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

class ExpressionEvaluator {

    private final char separator;
    private final SimpleCalculator calculator;

    ExpressionEvaluator(char separator) {
        this.separator = separator;
        this.calculator = new SimpleCalculator();
    }

    double evaluate(final String postfixExpression) {
        final String[] tokens = postfixExpression.split(String.valueOf(separator));
        final Deque<Double> stack = new ArrayDeque<>();
        for (String token : tokens)
            evaluateTokenInPlace(token, stack);
        return stack.pop();
    }

    private void evaluateTokenInPlace(String token, Deque<Double> stack) {
        final var firstCharacter = token.charAt(0);
        if (Character.isDigit(firstCharacter)) {
            stack.push(Double.parseDouble(token));
            return;
        }
        final double b = stack.pop();
        final double a = stack.pop();
        stack.push(compute(firstCharacter, a, b));
    }

    private double compute(char operator, double a, double b) {
        return switch (operator) {
            case '+' -> calculator.sum(a, b);
            case '-' -> calculator.subtract(a, b);
            case '*' -> calculator.multiply(a, b);
            case '/' -> calculator.divide(a, b);
            case '%' -> calculator.module(a, b);
            default ->
                    throw new IllegalArgumentException("Unknown operator: '%s'".formatted(operator));
        };
    }
}
