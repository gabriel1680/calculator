package org.gbl.gui.integration;

import org.gbl.gui.awt.mode.CalculatorMode;
import org.junit.jupiter.api.Test;

public class IntegrationTest {

    @Test
    void evaluate() throws Exception {
        AWTCalculatorDSL.instance()
                .type("2", "+", "2", "=").shouldDisplay("4.0")
                .type("4", "*", "4", "=").shouldDisplay("16.0")
                .type("4", "*", "4", "CE").shouldDisplay("")
                .type("1", ".", "0", "+", "0", ".", "1", "=").shouldDisplay("1.1")
                .type("1", "+/-").shouldDisplay("(-1)").type("+/-").shouldDisplay("1").type("CE")
                .type("4", "/", "4", "=").shouldDisplay("1.0");
    }

    @Test
    void switchModes() throws Exception {
        AWTCalculatorDSL.instance()
                .shouldHaveButtons(AWTCalculatorDSL.BASIC_BUTTONS)
                .switchTo(CalculatorMode.SCIENTIFIC.stringValue())
                .shouldHaveButtons(AWTCalculatorDSL.SCIENTIFIC_BUTTONS);
    }
}
