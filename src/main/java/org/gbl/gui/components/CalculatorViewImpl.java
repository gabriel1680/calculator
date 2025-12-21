package org.gbl.gui.components;

import org.gbl.gui.CalculatorInput;
import org.gbl.gui.CalculatorView;

import java.awt.*;
import java.util.function.Consumer;

public class CalculatorViewImpl implements CalculatorView {

    private final ContainerFrame frame;
    private Consumer<CalculatorInput> inputListener;
    private final Display display;

    public CalculatorViewImpl() {
        frame = new ContainerFrame();
        display = new Display();
        frame.add(display, BorderLayout.NORTH);

        final var buttonsPanel = new ButtonsPanel(this::handleInput);
        frame.add(buttonsPanel, BorderLayout.CENTER);
    }

    private void handleInput(CalculatorInput input) {
        if (inputListener != null) {
            inputListener.accept(input);
        }
    }

    @Override
    public void showText(String text) {
        display.setText(text);
    }

    @Override
    public void clearText() {
        display.setText("");
    }


    @Override
    public void onInput(Consumer<CalculatorInput> listener) {
        this.inputListener = listener;
    }

    @Override
    public void show() {
        frame.show();
    }
}
