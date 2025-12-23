package org.gbl.calculator;

public enum Operator {
    SUM('+'),
    SUBTRACTION('-'),
    DIVISION('/'),
    MULTIPLICATION('*'),
    MOD('%'),
    SQUARE_ROOT('√');

    private final char value;

    Operator(char value) {
        this.value = value;
    }

    public char symbol() {
        return value;
    }

    public static boolean isOperator(char c) {
        for (var operator : values()) {
            if (operator.value == c) return true;
        }
        return false;
    }

    public static Operator fromChar(char c) {
        for (var operator : values()) {
            if (operator.value == c) return operator;
        }
        throw new IllegalArgumentException("Unknown operator: '%s'".formatted(c));
    }
}
