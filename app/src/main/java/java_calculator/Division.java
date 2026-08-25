package java_calculator;

public class Division implements Operation {

    @Override
    public double execute(double operand1, double operand2) {
        if (operand2 == 0) {
            throw new IllegalArgumentException("Impossibile dividere per zero");
        }
        return operand1 / operand2;
    }

    @Override
    public String getSymbol() {
        return "/";
    }

}
