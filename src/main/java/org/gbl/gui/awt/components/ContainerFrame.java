package org.gbl.gui.awt.components;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ContainerFrame {

    private final Frame frame;

    public ContainerFrame() {
        this.frame = createFrame();
    }

    private static Frame createFrame() {
        final Frame frame = new Frame("Calculator");
        final var height = 400;
        frame.setSize(300, height);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // center window
        frame.setMinimumSize(new Dimension(0, height)); // height fixed
        frame.setMaximumSize(new Dimension(Integer.MAX_VALUE, height)); // height fixed
        frame.addWindowListener(new CleanupWindowAdapter(frame));
        return frame;
    }

    public void add(Presentable presentable, String position) {
        frame.add(presentable.getComponent(), position);
    }

    public void show() {
        frame.setVisible(true);
    }

    public Component[] getComponents() {
        return frame.getComponents();
    }

    public void pack() {
        frame.pack();
        frame.setResizable(false);
    }

    private static class CleanupWindowAdapter extends WindowAdapter {

        private final Frame frame;

        CleanupWindowAdapter(Frame frame) {
            this.frame = frame;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            for (Component comp : frame.getComponents()) {
                if (comp instanceof Button button) {
                    for (ActionListener al : button.getActionListeners()) {
                        button.removeActionListener(al);
                    }
                }
            }
            frame.dispose();
        }
    }
}
