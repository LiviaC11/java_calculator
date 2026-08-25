package java_calculator;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorGUI extends JFrame {
    private JTextField display;
    private final CalculatorEngine engine;
    private boolean isNewInput;

    public CalculatorGUI(CalculatorEngine engine) {
        this.isNewInput = true;
        this.engine = engine;
        setTitle("Calcolatrice");
        setSize(320, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JTextField("0");
        display.setEditable(false);

        add(display, BorderLayout.NORTH);
        JPanel gridPanel = createGridPanel();
        add(gridPanel, BorderLayout.CENTER);
        JPanel southPanel = createSouthPanel();
        add(southPanel, BorderLayout.SOUTH);
    }

    private JPanel createSouthPanel() {
        JPanel panel = new JPanel((new GridLayout(1, 2, 4, 5)));
        String[] buttons = {
                "C", "="
        };
        ActionListener listener = new ButtonClickListener();
        for (String text : buttons) {
            if (text.isEmpty()) {
                panel.add(new JPanel());
            } else {

                JButton button = new JButton(text);
                button.addActionListener(listener);
                panel.add(button);
            }
        }
        return panel;
    }

    private JPanel createGridPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 4, 4, 4));
        String[] buttons = {
                "1", "2", "3", "4",
                "5", "6", "7", "8",
                "9", "0", "+", "-",
                "*", "/", "", ""
        };
        ActionListener listener = new ButtonClickListener();
        for (String text : buttons) {
            if (text.isEmpty()) {
                panel.add(new JPanel()); // Spazio vuoto
            } else {
                JButton button = new JButton(text);
                button.addActionListener(listener);
                panel.add(button);
            }
        }

        return panel;
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand(); // Restituisce il testo del bottone (es. "7", "+", ecc.)

            if ("0123456789.".contains(command)) {
                handleNumber(command);
            } else if ("+-*/".contains(command)) {
                handleOperator(command);
            } else if ("=".equals(command)) {
                handleEquals();
            } else if ("C".equals(command)) {
                handleClear();
            }
        }
    }

    private void handleNumber(String digit) {
        if (isNewInput) {
            display.setText(digit);
            isNewInput = false;
        } else {
            String currentText = display.getText();
            display.setText(currentText + digit);
        }
    }

    private void handleOperator(String symbol) {
        try {
            double num = Double.parseDouble(display.getText());
            engine.enterNumber(num);
            engine.setOp(symbol);
            isNewInput = true;
        } catch (Exception e) {
            display.setText("Error");
            isNewInput = true;
        }

    }

    private void handleEquals() {
        try {
            double num = Double.parseDouble(display.getText());
            engine.enterNumber(num);

            String toPrint = "";
            toPrint = toPrint + engine.calculate();
            display.setText(toPrint);
            isNewInput = true;
        } catch (IllegalArgumentException e) {
            display.setText("Error");
            isNewInput = true;
        }

    }

    private void handleClear() {
        engine.clear();
        display.setText("0");
        isNewInput = true;
    }
}
