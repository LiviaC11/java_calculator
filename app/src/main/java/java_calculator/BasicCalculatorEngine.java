package java_calculator;

import java.util.HashMap;
import java.util.Map;

public class BasicCalculatorEngine implements CalculatorEngine {
    private final Map<String, Operation> operations = new HashMap<>();
    private double currentValue;
    private double accumulator;
    private String currentOp;

    public BasicCalculatorEngine() {
        Operation add = new Addition();
        Operation sub = new Subtraction();
        Operation div = new Division();
        Operation mul = new Multiply();
        operations.put(add.getSymbol(), add);
        operations.put(sub.getSymbol(), sub);
        operations.put(div.getSymbol(), div);
        operations.put(mul.getSymbol(), mul);
    }

    @Override
    public void enterNumber(double n) {
        this.currentValue = n;
    }

    @Override
    public void setOp(String op) {
        if (this.currentOp != null) {
            Operation o = operations.get(currentOp);
            this.accumulator = o.execute(this.accumulator, this.currentValue);
        } else {
            this.accumulator = this.currentValue;
        }
        this.currentOp = op;
    }

    @Override
    public double calculate() {
        double result = 0;
        if (this.currentOp != null) {
            Operation op = operations.get(currentOp);
            result = op.execute(this.accumulator, this.currentValue);
            this.accumulator = result;
            this.currentValue = result;
            this.currentOp = null;
        } else {
            return this.currentValue;
        }
        return this.currentValue;
    }

    @Override
    public void clear() {
        this.currentValue = 0.0;
        this.accumulator = 0.0;
        this.currentOp = null;
    }

    @Override
    public double getCurrentValue() {
        return this.currentValue;

    }
}
