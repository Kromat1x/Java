package homework.implementations.operators;

import homework.interfaces.operands.IOperand;
import homework.interfaces.operators.binaryOperators.IBinaryOperator;

public class BinaryOperator implements IBinaryOperator<Double> {

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
    public IOperand<Double> calculate(final Double leftOperand,
     final Double rightOperand) {
        return null;
    }

}
