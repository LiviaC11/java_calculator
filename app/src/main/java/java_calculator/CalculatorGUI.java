package java_calculator;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorGUI extends JFrame {
    private JTextField display;

    public CalculatorGUI() {
        setTitle("Calcolatrice");
        setSize(320, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);
        JPanel gridPanel = createGridPanel();
        add(gridPanle, BorderLayout.CENTER);
    }

    private JPanel createGridPanel() {
        Jpanel panel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "", "+"
        };
        for (String text : buttons) {
            if (text.isEmpty()) {
                panel.add(new JPanel()); // Spazio vuoto
            } else {
                JButton button = new JButton(text);
                panel.add(button);
            }
        }

        return panel;
    }
}
