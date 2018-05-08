package homework.implementations.operators;

import homework.interfaces.operators.IOperator;

public final class Operator implements IOperator {

    private String symbol;
    private int priority;

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public void setSymbol(final String auxSymbol) {
        symbol = auxSymbol;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

}
