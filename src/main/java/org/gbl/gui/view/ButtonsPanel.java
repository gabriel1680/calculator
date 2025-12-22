package org.gbl.gui.view;

import org.gbl.gui.controller.CalculatorInput;

import java.awt.*;
import java.util.function.Consumer;

class ButtonsPanel implements Presentable {
    private final Panel panel;

    ButtonsPanel(Consumer<CalculatorInput> buttonPressedConsumer) {
        this.panel = createPanel(buttonPressedConsumer);
    }

    private static Panel createPanel(Consumer<CalculatorInput> consumer) {
        Panel buttonsPanel = new Panel(new GridLayout(6, 4, 5, 5));
        String[] labels = {
                "←", "+/-", "%", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "CE", "0", ".", "=", "(", ")"};
        for (String label : labels) {
            final var button = createButton(consumer, label);
            buttonsPanel.add(button);
        }
        return buttonsPanel;
    }

    private static Button createButton(Consumer<CalculatorInput> consumer, String label) {
        Button button = new Button(label);
        button.addActionListener(e -> {
            final var input = mapLabel(label);
            consumer.accept(input);
        });
        return button;
    }

    private static CalculatorInput mapLabel(String label) {
        return switch (label) {
            case "+/-" -> new CalculatorInput.InvertSignal();
            case "CE" -> new CalculatorInput.Clear();
            case "←" -> new CalculatorInput.Backspace();
            case "=" -> new CalculatorInput.Evaluate();
            case "+", "-", "*", "/", "(", ")", "%" -> new CalculatorInput.Operator(label);
            default -> new CalculatorInput.Digit(label);
        };
    }

    @Override
    public Component getComponent() {
        return panel;
    }
}
