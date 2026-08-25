package java_calculator;

public interface CalculatorEngine {
    void enterNumber(double n);

    void setOp(String op);

    double calculate();

    void clear();

    double getCurrentValue();
}
