package com.tsystems.javaschool.tasks.calculator;

import java.util.Stack;

/**
 * @author vladadanilova
 * класс нужен для того, чтобы преобразовать строку в польскую нотацию
 * например: (8+2*5)/(1+3*2-4) -> 825*+132*+4-/
 */
public class ChangeToPol {
    private String input;
    private String output = "";
    private Stack <Character> stack;

    //конструктор
    public ChangeToPol(String inp)
    {
        input = inp;
        int stackSize = input.length();
        stack = new Stack<>();
    }

    public void getOperator(char newOp, int pre1) { //операторы и приоритеты
        while (!stack.isEmpty()) {
            char operF = stack.pop(); //берем первый элемент из стека
            //смотрим, что это за оператор
            if (operF ==  '(') {
                stack.push(operF); //в начало стека
                break;
            }
            else {
                int pre2;
                if (operF == '+' || operF == '-')     // приоритет 1
                    pre2 = 1;
                else
                    pre2 = 2; //приоритет 2
                if (pre2 < pre1) {              // сравниванием новый и старый по приоритету
                    stack.push(operF);
                    break;
                }
                else {
                    output = output + operF + ' ';
                }
            }
        }
        stack.push(newOp);     // добавляем новый оператор в стек
    }

    public String doChange() // метод осуществляет преобразование
    {
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == ' ')
                continue;
            if (ch == '+' || ch == '-') { // приоритет 1
                output += ' ';
                getOperator(ch, 1);
                continue;
            }
            if (ch == '*' || ch == '/') { // приоритет 2
                output += ' ';
                getOperator(ch, 2);
                continue;
            }
            if (ch == '(') {
                stack.push(ch);
                continue;
            }
            if (ch == ')') {
                int result = getP(ch);
                if (result == 0) return "";
            } else {
                output += ch;   // записываем в output
            }


        }
        while (!stack.isEmpty()) // добавляем оставшееся
        {
            output += ' ';
            output += stack.pop(); // сохраняем в output
        }
        return output;    // возвращаем преобразованный результат
    }

    public int getP(char ch) { //на вход идёт правая скобка
        //если в стеке меньше 2 элементов, то выражение неверное
        if (stack.size() < 2)
            return 0;
        while (!stack.isEmpty()) {
            char ch2 = stack.pop();
            if (ch2 == '(') {
                break;
            }
            else {
                output += ' ';
                output += ch2; // output it
            }
        }
        return 1;
    }


}
