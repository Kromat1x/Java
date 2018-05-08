package homework.implementations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import homework.implementations.brackets.BracketsFactory;
import homework.implementations.operands.OperandsFactory;
import homework.implementations.operators.BinaryOperator;
import homework.implementations.operators.OperatorsFactory;
import homework.implementations.operators.UnaryOperator;
import homework.interfaces.IServer;
import homework.interfaces.IToken;
import homework.interfaces.brackets.IBracket;
import homework.interfaces.operands.IOperand;
import homework.interfaces.operators.IOperator;

public final class Server implements IServer {

    private static Server instance = null;

    private Server() {

    }

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    private List<String> operatorList = new ArrayList<String>();
    private LinkedList<String> resultsList = new LinkedList<String>();
    private Stack<IToken> myStack = new Stack<IToken>();

    @Override
    public boolean canPublish(final String[] tokens) {

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+") || tokens[i].equals("-")
                    || tokens[i].equals("*") || tokens[i].equals("/")
                    || tokens[i].equals("^") || tokens[i].equals("sqrt")
                    || tokens[i].equals("log")) {

                if (!operatorList.contains(tokens[i])) {

                    return false;

                }
            }

        }
        return true;
    }

    @Override
    public void publish(final String command) {
        String[] tokens = command.split(" ");
        List<IToken> postfix = new ArrayList<IToken>();
        BracketsFactory brackets = BracketsFactory.getInstance();
        OperatorsFactory operators = OperatorsFactory.getInstance();
        OperandsFactory operands = OperandsFactory.getInstance();
        int indicator = 0;
        if (!canPublish(tokens)) {

            resultsList.add("IMPOSSIBRU");

        } else {

            for (int i = 0; i < tokens.length; i++) {

                if (tokens[i].equals("(") || tokens[i].equals("[")
                        || tokens[i].equals("{")) {

                    IBracket bracket = brackets.createBracket(tokens[i]);
                    myStack.push(bracket);

                } else if (tokens[i].equals(")") || tokens[i].equals("]")
                        || tokens[i].equals("}")) {

                    while ((!myStack.isEmpty()) && (!myStack.peek().getSymbol()
                            .equals("(")
                            && !myStack.peek().getSymbol().equals("[")
                            && !myStack.peek().getSymbol().equals("{"))) {

                        postfix.add(myStack.pop());
                    }
                    if (!myStack.isEmpty()) {
                        myStack.pop();
                    }

                } else if (tokens[i].equals("+") || tokens[i].equals("-")
                        || tokens[i].equals("/") || tokens[i].equals("*")
                        || tokens[i].equals("^") || tokens[i].equals("sqrt")
                        || tokens[i].equals("log")) {

                    IOperator opAux = operators.createOperator(tokens[i]);
                    while ((!myStack.isEmpty()) && (myStack.peek().getSymbol()
                            .equals("log")
                            || myStack.peek().getSymbol().equals("sqrt")
                            || myStack.peek().getSymbol().equals("+")
                            || myStack.peek().getSymbol().equals("-")
                            || myStack.peek().getSymbol().equals("/")
                            || myStack.peek().getSymbol().equals("^")
                            || myStack.peek().getSymbol().equals("*"))) {

                        if (opAux.getPriority() <= ((IOperator) myStack.peek())
                                .getPriority()) {
                            postfix.add(myStack.pop());
                        } else {
                            break;
                        }
                    }

                    myStack.push(opAux);

                } else {
                    IOperand<Double> operand = operands
                            .createOperand(tokens[i]);
                    postfix.add(operand);
                }
            }
            while (!myStack.isEmpty()) {
                postfix.add(myStack.pop());
            }

            // Evaluare
            for (int i = 0; i < postfix.size(); i++) {
                IToken auxToken = postfix.get(i);

                if (operators.isOperator(auxToken)) {

                    if (operators.isBinaryOperator((IOperator) auxToken)) {
                        IOperand<Double> operand1 = (IOperand<Double>) myStack
                                .pop();
                        IOperand<Double> operand2 = (IOperand<Double>) myStack
                                .pop();
                        if (operand1.getSymbolValue().equals(0.0)
                                && ((BinaryOperator) auxToken).getSymbol()
                                        .equals("/")) {
                            resultsList.add("IMPOSSIBRU");
                            indicator = 1;
                            break;
                        }
                        myStack.push(((BinaryOperator) auxToken).calculate(
                                operand2.getSymbolValue(),
                                operand1.getSymbolValue()));
                    } else {
                        IOperand<Double> operand3 = (IOperand<Double>) myStack
                                .pop();
                        myStack.push(((UnaryOperator) auxToken)
                                .calculate(operand3.getSymbolValue()));
                    }
                } else {
                    myStack.push(auxToken);
                }
            }
            if (indicator == 0) {
                IOperand<Double> result = (IOperand<Double>) myStack.pop();
                result.setSymbolValue(Math.floor(result.getSymbolValue()));
                result = operands.convertToRomanNumber(result.getSymbolValue());
                if (!result.getSymbol().equals("")) {
                    resultsList.add(result.getSymbol());
                }
            }
        }
    }

    @Override
    public void subscribe(final String operator) {
        operatorList.add(operator);
    }

    @Override
    public List<String> getResults() {
        return resultsList;
    }

}
