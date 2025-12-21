package org.gbl.gui.components;

import org.gbl.calculator.Calculator;

import java.util.HashMap;
import java.util.Map;

public class ViewController {

    private final Calculator calculator;
    private final Display display;
    private final StringBuilder input;

    private final Map<String, Runnable> actions;

    public ViewController(Calculator calculator, Display display) {
        this.calculator = calculator;
        this.display = display;
        this.input = new StringBuilder();

        actions = new HashMap<>();
        actions.put("CE", this::clear);
        actions.put("←", this::backspace);
        actions.put("=", this::evaluate);
    }

    public void handleInput(String label) {
        Runnable action = actions.get(label);
        if (action != null) {
            action.run();
        } else {
            appendLabel(label);
        }
    }

    private void appendLabel(String label) {
        input.append(label);
        display.setText(input.toString());
    }

    private void backspace() {
        if (!input.isEmpty()) {
            input.deleteCharAt(input.length() - 1);
            display.setText(input.toString());
        }
    }

    private void clear() {
        input.setLength(0);
        display.setText("");
    }

    private void evaluate() {
        try {
            double result = calculator.calculate(input.toString());
            display.setText(String.valueOf(result));
            input.setLength(0);
        } catch (Exception e) {
            display.setText("Error: " + e.getMessage());
            input.setLength(0);
        }
    }
}
