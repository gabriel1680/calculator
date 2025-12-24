package org.gbl.gui.integration;

import org.gbl.calculator.Calculator;
import org.gbl.gui.awt.AWTCalculatorView;
import org.gbl.gui.controller.ViewController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class AWTCalculatorDSL {

    public static final String[] BASIC_BUTTONS = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "CE", "←", "+/-", "%"
    };

    public static final String[] SCIENTIFIC_BUTTONS = {
            "sin", "cos", "tan", "√", "(", ")"
    };

    private static AWTCalculatorDSL instance;

    private final AWTCalculatorView view;

    private AWTCalculatorDSL() throws Exception {
        this.view = createViewOnEDT();
        Calculator calculator = new Calculator();
        ViewController controller = new ViewController(calculator, view);
        view.onInput(controller::handle);
    }

    public static AWTCalculatorDSL instance() throws Exception {
        if (instance == null) {
            instance = new AWTCalculatorDSL();
        }
        return instance;
    }

    /**
     * Type a sequence of buttons
     */
    public AWTCalculatorDSL type(String... keys) {
        for (String key : keys) {
            Button button = ComponentFinder.findAll(view.getComponents(), Button.class)
                    .stream()
                    .filter(b -> b.getLabel().equals(key))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("Button '" + key + "' not found"));

            for (var listener : button.getActionListeners()) {
                final var event = new ActionEvent(button, ActionEvent.ACTION_PERFORMED, key);
                listener.actionPerformed(event);
            }
        }
        return this;
    }

    /**
     * Assert the display shows expected value
     */
    public AWTCalculatorDSL shouldDisplay(String expected) {
        TextField display = ComponentFinder.findAll(view.getComponents(), TextField.class)
                .stream()
                .findFirst()
                .orElseThrow(() -> new AssertionError("Display TextField not found"));
        assertThat(display.getText()).isEqualTo(expected);
        return this;
    }

    /**
     * Switch calculator mode
     */
    public AWTCalculatorDSL switchTo(String mode) {
        view.switchTo(mode);
        return this;
    }

    /**
     * Assert the available buttons
     */
    public AWTCalculatorDSL shouldHaveButtons(String... expectedLabels) {
        List<String> labels = ComponentFinder.findAll(view.getComponents(), Button.class)
                .stream()
                .map(Button::getLabel)
                .collect(Collectors.toList());
        assertThat(labels).contains(expectedLabels);
        return this;
    }

    /**
     * Access the underlying view if needed
     */
    public AWTCalculatorView getView() {
        return view;
    }

    /**
     * Create AWTCalculatorView on the Event Dispatch Thread
     */
    private static AWTCalculatorView createViewOnEDT() throws Exception {
        AtomicReference<AWTCalculatorView> ref = new AtomicReference<>();
        EventQueue.invokeAndWait(() -> ref.set(new AWTCalculatorView()));
        return ref.get();
    }
}
