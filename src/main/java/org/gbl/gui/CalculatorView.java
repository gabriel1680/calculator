package org.gbl.gui;

import java.util.function.Consumer;

public interface CalculatorView {

    void showText(String text);

    void clearText();

    void onInput(Consumer<CalculatorInput> listener);

    void show();
}
