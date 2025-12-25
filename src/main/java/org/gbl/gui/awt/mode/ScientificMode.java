package org.gbl.gui.awt.mode;

import org.gbl.gui.awt.symbol.Symbol;

import java.awt.*;
import java.util.List;

public final class ScientificMode implements Mode {
    @Override
    public List<Symbol> symbols() {
        return List.of(
                // Row 1
                Symbol.SIN, Symbol.COS,
                Symbol.BACKSPACE, Symbol.INVERT, Symbol.MOD, Symbol.DIV,

                // Row 2
                Symbol.TAN, Symbol.SQRT,
                Symbol.SEVEN, Symbol.EIGHT, Symbol.NINE, Symbol.MUL,

                // Row 3
                Symbol.LOG, Symbol.POW,
                Symbol.FOUR, Symbol.FIVE, Symbol.SIX, Symbol.SUB,

                // Row 4
                Symbol.LPAREN, Symbol.RPAREN,
                Symbol.ONE, Symbol.TWO, Symbol.THREE, Symbol.ADD,

                // Row 5
                Symbol.EXP, Symbol.ABS,
                Symbol.CLEAR, Symbol.ZERO, Symbol.DOT, Symbol.EQUALS
        );
    }

    @Override
    public GridLayout layout() {
        return new GridLayout(5, 6, 5, 5);
    }
}
