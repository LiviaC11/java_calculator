package java_calculator;

import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorEngine engine = new BasicCalculatorEngine();
            CalculatorGUI gui = new CalculatorGUI(engine);
            gui.setVisible(true); // Rende visibile la finestra
        });
    }
}
