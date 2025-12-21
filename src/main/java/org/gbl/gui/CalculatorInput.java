package org.gbl.gui;

import org.gbl.gui.CalculatorInput.Backspace;
import org.gbl.gui.CalculatorInput.Clear;
import org.gbl.gui.CalculatorInput.Digit;
import org.gbl.gui.CalculatorInput.Evaluate;
import org.gbl.gui.CalculatorInput.Operator;

public sealed interface CalculatorInput
        permits Digit, Operator, Evaluate, Clear, Backspace {

    record Digit(String value) implements CalculatorInput {}

    record Operator(String value) implements CalculatorInput {}

    record Evaluate() implements CalculatorInput {}

    record Clear() implements CalculatorInput {}

    record Backspace() implements CalculatorInput {}
}
