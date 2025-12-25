package org.gbl.gui.awt.components.symbol;

public enum Symbol {
    ZERO("0", Type.DIGIT),
    ONE("1", Type.DIGIT),
    TWO("2", Type.DIGIT),
    THREE("3", Type.DIGIT),
    FOUR("4", Type.DIGIT),
    FIVE("5", Type.DIGIT),
    SIX("6", Type.DIGIT),
    SEVEN("7", Type.DIGIT),
    EIGHT("8", Type.DIGIT),
    NINE("9", Type.DIGIT),
    DOT(".", Type.DIGIT),
    LPAREN("(", Type.DIGIT),
    RPAREN(")", Type.DIGIT),

    ADD("+", Type.OPERATOR),
    SUB("-", Type.OPERATOR),
    MUL("*", Type.OPERATOR),
    DIV("/", Type.OPERATOR),
    POW("^", Type.OPERATOR),
    MOD("%", Type.OPERATOR),
    SIN("sin", Type.OPERATOR),
    COS("cos", Type.OPERATOR),
    TAN("tan", Type.OPERATOR),
    SQRT("√", Type.OPERATOR),
    LOG("log", Type.OPERATOR),
    EXP("exp", Type.OPERATOR),

    EQUALS("=", Type.ACTION),
    CLEAR("CE", Type.ACTION),
    BACKSPACE("←", Type.ACTION),
    INVERT("+/-", Type.ACTION),
    ABS("abs", Type.ACTION);

    public enum Type {
        DIGIT,
        OPERATOR,
        ACTION
    }

    private final String text;
    private final Type type;

    Symbol(String text, Type type) {
        this.text = text;
        this.type = type;
    }

    public String text() {
        return text;
    }

    public Type type() {
        return type;
    }
}
