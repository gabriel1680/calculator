package org.gbl.gui.awt.components.mode;

import org.gbl.gui.awt.components.symbol.Symbol;

import java.awt.*;
import java.util.List;

public final class BasicMode implements Mode {
    @Override
    public List<Symbol> symbols() {
        return List.of(
                Symbol.BACKSPACE, Symbol.INVERT, Symbol.MOD, Symbol.DIV,
                Symbol.SEVEN, Symbol.EIGHT, Symbol.NINE, Symbol.MUL,
                Symbol.FOUR, Symbol.FIVE, Symbol.SIX, Symbol.SUB,
                Symbol.ONE, Symbol.TWO, Symbol.THREE, Symbol.ADD,
                Symbol.CLEAR, Symbol.ZERO, Symbol.DOT, Symbol.EQUALS
        );
    }

    @Override
    public GridLayout layout() {
        return new GridLayout(5, 4, 5, 5);
    }
}
