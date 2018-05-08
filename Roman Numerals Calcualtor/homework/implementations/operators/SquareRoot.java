package homework.implementations.operators;

import homework.implementations.operands.Operand;
import homework.interfaces.operands.IOperand;

public final class SquareRoot extends UnaryOperator {
    private static final int PRIORITY = 3;
    private String sqrtSymbol = "sqrt";

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public String getSymbol() {
        return sqrtSymbol;
    }

    @Override
    public IOperand<Double> calculate(final Double operand) {
        Operand op = new Operand();
        op.setSymbolValue(Math.sqrt(operand));
        return op;
    }
}
