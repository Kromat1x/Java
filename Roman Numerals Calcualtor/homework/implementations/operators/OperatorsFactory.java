package homework.implementations.operators;

import homework.interfaces.IToken;
import homework.interfaces.operators.IOperator;
import homework.interfaces.operators.IOperatorsFactory;

public final class OperatorsFactory implements IOperatorsFactory {

    private static OperatorsFactory instance = null;

    private OperatorsFactory() {
    }

    public static OperatorsFactory getInstance() {
        if (instance == null) {
            instance = new OperatorsFactory();
        }
        return instance;
    }

    @Override
    public IOperator createOperator(final String token) {
        if (token.equals("+")) {

            return new Addition();

        } else if (token.equals("-")) {

            return new Subtraction();

        } else if (token.equals("*")) {

            return new Multiplication();

        } else if (token.equals("/")) {

            return new Division();

        } else if (token.equals("^")) {

            return new Raising();

        } else if (token.equals("sqrt")) {

            return new SquareRoot();

        } else if (token.equals("log")) {

            return new Logarithm();

        } else {
            System.out.println("Unknown token !");
            return null;
        }
    }

    @Override
    public boolean isOperator(final IToken token) {
        String aux = token.getSymbol();
        if (aux.equals("+")
                || aux.equals("-") || aux.equals("/")
                || aux.equals("*") || aux.equals("sqrt")
                || aux.equals("log") || aux.equals("^")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isUnaryOperator(final IOperator operator) {
        String aux = operator.getSymbol();
        if (aux.equals("log") || aux.equals("^")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isBinaryOperator(final IOperator operator) {
        String aux = operator.getSymbol();
        if (aux.equals("+")
                || aux.equals("-") || aux.equals("/")
                || aux.equals("*") || aux.equals("^")) {
            return true;
        }
        return false;
    }

}
