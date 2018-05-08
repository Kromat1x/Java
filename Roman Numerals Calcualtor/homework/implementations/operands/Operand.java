package homework.implementations.operands;

import homework.interfaces.operands.IOperand;

public final class Operand implements IOperand<Double> {

    private String symbol;
    private Double value;

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public void setSymbol(final String auxSymbol) {
        symbol = auxSymbol;
    }

    @Override
    public Double getSymbolValue() {
        return this.value;
    }

    @Override
    public void setSymbolValue(final Double auxValue) {
        value = auxValue;
    }
}
