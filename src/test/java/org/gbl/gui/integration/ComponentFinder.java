package org.gbl.gui.integration;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class ComponentFinder {

    private ComponentFinder() {
    }

    public static <T extends Component> List<T> findAll(
            Component[] roots,
            Class<T> type
    ) {
        List<T> result = new ArrayList<>();
        for (Component c : roots) {
            result.addAll(collect(c, type));
        }
        return result;
    }

    private static <T extends Component> List<T> collect(Component c, Class<T> type) {
        List<T> result = new ArrayList<>();
        if (type.isInstance(c)) {
            result.add(type.cast(c));
        }
        if (c instanceof Container container) {
            for (Component child : container.getComponents()) {
                result.addAll(collect(child, type));
            }
        }
        return result;
    }
}
