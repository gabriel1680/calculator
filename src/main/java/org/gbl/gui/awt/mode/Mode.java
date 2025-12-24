package org.gbl.gui.awt.mode;

import java.awt.*;

public sealed interface Mode permits BasicMode, ScientificMode {
    String[] labels();

    GridLayout layout();
}
