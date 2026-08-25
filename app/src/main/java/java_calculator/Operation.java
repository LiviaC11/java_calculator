package java_calculator;

public interface Operation {
    double execute(double operand1, double operand2);

    String getSymbol();
}
