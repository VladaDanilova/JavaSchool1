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
    public String evaluate(String statement) {
        // если выражение null, то и считать нечего
        if (statement == null)
            return null;
        // преобразовать строку в польскую нотацию
        ChangeToPol change = new ChangeToPol(statement);
        changed = change.doChange();
        double tmpRes = 0; //промежуточный результат
        String[] tmp = changed.split(" ");
        double num, num1, num2;
        //проверка на пустое выражение
        if (changed.isEmpty())
            return null;
        //работаем с преобразованным выражением, каждая отдельная часть которого хранится в tmp
        for (int j = 0; j < tmp.length; j++) {
            //проверка на пустое выражение
            if (tmp[j].isEmpty())
                return null;
            // проверка оператор ли
            if (!tmp[j].equals("+") && !tmp[j].equals("-") &&
                    !tmp[j].equals("*") && !tmp[j].equals("/")) {
                try {
                    num = Double.valueOf(tmp[j]);
                } catch (NumberFormatException ex) {
                    //если не оператор и не число - что-то не так в выражении, null
                    return null;
                }
                stack.push(num);
            } else {
                //если оператор, то вычисляем
                // берем числа из стека
                num2 = Double.valueOf(stack.pop());
                num1 = Double.valueOf(stack.pop());
                if (tmp[j].equals("+")) {
                    tmpRes = num1 + num2;
                }
                if (tmp[j].equals("-")) {
                    tmpRes = num1 - num2;
                }
                if (tmp[j].equals("*")) {
                    tmpRes = num1 * num2;
                }
                if (tmp[j].equals("/")) {
                    //не делим на ноль!!!
                    if (num2 == 0)
                        return null;
                    tmpRes = num1 / num2;
                }
                //сохраняем промежуточный результат в начало стека
                stack.push(tmpRes);
            }
        }
        //причесываем результат
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
