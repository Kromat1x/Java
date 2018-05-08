package homework.implementations.operators;

import homework.interfaces.operands.IOperand;
import homework.interfaces.operators.unaryOperators.IUnaryOperator;

public class UnaryOperator implements IUnaryOperator<Double> {

    private int priority;
    private String symbol;

/**
*
*/
    @Override
    public int getPriority() {
        return this.priority;
    }

/**
*
*/
    @Override
    public String getSymbol() {
        return this.symbol;
    }

/**
*
*/
    @Override
    public void setSymbol(final String auxSymbol) {
        symbol = auxSymbol;
    }

/**
*
*/
    @Override
    public IOperand<Double> calculate(final Double operand) {
        return null;
    }

}
