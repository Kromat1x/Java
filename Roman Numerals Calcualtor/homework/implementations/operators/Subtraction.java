package homework.implementations.operators;

import homework.implementations.operands.Operand;
import homework.interfaces.operands.IOperand;

public final class Subtraction extends BinaryOperator {
    private static final int PRIORITY = 0;
    private String subSymbol = "-";

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public String getSymbol() {
        return subSymbol;
    }

    @Override
    public IOperand<Double> calculate(final Double leftOperand,
    final Double rightOperand) {
        Operand op = new Operand();
        op.setSymbolValue(leftOperand - rightOperand);
        return op;
    }
}
