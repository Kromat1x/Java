package homework.implementations.operators;

import homework.implementations.operands.Operand;
import homework.interfaces.operands.IOperand;

public final class Raising extends BinaryOperator {
    private static final int PRIORITY = 2;
    private String raiseSymbol = "^";

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public String getSymbol() {
        return raiseSymbol;
    }

    @Override
    public IOperand<Double> calculate(final Double leftOperand,
    final Double rightOperand) {
        Operand op = new Operand();
        op.setSymbolValue(Math.pow(leftOperand, rightOperand));
        return op;
    }
}
