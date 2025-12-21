package org.gbl.gui;

import org.gbl.calculator.Calculator;

public class ViewController {

    private final Calculator calculator;
    private final CalculatorView view;
    private final StringBuilder input;
    private boolean showingResult;

    public ViewController(Calculator calculator, CalculatorView view) {
        this.calculator = calculator;
        this.view = view;
        this.input = new StringBuilder();
        this.showingResult = false;
    }

    public void show() {
        view.show();
    }

    public void handle(CalculatorInput input) {
        switch (input) {
            case CalculatorInput.Clear c -> clear();
            case CalculatorInput.Backspace b -> backspace();
            case CalculatorInput.Evaluate e -> evaluate();
            case CalculatorInput.Digit digit -> appendLabel(digit.value());
            case CalculatorInput.Operator operator -> appendLabel(operator.value());
        }
    }

    private void appendLabel(String label) {
        if (showingResult) {
            if (isDigit(label)) {
                input.setLength(0); // start new expression
            }
            showingResult = false;
        }
        input.append(label);
        view.showInput(input.toString());
    }

    private boolean isDigit(String value) {
        return value.length() == 1 && Character.isDigit(value.charAt(0));
    }

    private void backspace() {
        if (!input.isEmpty()) {
            input.deleteCharAt(input.length() - 1);
            view.showInput(input.toString());
        }
        showingResult = false;
    }

    private void clear() {
        clearInputBuffer();
        showingResult = false;
        view.clearText();
    }

    private void evaluate() {
        try {
            double result = calculator.calculate(input.toString());
            view.showResult(result);
            clearInputBuffer();
            input.append(result);   // ⭐ carry result forward
            showingResult = true;
        } catch (Exception e) {
            view.showError(e);
            clearInputBuffer();
            showingResult = false;
        }
    }

    private void clearInputBuffer() {
        input.setLength(0);
    }
}
