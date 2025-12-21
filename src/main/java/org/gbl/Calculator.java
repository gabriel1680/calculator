package org.gbl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

public class Calculator {

    private static final MathContext PRECISION = MathContext.DECIMAL64;
    private static final char SEPARATOR = ' ';

    public double sum(double a, double b) {
        return toBigDecimal(a).add(toBigDecimal(b), PRECISION).doubleValue();
    }

    public double multiply(double a, double b) {
        return toBigDecimal(a).multiply(toBigDecimal(b), PRECISION).doubleValue();
    }

    public double divide(double a, double b) {
        return toBigDecimal(a).divide(toBigDecimal(b), PRECISION).doubleValue();
    }

    public double subtract(double a, double b) {
        return toBigDecimal(a).subtract(toBigDecimal(b), PRECISION).doubleValue();
    }

    private static BigDecimal toBigDecimal(double a) {
        return BigDecimal.valueOf(a);
    }

    public double calculate(String expression) {
        return evaluate(toPostfixNotation(expression));
    }

    private double evaluate(StringBuilder postfix) {
        final String[] tokens = postfix.toString().split("\\s+");
        final var stack = new Stack<Double>();
        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }
            final var operator = token.charAt(0);
            if (Character.isDigit(operator)) {
                stack.push(Double.parseDouble(token));
                continue;
            }
            final double b = stack.pop();
            final double a = stack.pop();
            stack.push(compute(operator, a, b));
        }
        return stack.pop();
    }

    private double compute(char operator, double a, double b) {
        return switch (operator) {
            case '+' -> sum(a, b);
            case '-' -> subtract(a, b);
            case '*' -> multiply(a, b);
            case '/' -> divide(a, b);
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }

    private static StringBuilder toPostfixNotation(final String expression) {
        final var postfix = new StringBuilder();
        final var operators = new Stack<Character>();
        for (int i = 0; i < expression.length(); i++) {
            final char character = expression.charAt(i);
            if (character == SEPARATOR) {
                continue;
            }
            if (Character.isDigit(character)) {
                final var number = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    number.append(expression.charAt(i));
                    i++;
                }
                i--; // step back one position
                postfix.append(number).append(SEPARATOR);
            } else if (character == '(') {
                operators.push(character);
            } else if (character == ')') {
                while (!operators.isEmpty() && operators.peek() != '(')
                    postfix.append(operators.pop()).append(SEPARATOR);
                operators.pop(); // discard the '(' operator and never adds the ')'
            } else {
                while (!operators.isEmpty() && havePrecedenceOver(operators.peek(), character))
                    postfix.append(operators.pop()).append(SEPARATOR);
                operators.push(character);
            }
        }
        while (!operators.isEmpty()) {
            postfix.append(operators.pop()).append(SEPARATOR);
        }
        return postfix;
    }

    private static boolean havePrecedenceOver(char operation1, char operation2) {
        return precedenceFor(operation1) >= precedenceFor(operation2);
    }

    private static int precedenceFor(Character operation) {
        return switch (operation) {
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> 0;
        };
    }
}
