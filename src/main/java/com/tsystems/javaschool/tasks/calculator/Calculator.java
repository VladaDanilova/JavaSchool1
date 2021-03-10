package com.tsystems.javaschool.tasks.calculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Stack;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     * @author vladadanilova
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */

    Stack<Double> stack = new Stack<>();
    String changed;

    public Calculator() {
        stack = new Stack<>();
        changed = "";
    }


    public String evaluate(String statement) {
        // преобразовать строку в польскую нотацию
        if (statement == null)
            return null;
        ChangeToPol change = new ChangeToPol(statement);
        changed = change.doChange();
        double val;
        double tmpResult = 0;
        double num1, num2;
        //equation is incorrect
        if (changed.isEmpty())
            return null;

        String[] tmp = changed.split(" ");

        for (int j = 0; j < tmp.length; j++) {
            if (tmp[j].isEmpty())
                return null;
            // if it is not an operator
            if (!tmp[j].equals("+") && !tmp[j].equals("-") &&
                    !tmp[j].equals("*") && !tmp[j].equals("/")) {
                try {
                    val = Double.valueOf(tmp[j]);
                } catch (NumberFormatException ex) {
                    //if its not a digit return null
                    return null;
                }
                stack.push(val);
            } else {
                //if its an operator - make calculation
                num2 = Double.valueOf(stack.pop());
                num1 = Double.valueOf(stack.pop());
                if (tmp[j].equals("+")) {
                    tmpResult = num1 + num2;
                }
                if (tmp[j].equals("-")) {
                    tmpResult = num1 - num2;
                }
                if (tmp[j].equals("*")) {
                    tmpResult = num1 * num2;
                }
                if (tmp[j].equals("/")) {
                    //divide by zero is forbidden
                    if (num2 == 0)
                        return null;
                    tmpResult = num1 / num2;
                }
                stack.push(tmpResult);
            }
        }
        //making right output
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        String pattern = "#.####";
        DecimalFormat df = new DecimalFormat(pattern, otherSymbols);
        df.setRoundingMode(RoundingMode.CEILING);
        String res = "";

        res = df.format(stack.pop());
        return res;


    }


}
