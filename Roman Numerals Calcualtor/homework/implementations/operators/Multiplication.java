package homework.implementations.operators;

import homework.implementations.operands.Operand;
import homework.interfaces.operands.IOperand;

public final class Multiplication extends BinaryOperator {
    private static final int PRIORITY = 1;
    private String mulSymbol = "*";

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public String getSymbol() {
        return mulSymbol;
    }

    @Override
    public IOperand<Double> calculate(final Double leftOperand,
            final Double rightOperand) {
        Operand op = new Operand();
        op.setSymbolValue(leftOperand * rightOperand);
        return op;
    }
}
