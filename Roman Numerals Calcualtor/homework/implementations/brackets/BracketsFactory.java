package homework.implementations.brackets;

import homework.interfaces.IToken;
import homework.interfaces.brackets.IBracket;
import homework.interfaces.brackets.IBracketsFactory;

public final class BracketsFactory implements IBracketsFactory {

    private static BracketsFactory instance = null;

    private BracketsFactory() {
    }

    public static BracketsFactory getInstance() {
        if (instance == null) {
            instance = new BracketsFactory();
        }
        return instance;
    }

    @Override
    public IBracket createBracket(final String token) {
        if (token.equals(")")
            || token.equals("]") || token.equals("}")) {
            ClosedBracket bracket = new ClosedBracket();
            bracket.setSymbol(token);
            return bracket;
        } else if (token.equals("(")
            || token.equals("[") || token.equals("{")) {
            OpenBracket bracket = new OpenBracket();
            bracket.setSymbol(token);
            return bracket;
        } else {
            return null;
        }
    }

    @Override
    public boolean isBracket(final IToken token) {
        if (isOpenedBracket((IBracket) token)
                || isClosedBracket((IBracket) token)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isOpenedBracket(final IBracket bracket) {
        if (bracket.getSymbol().equals("(")) {
            return true;
        } else if (bracket.getSymbol().equals("[")) {
            return true;
        } else {
            return bracket.getSymbol().equals("{");
        }
    }

    @Override
    public boolean isClosedBracket(final IBracket bracket) {
        if (bracket.getSymbol().equals(")")) {
            return true;
        } else if (bracket.getSymbol().equals("]")) {
            return true;
        } else {
            return bracket.getSymbol().equals("}");
        }
    }

    @Override
    public boolean isBracketPair(final IBracket openBracket,
     final IBracket closeBracket) {
        if (openBracket.getSymbol().equals("(")
                && closeBracket.getSymbol().equals(")")) {
            return true;
        } else if (openBracket.getSymbol().equals("[")
                && closeBracket.getSymbol().equals("]")) {
            return true;
        } else {
            return openBracket.getSymbol().equals("{")
                && closeBracket.getSymbol().equals("}");
        }
    }

}
