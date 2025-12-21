package org.gbl.gui.components;

import org.gbl.calculator.Calculator;

import java.awt.*;
import java.awt.event.ActionEvent;

public class CalculatorWindow {

    private final ViewController controller;

    public CalculatorWindow(Calculator calculator) {
        Display display = new Display();
        this.controller = new ViewController(calculator, display);

        final var frame = new ContainerFrame();
        frame.add(display, BorderLayout.NORTH);

        final var buttonsPanel = new ButtonsPanel(this::buttonPressed);
        frame.add(buttonsPanel, BorderLayout.CENTER);

        frame.show();
    }

    private void buttonPressed(ActionEvent e) {
        String label = ((Button) e.getSource()).getLabel();
        controller.handleInput(label);
    }
}
