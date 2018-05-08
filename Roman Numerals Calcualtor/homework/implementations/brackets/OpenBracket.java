package homework.implementations.brackets;

import homework.interfaces.brackets.IBracket;

public final class OpenBracket implements IBracket {

    private String symbol;

    @Override
    public String getSymbol() {
        return this.symbol;

    }

    @Override
    public void setSymbol(final String auxSymbol) {
        symbol = auxSymbol;
    }

}
