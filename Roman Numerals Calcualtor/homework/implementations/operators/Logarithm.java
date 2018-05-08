package homework.implementations.operators;

import homework.implementations.operands.Operand;
import homework.interfaces.operands.IOperand;

public final class Logarithm extends UnaryOperator {
    private static final int PRIORITY = 3;
    private String logSymbol = "log";

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public String getSymbol() {
        return logSymbol;
    }

    @Override
    public IOperand<Double> calculate(final Double operand) {
        Operand op = new Operand();
        op.setSymbolValue(Math.log(operand));
        return op;
    }
}
