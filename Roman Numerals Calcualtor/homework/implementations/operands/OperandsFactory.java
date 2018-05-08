package homework.implementations.operands;

import homework.interfaces.operands.IOperand;
import homework.interfaces.operands.IOperandsFactory;

public final class OperandsFactory implements IOperandsFactory<Double> {

    public static final int THOUSAND = 1000;
    public static final int NINEH = 900;
    public static final int FIVEH = 500;
    public static final int FOURH = 400;
    public static final int ONEH = 100;
    public static final int NINETY = 90;
    public static final int FIFTY = 50;
    public static final int FORTY = 40;
    public static final int TEN = 10;
    public static final int NINE = 9;
    public static final int FIVE = 5;
    public static final int FOUR = 4;
    public static final int ONE = 1;

    private static OperandsFactory instance = null;

    private OperandsFactory() {
    }

    public static OperandsFactory getInstance() {
        if (instance == null) {
            instance = new OperandsFactory();
        }
        return instance;
    }

    @Override
    public IOperand<Double> createOperand(final String token) {
        IOperand<Double> operand = convertToArabNumber(token);
        return operand;
    }

    @Override
    public IOperand<Double> convertToRomanNumber(final Double arabNumber) {
        Operand op = new Operand();
        Double arabNumberAux = arabNumber;
        op.setSymbolValue(arabNumberAux);
        String romanNumber;
        if (arabNumberAux < 0) {
            romanNumber = "- ";
            arabNumberAux = Math.abs(arabNumberAux);
        } else {
            romanNumber = "";
        }

        while (arabNumberAux >= THOUSAND) {
            arabNumberAux -= THOUSAND;
            romanNumber += "M";
        }

        while (arabNumberAux >= NINEH) {
            arabNumberAux -= NINEH;
            romanNumber += "CM";
        }

        while (arabNumberAux >= FIVEH) {
            arabNumberAux -= FIVEH;
            romanNumber += "D";
        }

        while (arabNumberAux >= FOURH) {
            arabNumberAux -= FOURH;
            romanNumber += "CD";
        }

        while (arabNumberAux >= ONEH) {
            arabNumberAux -= ONEH;
            romanNumber += "C";
        }

        while (arabNumberAux >= NINETY) {
            arabNumberAux -= NINETY;
            romanNumber += "XC";
        }

        while (arabNumberAux >= FIFTY) {
            arabNumberAux -= FIFTY;
            romanNumber += "L";
        }

        while (arabNumberAux >= FORTY) {
            arabNumberAux -= FORTY;
            romanNumber += "XL";
        }

        while (arabNumberAux >= TEN) {
            arabNumberAux -= TEN;
            romanNumber += "X";
        }

        while (arabNumberAux >= NINE) {
            arabNumberAux -= NINE;
            romanNumber += "IX";
        }

        while (arabNumberAux >= FIVE) {
            arabNumberAux -= FIVE;
            romanNumber += "V";
        }

        while (arabNumberAux >= FOUR) {
            arabNumberAux -= FOUR;
            romanNumber += "IV";
        }

        while (arabNumberAux >= ONE) {
            arabNumberAux -= ONE;
            romanNumber += "I";
        }

        op.setSymbol(romanNumber);
        return op;
    }

    @Override
    public IOperand<Double> convertToArabNumber(final String romanNumber) {
        Operand op = new Operand();
        String romanNumberAux = romanNumber;
        op.setSymbol(romanNumberAux);
        char[] roman = romanNumberAux.toCharArray();
        Double arabNumber = 0.0;
        for (int i = roman.length - 1; i >= 0; i--) {

            if (roman[i] == 'I') {

                if (i == roman.length - 1) {
                    arabNumber += 1;
                } else if (roman[i + 1] == 'V' || roman[i + 1] == 'X') {
                    arabNumber -= 1;
                } else {
                    arabNumber += 1;
                }
            } else if (roman[i] == 'V') {

                arabNumber += FIVE;
            } else if (roman[i] == 'X') {

                if (i == roman.length - 1) {
                    arabNumber += TEN;
                } else if (roman[i + 1] == 'L' || roman[i + 1] == 'C') {
                    arabNumber -= TEN;
                } else {
                    arabNumber += TEN;
                }
            } else if (roman[i] == 'L') {

                arabNumber += FIFTY;

            } else if (roman[i] == 'C') {

                if (i == roman.length - 1) {
                    arabNumber += ONEH;
                } else if (roman[i + 1] == 'D' || roman[i + 1] == 'M') {
                    arabNumber -= ONEH;
                } else {

                    arabNumber += ONEH;
                }
            } else if (roman[i] == 'D') {

                arabNumber += FIVEH;

            } else if (roman[i] == 'M') {

                arabNumber += THOUSAND;

            }

        }
        op.setSymbolValue(arabNumber);
        return op;
    }

}
