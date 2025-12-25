package org.gbl.gui.awt.components.mode;

import org.gbl.gui.awt.components.symbol.Symbol;

import java.awt.*;
import java.util.List;

public sealed interface Mode permits BasicMode, ScientificMode {
    List<Symbol> symbols();

    GridLayout layout();
}
